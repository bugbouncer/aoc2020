package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class BagTreeTest {

    @Test
    fun countBoughsTest() {
        var bt = BagTree()
        var testData = File("src/test/resources/p7-test-input.txt").readLines()
        var ret = bt.findWays(testData, "shiny gold")
        assertEquals(4, ret)
        ret = bt.countContainables("shiny gold bag")
        assertEquals(32, ret)

        bt = BagTree()
        testData = File("src/test/resources/p7-input.txt").readLines()
        ret = bt.findWays(testData, "shiny gold")
        assertEquals(355, ret)
        ret = bt.countContainables("shiny gold bag")
        assertEquals(5312, ret)
    }
}
