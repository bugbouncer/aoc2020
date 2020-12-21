package com.aoc2020

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ElvesMemoryTest {
    @Test
    fun testMemory() {
        println(Runtime.version())
        println("Max heap: ${Runtime.getRuntime().maxMemory()}")
        var ret = ElvesMemory().getFinalNumber(listOf(0, 3, 6))
        assertEquals(436, ret)

        ret = ElvesMemory().getFinalNumber(listOf(1, 3, 2))
        assertEquals(1, ret)

        ret = ElvesMemory().getFinalNumber(listOf(2, 1, 3))
        assertEquals(10, ret)

        ret = ElvesMemory().getFinalNumber(listOf(1, 2, 3))
        assertEquals(27, ret)

        ret = ElvesMemory().getFinalNumber(listOf(1, 2, 16, 19, 18, 0))
        assertEquals(536, ret) // solution day 15 - part 1

        ret = ElvesMemory().getFinalNumber(listOf(0, 3, 6), 30000000)
        assertEquals(175594, ret)

        ret = ElvesMemory().getFinalNumber(listOf(1, 3, 2), 30000000)
        assertEquals(2578, ret)

        ret = ElvesMemory().getFinalNumber(listOf(2, 1, 3), 30000000)
        assertEquals(3544142, ret)

        ret = ElvesMemory().getFinalNumber(listOf(1, 2, 3), 30000000)
        assertEquals(261214, ret)

        ret = ElvesMemory().getFinalNumber(listOf(1, 2, 16, 19, 18, 0), 30000000)
        assertEquals(24065124, ret) // solution day 15 - part 2


    }
}
