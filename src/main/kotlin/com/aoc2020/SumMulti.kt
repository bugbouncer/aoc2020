package com.aoc2020

class SumMulti {
    fun findPairForSum(numbers: List<Int>, targetSum: Int): Pair<Int, Int> {
        val numbers2 = numbers.toList()
        numbers.forEach{
            n1 ->
            numbers2.forEach{
                n2 ->
                if( (n1 + n2) == targetSum) {
                    return Pair(n1, n2)
                }
            }
        }
        val ret = Pair(1,2)
        return ret
    }

    fun findTripleForSum(numbers: List<Int>, targetSum: Int): Triple<Int, Int, Int> {
        val numbers2 = numbers.toList()
        val numbers3 = numbers.toList()
        var calcs = 0
        numbers.forEach{
                n1 ->
            if(n1 < targetSum) {
                numbers2.forEach { n2 ->
                    if (n1 + n2 < targetSum) {
                        numbers3.forEach { n3 ->
                            calcs++
                            if ((n1 + n2 + n3) == targetSum) {
                                println("We used $calcs calculations")
                                return Triple(n1, n2, n3)
                            }
                        }
                    }
                }
            }
        }
        val ret = Triple(1,2, 3)
        return ret
    }
}
