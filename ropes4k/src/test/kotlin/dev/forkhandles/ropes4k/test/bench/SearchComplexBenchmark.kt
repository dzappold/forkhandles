/*
 * Copyright (C) 2024 James Richardson
 *  - Originally Copyright (C) 2007 Amin Ahmad.
 * Licenced under GPL
 */

package dev.forkhandles.ropes4k.test.bench

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
open class SearchComplexBenchmark {

    val toFind = "Bob was very cheerful with them, and spoke pleasantly to"

    val complex = Complex()

    val string = complex.s
    val stringBuilder = complex.sb
    val rope = complex.r

    val expected = string.indexOf(toFind)

    @Benchmark
    fun string(): Int {
        val indexOf = string.indexOf(toFind)
        require(indexOf == expected) { "Checksum mismatch, expected $expected, was $indexOf" }
        return indexOf
    }

    @Benchmark
    fun stringbuilder(): Int {
        val indexOf = stringBuilder.indexOf(toFind)
        require(indexOf == expected) { "Checksum mismatch, expected $expected, was $indexOf" }
        return indexOf
    }

    @Benchmark
    fun rope(): Int {
        val indexOf = rope.indexOf(toFind)
        require(indexOf == expected) { "Checksum mismatch, expected $expected, was $indexOf" }
        return indexOf
    }
}
