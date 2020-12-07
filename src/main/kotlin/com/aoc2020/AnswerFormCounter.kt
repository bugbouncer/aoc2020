package com.aoc2020

class AnswerFormCounter {
    fun countAnswers(input: List<String>): Int {
        val groupedAnswers = mutableMapOf<Int, MutableSet<Char>>()
        var groupCounter = 1
        groupedAnswers.put(groupCounter, mutableSetOf<Char>())

        input.forEach { line ->
            if (line.isEmpty()) {
                groupCounter++
                groupedAnswers.put(groupCounter, mutableSetOf<Char>())
            } else {
                line.forEach { cha -> groupedAnswers[groupCounter]!!.add(cha) }
            }
        }
        var ret = 0
        groupedAnswers.forEach { it -> ret += it.value.size }
        return ret
    }

    fun countEverybodiesDarlings(input: List<String>): Int {
        val groupedAnswers = mutableMapOf<Int, MutableMap<Char, Int>>()
        var groupCounter = 1
        groupedAnswers.put(groupCounter, mutableMapOf<Char, Int>())
        val groupSizes = mutableMapOf<Int, Int>()

        input.forEach { line ->
            if (line.isEmpty()) {
                groupCounter++
                groupedAnswers.put(groupCounter, mutableMapOf<Char, Int>())
            } else {
                groupSizes.putIfAbsent(groupCounter, 0)
                var soso = groupSizes.get(groupCounter) ?: 0
                groupSizes.put(groupCounter, soso + 1)

                line.forEach { cha ->
                    groupedAnswers[groupCounter]!!.putIfAbsent(cha, 0)
                    val aha = groupedAnswers[groupCounter]!!.get(cha) ?: 0
                    groupedAnswers[groupCounter]!!.set(cha, aha + 1)
                }
            }
        }
        var ret = 0
        groupedAnswers.forEach { it ->
            val groupSize = groupSizes[it.key]
            it.value.forEach { aha ->
                if (aha.value == groupSize) ret++
            }
        }
        return ret
    }
}
