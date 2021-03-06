package com.aoc2020

import org.apache.logging.log4j.LogManager

class BagTree {
    var containerColors = mutableSetOf<String>()
    var containerCounter = mutableMapOf<String, MutableMap<String, Int>>()
    val ruleSet = mutableMapOf<String, List<String>>()

    val log = LogManager.getLogger(BagTree::class.java)

    private fun parseRules(rules: List<String>) {
        rules.forEach { rule ->
            val ruleLine = rule.replace("bags", "bag")
            val aha = ruleLine.split(" contain ", ", ")
            // sanity
            if (aha.size < 2) {
                log.atError().log("Could not parse line $rule")
                return
            }

            ruleSet.put(aha[0], getContainableBags(aha[0], aha.subList(1, aha.size)))

        }
    }

    fun findWays(rules: List<String>, wantedBag: String): Int {
        containerColors.clear()
        parseRules(rules)

        findRecursive(ruleSet, "$wantedBag bag")
        return containerColors.size
    }

    private fun findRecursive(rules: Map<String, List<String>>, wanted: String): Int {
        var ret = 0
        val possible = rules.filter { (key, value) -> value.contains(wanted) }
        ret += possible.size
        if (possible.size > 0) {
            val x = possible.keys
            x.forEach { bagColor ->
                containerColors.add(bagColor)
                log.atDebug().log("$wanted can be contained in $bagColor")
                ret += findRecursive(rules, bagColor)
            }

        }
        return ret
    }

    fun countContainables(container: String): Int {
        var ret = 0
        val containables = containerCounter[container]
        if (containables != null) {
            containables.forEach { containable ->
                // contains number of bags of this color
                ret += containable.value
                val c = countContainables(containable.key)
                // plus the number of bags * the number of contained bags
                if (c != 0) ret += (containable.value * c)
            }
        }
        return ret
    }

    private fun getContainableBags(container: String, bags: List<String>): List<String> {
        val ret = mutableListOf<String>()
        bags.forEach() { it ->
            log.atDebug().log("aha: $it")
            val s = it.replace("bags", "bag").replace(".", "")
            val re = "(\\d+)\\s(.*)".toRegex()
            val aha = re.find(s)
            var color = ""
            var num = 0
            if (aha != null && aha.groups.size > 0) {
                num = aha.groups[1]!!.value.toInt()
                color = aha.groups[2]!!.value
            }
            ret.add(color)
            containerCounter.putIfAbsent(container, mutableMapOf())
            if (num > 0) {
                containerCounter.get(container)!!.put(color, num)
            }
        }
        return ret
    }
}
