package com.aoc2020

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.MarkerManager
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class FifoTest {
    var log = LogManager.getLogger("TEST")
    var marker = MarkerManager.getMarker("A MARKER")

    @Test
    fun test() {
        println("Log level: ${log.level}")
        log.info("soso")
        log.info(marker)
        log.atInfo().withMarker(marker).log("Starting 'test'")
        var testData = File("src/test/resources/p9-test-input.txt").readLines()
        var longs = mutableListOf<Long>()
        testData.forEach {
            longs.add(it.toLong())
        }
        var myPrivateFifo = MyPrivateFifo(longs, 5)
        var ret = myPrivateFifo.findInvalidNumber()
        assertEquals(127, ret)

        // part 2
        val pair = myPrivateFifo.findContiguous(ret)
        assertEquals(62, pair.first + pair.second)

    }

    @Test
    fun testPart1() {
        var testData = File("src/test/resources/p9-input.txt").readLines()
        var longs = mutableListOf<Long>()
        testData.forEach {
            longs.add(it.toLong())
        }
        var myPrivateFifo = MyPrivateFifo(longs, 25)
        var ret = myPrivateFifo.findInvalidNumber()
        assertEquals(257342611, ret)

        // part 2
        val pair = myPrivateFifo.findContiguous(ret)
        assertEquals(35602097, pair.first + pair.second)
    }
}
