package com.aoc2020

class RingBuffer<T> {
    var elements = mutableListOf<RbElement<T>>()
    var current: RbElement<T>? = null

    fun append(element: T) {
        append(RbElement(element))
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

class RbElement<T>(val data: T) {
    var next: RbElement<T>? = null
}
