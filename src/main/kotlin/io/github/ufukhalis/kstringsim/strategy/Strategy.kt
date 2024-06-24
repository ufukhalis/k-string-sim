package io.github.ufukhalis.kstringsim.strategy

import io.github.ufukhalis.kstringsim.CompareResult

interface Strategy {
    fun compare(source: String, target: String): CompareResult
}
