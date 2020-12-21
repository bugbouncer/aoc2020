package com.aoc2020

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class FoodAlergeneCalculatorTest {
    @Test
    fun test() {
        var testData = File("src/test/resources/p21-test-input.txt").readLines()
        var fac = FoodAlergeneCalculator()
        testData.forEach {
            fac.addFood(Food(it))
        }
        var ret = fac.countIngredientsWoAlergene()
        assertEquals(5, ret)

        testData = File("src/test/resources/p21-input.txt").readLines()
        fac = FoodAlergeneCalculator()
        testData.forEach {
            fac.addFood(Food(it))
        }
        ret = fac.countIngredientsWoAlergene()
        assertEquals(2412, ret) // day 21 part 1
    }
}
