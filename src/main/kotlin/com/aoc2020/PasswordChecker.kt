package com.aoc2020

class PasswordChecker {
    fun getNumberOfValidPasswords(input : List<String>, policy: String = "shopkeeper"): Int {
        var ret = 0
        // 8-11 c: ccctccccccw
        val re = "(\\d+)-(\\d+)\\s([a-zA-Z]):\\s(.*)".toRegex()
        input.forEach {
            i -> val matches = re.find(i)
            val min = matches?.groups?.get(1)?.value
            val max = matches?.groups?.get(2)?.value
            val wc = matches?.groups?.get(3)?.value
            val pwd = matches?.groups?.get(4)?.value
            val wchar = wc?.get(0)

            if(policy == "shopkeeper") {
                val occurances = pwd?.count( { c: Char -> c == wchar}) ?: 0
                if (min != null && max != null) {
                    if( occurances >= min.toInt() && occurances <= max.toInt()) {
                        ret++
                    }
                }
            }
            else if (policy == "tobogan") {
                if( min != null && max != null ) {
                    val m1 = pwd?.get(min.toInt() - 1)?.equals(wchar)

                    val m2 = pwd?.get(max.toInt() - 1)?.equals(wchar)
                    if (m1 != null && m2 != null) {
                        if( m1 xor m2 ) {
                            ret++
                        }
                    }
                }
            }
        }
        return ret
    }
}
