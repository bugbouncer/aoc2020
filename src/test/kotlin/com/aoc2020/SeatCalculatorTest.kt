package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class SeatCalculatorTest {
    var sc = SeatCalculator()

    @Test
    fun seatIdTest() {
        var seatId = sc.getSeatId("BFFFBBFRRR")
        assertEquals(seatId, 567)
        seatId = sc.getSeatId("FFFBBBFRRR")
        assertEquals(seatId, 119)
        seatId = sc.getSeatId("BBFFBBFRLL")
        assertEquals(seatId, 820)
    }

    @Test
    fun findHighestSeat() {
        val testData = File("src/test/resources/p5-input.txt").readLines()
        var high = 0
        testData.forEach { p ->
            val seatId = sc.getSeatId(p)
            if (seatId > high) high = seatId
        }
        assertEquals(978, high)
        println("hightest seatId = $high")
    }

    @Test
    fun findMySeat() {
        var seatIdList = mutableListOf<Int>()
        for (row in 1..127) {
            for (col in 0..7) {
                seatIdList.add(row * 8 + col)
            }
        }
        val testData = File("src/test/resources/p5-input.txt").readLines()

        testData.forEach { p ->
            val seatId = sc.getSeatId(p)
            seatIdList.remove(seatId)
        }
        // debug and check reamaining items in seatIdList
        // the one without neighbours is yours
        // correct answer : 727
    }
}


