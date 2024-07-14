package io.github.ufukhalis.kstringsim.strategy

enum class StrategyType {
    LEVENSHTEIN,
    JARO_DISTANCE,
    JARO_WINKLER;

    companion object {
        fun StrategyType.toStrategy(): Strategy {
            return when (this) {
                LEVENSHTEIN -> LevenshteinStrategy()
                JARO_DISTANCE -> JaroStrategy()
                JARO_WINKLER -> JaroWinklerStrategy()
                else -> throw IllegalArgumentException("Invalid strategy type")
            }
        }
    }
}
