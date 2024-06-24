package io.github.ufukhalis.kstringsim.strategy

import io.github.ufukhalis.kstringsim.CompareResult

class LevenshteinStrategy : Strategy {

    override fun compare(source: String, target: String): CompareResult {

        // create a matrix with the size of value1.length + 1 and value2.length + 1
        val matrix = Array(source.length + 1) { IntArray(target.length + 1) }

        // fill the first row and column with the index
        for (i in 0..source.length) {
            matrix[i][0] = i
        }

        // fill the first row and column with the index
        for (j in 0..target.length) {
            matrix[0][j] = j
        }

        // fill the matrix with the cost of the operations
        // if the compared values are the same then the cost is matrix[i-1][j-1]
        // otherwise the cost is the minimum of the left, top and diagonal cell + 1
        for (i in 1..source.length) {
            for (j in 1..target.length) {
                val cost = if (source[i - 1] == target[j - 1]) {
                    matrix[i-1][j-1]
                } else {
                    minOf(
                        matrix[i - 1][j],
                        matrix[i][j - 1],
                        matrix[i - 1][j - 1]
                    ) + 1
                }
                matrix[i][j] = cost
            }
        }

        val distance = matrix[source.length][target.length]
        val normalizedValue = 1 - (distance.toDouble() / maxOf(source.length, target.length))
        return CompareResult.Levenshtein(
            score = normalizedValue,
            distance = distance
        )
    }

}
