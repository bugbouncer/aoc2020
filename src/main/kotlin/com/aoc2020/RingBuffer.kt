package com.aoc2020

class RingBuffer<T> {
    private var elements = mutableListOf<RbElement<T>>()
    private var current: RbElement<T>? = null

    /**
     * Append element as last element of all elements which have been added so far
     */
    fun append(element: T) {
        append(RbElement(element))
    }

    /**
     * Add element after the current element.
     */
    fun add(element: T) {
        val rbElement = RbElement(element)
        elements.add(rbElement)
        if (current == null) {
            current = rbElement
            rbElement.next = rbElement
        } else {
            rbElement.next = current!!.next
            current!!.next = rbElement
        }
    }

    fun moveCursorTo(element: T) {
        elements.forEach { if (it.data == element) current = it }
    }

    /**
     * move cursor forward and return value of the new current (the former next)
     */
    fun moveCursor(): T? {
        current = current?.next
        return current?.data
    }

    fun popNext(): T? {
        val ret = current?.next?.data
        elements.remove(current?.next)
        return ret
    }

    private fun append(element: RbElement<T>) {
        if (elements.size == 0) {
            element.next = element
            current = element
        } else {
            element.next = elements.last().next
            elements.last().next = element
        }
        elements.add(element)
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
}

private class RbElement<T>(val data: T) {
    var next: RbElement<T> = this
    override fun toString(): String {
        return "RbElement(data=$data)"
    }
}
