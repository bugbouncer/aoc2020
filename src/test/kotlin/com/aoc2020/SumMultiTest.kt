package com.aoc

import com.aoc2020.SumMulti
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class SumMultiTest {
    val sumMulti = SumMulti()
    @Test
    fun findPair() {
        val ret1 = sumMulti.findPairForSum(listOf<Int>(1721, 979, 366, 299, 675, 1456), 2020)
        Assertions.assertEquals(Pair(1721, 299), ret1)

        val testDataStr = File("src/test/resources/p1-input.txt").readLines()
        var testData = mutableListOf<Int>()

        testDataStr.forEach{ s -> testData.add(s.toInt()) }
        val ret2 = sumMulti.findPairForSum(testData, 2020)
        Assertions.assertEquals(Pair(1209, 811), ret2)
        println("Solution for puzzle 1: ${ret2.first * ret2.second}")

    }

    @Test
    fun findTriple() {
        val ret1 = sumMulti.findTripleForSum(listOf<Int>(1721, 979, 366, 299, 675, 1456), 2020)
        Assertions.assertEquals(Triple(979, 366, 675), ret1)

        val testDataStr = File("src/test/resources/p1-input.txt").readLines()
        var testData = mutableListOf<Int>()

        testDataStr.forEach{ s -> testData.add(s.toInt()) }

        val ret2 = sumMulti.findTripleForSum(testData, 2020)
        Assertions.assertEquals(Triple(1198,373,449), ret2)
        println("Solution for puzzle 1 part 2: ${ret2.first * ret2.second * ret2.third}")
    }
}
