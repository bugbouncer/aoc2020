package com.aoc2020

import com.aoc2020.OperationType.*
import javax.management.BadAttributeValueExpException

class InstructionProcessor(input: List<String>) {
    private var instructions = mutableListOf<Instruction>()

    init {
        println("Got ${input.size} instructions")
        val re = "(.*)\\s(.*)".toRegex()
        input.forEach {
            val matches = re.find(it)
            if (matches == null || matches.groupValues.size < 2) {
                println("Could not parse instruction $it")
                throw BadAttributeValueExpException(it)
            }
            val op = OperationType.valueOf(matches.groupValues[1].toUpperCase())
            val arg = matches.groupValues[2].toInt()
            instructions.add(Instruction(op, arg))
        }
        if (input.size != instructions.size) {
            println("Less instructions parsed than provided in input")
        }
    }

    fun execute(): Int {
        var accumulator = 0
        var opPointer = 0
        val usedOps = mutableListOf<Int>()

        while (opPointer < instructions.size && !usedOps.contains(opPointer)) {
            usedOps.add(opPointer)
            when (instructions[opPointer].operation) {
                NOP -> opPointer++
                ACC -> {
                    accumulator += instructions[opPointer].argument
                    opPointer++
                }
                JMP -> opPointer += instructions[opPointer].argument
            }
        }
        if (opPointer >= instructions.size) println("------------> Natural End $accumulator")

        return accumulator
    }

    fun findNaturalEnd() {
        println("Find natural end ...")
        val listCopy = mutableListOf<Instruction>()
        listCopy.addAll(instructions)

        var changePointer = 0
        while (changePointer < instructions.size) {
            instructions.clear()
            instructions.addAll(listCopy)
            while ((instructions[changePointer].operation !in listOf(JMP, NOP))) {
                changePointer++
                if (changePointer >= instructions.size) return
            }
            switchOp(changePointer)
            changePointer++

            execute()
        }
    }

    private fun switchOp(opNumber: Int) {
        val i = instructions[opNumber]
        if (i.operation == NOP) instructions[opNumber] = Instruction(JMP, i.argument)
        if (i.operation == JMP) instructions[opNumber] = Instruction(NOP, i.argument)
    }
}


data class Instruction(val operation: OperationType, val argument: Int)

enum class OperationType {
    NOP, ACC, JMP
}
