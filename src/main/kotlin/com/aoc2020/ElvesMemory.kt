package com.aoc2020

class ElvesMemory {
    var myList = mutableListOf<Int>()
    public fun getFinalNumber(input: List<Int>, turns: Int = 2020): Int {
        myList.addAll(input)

        var lastIndexOf = mutableMapOf<Int, Int>()
        for (i in 0 until myList.size) {
            lastIndexOf.putIfAbsent(myList[i], i)
            lastIndexOf[myList[i]] = i
        }
        myList.add(0)
        var x = 0

        for (i in input.size until turns - 1) {
            if (!lastIndexOf.contains(myList[i])) {
                lastIndexOf.put(myList[i], i)
                x = i
            } else {
                x = lastIndexOf[myList[i]]!!
                lastIndexOf[myList[i]] = i
            }
            if (i % 1000000 == 0) {
                val heapSize = Runtime.getRuntime().totalMemory()
                val heapMaxSize = Runtime.getRuntime().maxMemory()
                println("Turn $i $heapSize / $heapMaxSize")
            }
            myList.add(i - x)
        }
        println("Returning ${myList.last()}")
        return myList.last()
    }

    private fun findNumberBefore(number: Int, before: Int): Int {
        for (i in before downTo 0) {
            if (myList[i] == number) {
                return i
            }
        }
        return before + 1 // starting number
    }
}
