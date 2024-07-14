package io.github.ufukhalis.kstringsim.strategy

import io.github.ufukhalis.kstringsim.CompareResult

class JaroWinklerStrategy : Strategy {

    override fun compare(source: String, target: String): CompareResult {
        val jaro = JaroStrategy().compare(source, target)

        val commonPrefix = source.zip(target)
            .takeWhile { it.first == it.second }
            .count()
            .coerceAtLeast(4)
        val scalingFactor = 0.1

        val jaroWinklerScore = jaro.score + commonPrefix * scalingFactor * (1 - jaro.score)

        return CompareResult.JaroWinkler(
            score = jaroWinklerScore,
            jaro = jaro
        )
    }

}
