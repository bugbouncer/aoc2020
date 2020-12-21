package com.aoc2020

class FoodAlergeneCalculator {
    val allergeneLikeliness = mutableMapOf<String, MutableMap<Ingredient, Int>>()
    val ingredientCount = mutableMapOf<Ingredient, Int>()

    fun addFood(food: Food) {
        food.allergenes.forEach { allerg ->
            allergeneLikeliness.putIfAbsent(allerg, mutableMapOf())
            food.ingredients.forEach { it ->
                allergeneLikeliness[allerg]!!.putIfAbsent(it, 0)
                allergeneLikeliness[allerg]!![it] = allergeneLikeliness[allerg]?.get(it)!! + 1
            }
        }
        food.ingredients.forEach {
            ingredientCount.putIfAbsent(it, 0)
            ingredientCount[it] = ingredientCount[it]!! + 1
        }
    }

    fun countIngredientsWoAlergene(): Int {
        allergeneLikeliness.forEach {
            var high = 0
            it.value.forEach {
                if (it.value > high) high = it.value
            }
            println("Highest for ${it.key}: $high")
            it.value.filter { it.value == high }.forEach { ing -> ingredientCount.remove(ing.key) }
        }
        var ret = 0;
        ingredientCount.filter { it.key.containsAllergene == false }.forEach { ret += it.value }
        return ret
    }
}

class Food(definition: String) {
    val splitRe = "(.*) \\(contains (.*)\\)".toRegex()
    val ingredients = mutableListOf<Ingredient>()
    val allergenes = mutableListOf<String>()

    init {
        val mr = splitRe.find(definition)
        val ings = mr!!.groups[1]!!.value
        val allergs = mr!!.groups[2]!!.value
        ings.split(" ").forEach {
            ingredients.add(Ingredient(it))
        }
        allergs.split(", ").forEach {
            allergenes.add(it)
        }
    }
}

class Ingredient(val name: String) {
    var containsAllergene: Boolean = false
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ingredient

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }


}
