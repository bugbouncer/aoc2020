package com.aoc2020

import java.lang.NullPointerException

class RingBuffer<T> {
    private var elements = mutableMapOf<T, RbElement<T>>()
    private var current: RbElement<T>? = null
    private var last: RbElement<T>? = null

    /**
     * Append element as last element of all elements which have been added so far
     */
    fun append(element: T) {
        append(RbElement(element))
    }

    /**
     * Add element after the current element.
     */
    fun add(value: T) {
        val rbElement = RbElement(value)
        elements.put(value, rbElement)
        if (current == null) {
            current = rbElement
            rbElement.next = rbElement
        } else {
            rbElement.next = current!!.next
            current!!.next = rbElement
        }
    }

    private fun find(element: T): RbElement<T> {
        try {
            return elements[element]!!
        } catch (e: NullPointerException) {
            println("Could not find element $element")
            e.printStackTrace()
        }
        return elements[element]!!
    }

    fun moveElements(count: Int, after : T, destination: T) {
        val rbAfter = find(after)
        val rbDest = find(destination)
        val firstToMove = rbAfter.next
        var lastToMove = rbAfter
        for(i in 1 .. count) {
            lastToMove = lastToMove.next
        }
        rbAfter.next = lastToMove.next
        lastToMove.next = rbDest.next
        rbDest.next = firstToMove
    }

    fun moveCursorTo(element: T) {
        current = elements[element]
    }

    /**
     * move cursor forward and return value of the new current (the former next)
     */
    fun moveCursor(): T? {
        current = current?.next
        return current?.data
    }

    fun getCurrent(): T {
        return current!!.data
    }

    fun moveCursorNext() {
        current = current!!.next
    }

    /**
     * Returns value of next element and moves cursor forward.
     */
    fun getNext(): T? {
        val ret = current?.next?.data
        current = current!!.next
        return ret
    }

    fun popNext(): T? {
        val ret = current?.next?.data
        val next = current?.next
        elements.remove(current?.next?.data)
        current?.next = next!!.next
        return ret
    }

    private fun append(element: RbElement<T>) {
        if(current == null) current = element

        if(last != null) {
            element.next = last!!.next
            last!!.next = element
        } else {
            element.next = element
        }
        elements.put(element.data, element)
        last = element
    }

    /**
     * returns all values, starting with the current.
     * @return empty list if buffer is empty
     */
    fun allValuesAsList(): List<T> {
        val ret = mutableListOf<T>()
        if (current != null) {
            var runner = current
            do {
                if (runner != null) {
                    ret.add(runner.data)
                }
                runner = runner?.next
            } while (runner != current)
        }
        return ret
    }
    fun getLastElement(): T {
        return last!!.data
    }
}

private class RbElement<T>(val data: T) {
    var next: RbElement<T> = this
    override fun toString(): String {
        return "RbElement(data=$data)"
    }
}
