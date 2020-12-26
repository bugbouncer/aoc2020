package com.aoc2020

import org.apache.logging.log4j.LogManager

class CupGame(input: String) {
    val log = LogManager.getLogger("CupGame")
    val rb = RingBuffer<Int>()

    init {
        input.forEach { rb.append(it.toString().toInt()) }
    }

    fun generateData() {
        for(i in 10..1000000 ) rb.append(i)
        log.debug("Last Element is: ${rb.getLastElement()}")
    }

    fun getResultPart2() : Long {
        var ret = 0L
        rb.moveCursorTo(1)
        val x = rb.popNext()!!.toLong()
        val y = rb.popNext()!!.toLong()
        if (x != null && y != null) {
            ret = x*y
        }
        return ret
    }

    fun play(moves: Int, returnElementsAfter: Int = 1) {
        var bench = mutableListOf<Int>()
        var destination: Int
        for( i in 1..moves) {
            if(i % 100000 == 0) println("-- move $i --")
            var current = rb.getCurrent()
            for(x in 1..3) bench.add(rb.getNext()?:0)
            if(log.isDebugEnabled) {
                var debugString = "Pick up: "
                bench.forEach {
                    debugString += it
                    debugString += ", "
                }
                log.debug(debugString)
            }
            destination = findNextCurrentCup(current, bench)
            log.atDebug().log("Destination: $destination")

            rb.moveElements(3, current, destination)
            bench.clear()
            rb.moveCursorTo(current)
            rb.moveCursorNext()
        }
    }

    fun getResultPart1(returnElementsAfter: Int = 1) : String {
        var ret = ""
        rb.moveCursorTo(returnElementsAfter)
        var e = rb.popNext()
        while( e != returnElementsAfter) {
            ret += e.toString()
            e = rb.popNext()
        }
        return ret
    }

    private fun findNextCurrentCup(current: Int, bench : List<Int>): Int {
        var i = current
        var ok = false
        do {
            i--
            if(i==0) i = 1000000
            if(!bench.contains(i)) ok = true
        } while (!ok)
        return i
    }

}
