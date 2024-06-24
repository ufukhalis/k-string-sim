package io.github.ufukhalis.kstringsim

import io.github.ufukhalis.kstringsim.strategy.StrategyType
import io.github.ufukhalis.kstringsim.strategy.StrategyType.Companion.toStrategy

class KStringSim private constructor(
    private val source: String,
    private val target: String,
    private val strategyType: StrategyType
) {

    class Builder {
        var source: String? = null
        var target: String? = null

        fun values(value1: String, value2: String): StrategyBuilder {
            this.source = value1
            this.target = value2
            return StrategyBuilder(this)
        }
    }

    class StrategyBuilder(private val builder: Builder) {
        private var strategyType: StrategyType? = null

        fun strategy(strategyType: StrategyType): KStringSim {
            this.strategyType = strategyType
            return KStringSim(builder.source!!, builder.target!!, strategyType)
        }
    }

    fun compare(): CompareResult {
        return strategyType.toStrategy().compare(source, target)
    }
}
