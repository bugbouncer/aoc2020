package com.aoc2020

import org.apache.logging.log4j.LogManager

class JoltCounter {
    var diffs = mutableMapOf<Int, Int>()
    val log = LogManager.getLogger("JoltCounter")
    fun find1and3Diffs(input: List<Int>): Pair<Int, Int> {
        diffs.clear()
        var myList = mutableListOf<Int>()
        myList.addAll(input)
        var cj = 0;
        while (myList.size > 0) {
            val min = findMin(myList)
            storeDiff(cj, min)
            cj = min
            myList.remove(min)
        }
        return Pair(diffs[1] ?: 0, (diffs[3] ?: 0) + 1)
    }

    private fun storeDiff(a: Int, b: Int) {
        var diff = b - a
        diffs.putIfAbsent(diff, 0)
        val current = diffs[diff] ?: 0
        diffs.put(diff, current + 1)
    }

    private fun findMin(input: List<Int>): Int {
        var min = Int.MAX_VALUE
        input.forEach {
            if (it < min) min = it
        }
        return min
    }

    var chunk = listOf<Int>()
    var possibilities = 0L

    fun findPossibilities(input: List<Int>): Long {
        var ret = 1L
        log.atInfo().log("Counting possible combinations")
        val sorted = input.sorted()
        val chunks = splitAt3(sorted)

        chunks.forEach {
            chunk = it
            possibilities = 0
            findPossibilitiesRecursive(0)
            if (possibilities > 0) ret = ret * possibilities

            log.atInfo().log("Possibilities: $ret")
        }

        return ret
    }

    fun splitAt3(input: List<Int>): List<List<Int>> {
        var ret = mutableListOf<List<Int>>()
        var chunk = mutableListOf<Int>()
        var i = 0
        do {
            chunk.add(input[i])
            if (chunk.size > 11 && (i != input.size - 1) && (input[i + 1] - input[i] == 3)) {
                ret.add(chunk)
                chunk = mutableListOf()
            }
            i++
        } while (i < input.size)
        ret.add(chunk)
        return ret
    }

    private fun findPossibilitiesRecursive(startAt: Int) {
        if (startAt == chunk.size - 1) {
            possibilities += 1
            return
        }
        val currentJolt = chunk[startAt]
        var i = 1
        while (((startAt + i) < (chunk.size)) && (chunk[startAt + i] - currentJolt < 4)) {
            findPossibilitiesRecursive(startAt + i)
            i++
        }
    }
}
