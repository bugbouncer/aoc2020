package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class PasswordCheckerTest {
    val p = PasswordChecker()

    @Test
    fun testPwdRules() {
        val input = listOf<String>("1-3 a: abcde",
                "1-3 b: cdefg", "2-9 c: ccccccccc")
        val ret1 = p.getNumberOfValidPasswords(input)
        assertEquals(2, ret1)

        val testDataStr = File("src/test/resources/p2-input.txt").readLines()
        val ret2 = p.getNumberOfValidPasswords(testDataStr)
        assertEquals(591, ret2)

        val ret3 = p.getNumberOfValidPasswords(input, "tobogan")
        assertEquals(1, ret3)

        val ret4 = p.getNumberOfValidPasswords(testDataStr, "tobogan")
        assertEquals(335, ret4)
    }


}
