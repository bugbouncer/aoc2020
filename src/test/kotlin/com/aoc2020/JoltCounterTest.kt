package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class JoltCounterTest {
    @Test
    fun test() {
        var jc = JoltCounter()
        var testData = File("src/test/resources/p10-test-input1.txt").readLines()
        var jolts = mutableListOf<Int>()
        testData.forEach {
            jolts.add(it.toInt())
        }
        var ret = jc.find1and3Diffs(jolts)
        assertEquals(Pair(7, 5), ret)

        jc = JoltCounter()
        testData = File("src/test/resources/p10-input.txt").readLines()
        jolts = mutableListOf<Int>()
        testData.forEach {
            jolts.add(it.toInt())
        }
        ret = jc.find1and3Diffs(jolts)
        assertEquals(Pair(69, 30), ret)
        println("Result day 10 part 1: ${ret.first * ret.second}")
    }

    @Test
    fun countPossibilities() {
        var js = JoltCounter()
        var testData = File("src/test/resources/p10-test-input1.txt").readLines()
        var jolts = mutableListOf<Int>()
        testData.forEach {
            jolts.add(it.toInt())
        }
        var ret = js.findPossibilities(jolts)
        assertEquals(8, ret)

        js = JoltCounter()
        testData = File("src/test/resources/p10-input.txt").readLines()
        jolts = mutableListOf<Int>()
        testData.forEach {
            jolts.add(it.toInt())
        }
        ret = js.findPossibilities(jolts)
        assertEquals(12089663946752, ret) // too low

    }
}
