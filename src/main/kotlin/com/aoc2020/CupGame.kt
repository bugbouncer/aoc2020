package com.aoc2020

class CupGame(input: String) {
    val rb = RingBuffer<Int>()

    init {
        input.forEach { rb.append(it.toString().toInt()) }
    }

    fun play(moves: Int, returnElementsAfter: Int = 1): String {
        var ret = ""
        return ret
    }
}
