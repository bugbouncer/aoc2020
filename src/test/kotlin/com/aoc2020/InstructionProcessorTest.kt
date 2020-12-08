package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class InstructionProcessorTest {
    @Test
    fun test() {
        var testData = File("src/test/resources/p8-test-input.txt").readLines()
        var ip = InstructionProcessor(testData)
        var ret = ip.execute()
        assertEquals(5, ret)

        ip.findNaturalEnd()

        testData = File("src/test/resources/p8-input.txt").readLines()
        ip = InstructionProcessor(testData)
        ret = ip.execute()
        assertEquals(1487, ret) // solution for part 1
        // solution for part 2 = 1607 (look in log)

        ip.findNaturalEnd()

    }
}
