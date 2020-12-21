package com.aoc2020

import kotlin.math.pow

class SeatCalculator {
    fun getSeatId(seatCode: String): Int {
        val ret: Int
        val rc = getRowColumn(seatCode)
        ret = rc.first * 8 + rc.second
        return ret
    }

    fun getRowColumn(seatCode: String): Pair<Int, Int> {
        var row = 63
        var exp = 5
        for (r in 0..5) {
            if (seatCode.get(r) == 'F') row -= 2.toDouble().pow(exp).toInt()
            if (seatCode.get(r) == 'B') row += 2.toDouble().pow(exp).toInt()
            exp--
        }
        if (seatCode.get(6) == 'B') row++
        var col = 3
        exp = 1
        for (c in 7..8) {
            if (seatCode.get(c) == 'L') col -= 2.toDouble().pow(exp).toInt()
            if (seatCode.get(c) == 'R') col += 2.toDouble().pow(exp).toInt()
            exp--
        }
        if (seatCode.get(9) == 'R') col++
        return Pair(row, col)
    }
}
