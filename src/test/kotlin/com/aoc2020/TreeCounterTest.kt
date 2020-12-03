package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

/**
 * Puzzle 3
 */

class TreeCounterTest {
    private val tc = TreeCounter()

    @Test
    fun treeCountTest() {
        val testDataStr = File("src/test/resources/p3-test-input.txt").readLines()
        val ret1 = tc.countTrees(testDataStr)
        assertEquals(ret1, 7)

        val testData2 = File("src/test/resources/p3-input.txt").readLines()
        val ret2 = tc.countTrees(testData2)
        assertEquals(ret2, 184)
    }

    @Test
    fun treeCountTestPart2() {
        var testDataStr = File("src/test/resources/p3-test-input.txt").readLines()
        var result : Long = 1
        var ret = tc.countTrees(testDataStr, 1, 1)
        assertEquals(ret, 2)
        result *= ret
        ret = tc.countTrees(testDataStr, 1, 3)
        assertEquals(ret, 7)
        result *= ret
        ret = tc.countTrees(testDataStr, 1, 5)
        assertEquals(ret, 3)
        result *= ret
        ret = tc.countTrees(testDataStr, 1, 7)
        assertEquals(ret, 4)
        result *= ret
        ret = tc.countTrees(testDataStr, 2, 1)
        assertEquals(ret, 2)
        result *= ret
        println("Test data result: $result")


        testDataStr = File("src/test/resources/p3-input.txt").readLines()
        result = 1
        ret = tc.countTrees(testDataStr, 1, 1)
        assertEquals(ret, 62)
        result *= ret
        ret = tc.countTrees(testDataStr, 1, 3)
        assertEquals(ret, 184)
        result *= ret
        ret = tc.countTrees(testDataStr, 1, 5)
        assertEquals(ret, 80)
        result *= ret
        ret = tc.countTrees(testDataStr, 1, 7)
        assertEquals(ret, 74)
        result *= ret
        ret = tc.countTrees(testDataStr, 2, 1)
        assertEquals(ret, 36)
        result *= ret
        // wrong: 2093596160
        println("Part 2 result: $result")

    }

}
