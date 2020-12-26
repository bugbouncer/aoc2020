package com.aoc2020

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.LoggerContext
import org.apache.logging.log4j.core.config.Configuration
import org.apache.logging.log4j.core.config.LoggerConfig
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class CupGameTest {
    @Test
    fun testPart1() {
        // LogManager.getLogger("a").level = (Level.DEBUG)
        val ctx: LoggerContext = LogManager.getContext(false) as LoggerContext
        val config: Configuration = ctx.configuration
        val loggerConfig: LoggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME)
        loggerConfig.level = Level.DEBUG
        ctx.updateLoggers()
        var cg = CupGame("389125467")
        cg.play(10)
        var ret = cg.getResultPart1()
        assertEquals("92658374", ret)

        cg = CupGame("389125467")
        cg.play(100)
        ret = cg.getResultPart1()
        assertEquals("67384529", ret)


        // puzzle day 23 - part 1
        cg = CupGame("463528179")
        cg.play(100)
        ret = cg.getResultPart1()
        assertEquals("52937846", ret) // result day 23 part 1


    }

    @Test
    fun testPart2Sample() {
        var cg = CupGame("389125467")
        cg.generateData()
        cg.play(10000000)
        val ret = cg.getResultPart2()
        val expected = 934001L * 159792L
        assertEquals(expected, ret)
    }

    @Test
    fun testPart2() {
        // part 2
        var cg = CupGame("463528179")
        cg.generateData()
        cg.play(10000000)
        val ret = cg.getResultPart2()
        assertEquals(715758780, ret)
    }
}
