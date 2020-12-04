package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class PassportValidatorTest {
    val pv = PassportValidator()

    @Test
    fun countValidPassportsTest() {
        var testData = File("src/test/resources/p4-test-input.txt")
        var ret = pv.countValidPassports(testData, listOf("cid"))
        assertEquals(2, ret)

        testData = File("src/test/resources/p4-input.txt")
        ret = pv.countValidPassports(testData, listOf("cid"))
        assertEquals(256, ret) // solution for puzzle part 1

        ret = pv.countValidPassportsComplex(testData, listOf("cid"))
        assertEquals(198, ret) // solution for puzzle part 2

    }
}
