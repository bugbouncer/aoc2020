package com.aoc2020

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CupGameTest {
    @Test
    fun test() {
        val cg = CupGame("389125467")
        var ret = cg.play(10)
        assertEquals("67384529", ret)
    }
}
