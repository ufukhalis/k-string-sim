package io.github.ufukhalis.kstringsim.strategy

import io.github.ufukhalis.kstringsim.CompareResult

class JaroStrategy : Strategy{
    override fun compare(source: String, target: String): CompareResult {
        val sourceLength = source.length
        val targetLength = target.length

        if (sourceLength == 0 && targetLength == 0) {
            return CompareResult.Jaro(1.0, 0, 0)
        }

        if (sourceLength == 0 || targetLength == 0) {
            return CompareResult.Jaro(0.0, 0, 0)
        }

        val matchDistance = maxOf(source.length, target.length) / 2 - 1

        val sourceMatches = BooleanArray(sourceLength)
        val targetMatches = BooleanArray(targetLength)

        var matches = 0
        for (i in 0 until sourceLength) {
            val start = maxOf(0, i - matchDistance)
            val end = minOf(targetLength - 1, i + matchDistance) + 1

            for (j in start until end) {
                if (source[i] == target[j] && !targetMatches[j]) {
                    sourceMatches[i] = true
                    targetMatches[j] = true
                    matches++
                    break
                }
            }
        }

        if (matches == 0) {
            return CompareResult.Jaro(0.0, 0, 0)
        }

        val transpositions = calculateTranspositions(source, target, sourceMatches, targetMatches)

        val result = (1.0 / 3.0) * (
                matches.toDouble() / sourceLength +
                matches.toDouble() / targetLength +
                (matches - transpositions).toDouble() / matches
        )

        return CompareResult.Jaro(result, transpositions, matches)
    }

    private fun calculateTranspositions(source: String,
                                        target:String,
                                        sourceMatches: BooleanArray,
                                        targetMatches: BooleanArray): Int {
        val sourceMatched = sourceMatches.indices.filter { sourceMatches[it] }.map { source[it] }
        val targetMatched = targetMatches.indices.filter { targetMatches[it] }.map { target[it] }

        var transpositions = 0
        for (i in sourceMatched.indices) {
            if (sourceMatched[i] != targetMatched[i]) {
                transpositions++
            }
        }
        transpositions /= 2

        return transpositions
    }
}
