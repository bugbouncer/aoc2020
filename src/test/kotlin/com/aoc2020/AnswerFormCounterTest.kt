package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class AnswerFormCounterTest {
    val afc = AnswerFormCounter()

    @Test
    fun test() {
        var ret: Int
        var testData = File("src/test/resources/p6-test-input.txt").readLines()
        ret = afc.countAnswers(testData)
        assertEquals(11, ret)

        testData = File("src/test/resources/p6-input.txt").readLines()
        ret = afc.countAnswers(testData)
        assertEquals(6259, ret)

        testData = File("src/test/resources/p6-test-input.txt").readLines()
        ret = afc.countEverybodiesDarlings(testData)
        assertEquals(6, ret)

        testData = File("src/test/resources/p6-input.txt").readLines()
        ret = afc.countEverybodiesDarlings(testData)
        assertEquals(3178, ret) // too low
    }
}
