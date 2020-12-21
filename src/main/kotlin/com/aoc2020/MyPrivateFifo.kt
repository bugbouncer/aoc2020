package com.aoc2020

import org.apache.logging.log4j.LogManager

class MyPrivateFifo(val input: List<Long>, val lenght: Int) {
    private val log = LogManager.getLogger("sosologger")
    fun findInvalidNumber(): Long {
        log.info("findInvalidNumber ...")
        var ret = 0L
        val myFifo = MyFifo(lenght)
        input.forEach {
            if (!myFifo.add(it)) return it
        }
        return ret
    }

    fun findContiguous(value: Long): Pair<Long, Long> {
        for (first in 0 until input.size) {
            var sum = input[first]
            var min = input[first]
            var max = input[first]

            var last = first + 1
            do {
                sum += input[last]
                if (input[last] > max) max = input[last]
                if (input[last] < min) min = input[last]
                if (sum == value) return Pair(min, max)
                last++
            } while (last < input.size && sum < value)
        }
        return Pair(0, 0)
    }
}

class MyFifo(val length: Int) {
    var fifo = mutableListOf<Long>()

    fun add(value: Long): Boolean {
        if (fifo.size < length) fifo.add(value)
        else {
            if (isValid(value)) {
                fifo.removeAt(0)
                fifo.add(value)
            } else {
                return false
            }
        }
        return true
    }

    private fun isValid(value: Long): Boolean {
        for (i in 0 until fifo.size) {
            for (j in 0 until fifo.size) {
                if (i != j) {
                    if ((fifo[i] + fifo[j]) == value) return true
                }
            }
        }
        return false
    }
}
