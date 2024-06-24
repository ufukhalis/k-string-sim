package io.github.ufukhalis.kstringsim

sealed class CompareResult(open val score: Double) {
    data class Levenshtein(override val score: Double, val distance: Int) : CompareResult(score)
    data class Jaro(override val score: Double, val transpositions: Int, val matches: Int) : CompareResult(score)
}
