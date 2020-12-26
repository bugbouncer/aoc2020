package com.aoc2020

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RingBufferTest {
    @Test
    fun testBuffer() {
        val rb = RingBuffer<Char>()
        rb.append('a')
        rb.append('b')
        rb.append('c')
        rb.append('d')
        val ret = rb.allValuesAsList()
        var sb = ""
        ret.forEach { sb += it }
        assertEquals("abcd", sb)
        val ret2 = rb.moveCursor()
        assertEquals('b', ret2)
        val ret3 = rb.popNext()
        assertEquals('c', ret3)
        val ret4 = rb.allValuesAsList()
        sb = ""
        ret4.forEach { sb += it }
        assertEquals("bda", sb)
    }

    @Test
    fun testMove() {
        val rb = RingBuffer<Char>()
        rb.append('A')
        rb.append('x'); rb.append('y'); rb.append('z')
        rb.append('B');rb.append('C')

        val before = rb.allValuesAsList()
        var sb = ""
        before.forEach { sb += it }
        assertEquals("AxyzBC", sb)

        rb.moveElements(3, 'A', 'B')

        val after = rb.allValuesAsList()
        sb = ""
        after.forEach { sb += it }
        assertEquals("ABxyzC", sb)
    }
}
