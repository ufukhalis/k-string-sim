package io.github.ufukhalis.kstringsim

import io.github.ufukhalis.kstringsim.strategy.StrategyType
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class KStringSimTests : ShouldSpec() {

    init {
        context("Jaro Distance strategy") {
            should("find Jaro distance if values are same") {
                val result = KStringSim.Builder()
                    .values("hello", "hello")
                    .strategy(StrategyType.JARO_DISTANCE)
                    .compare()

                result shouldBe CompareResult.Jaro(1.0, 0, 5)
            }

            should("find Jaro distance if values are different") {
                val result = KStringSim.Builder()
                    .values("Hello", "Hella")
                    .strategy(StrategyType.JARO_DISTANCE)
                    .compare()

                result shouldBe CompareResult.Jaro(0.8666666666666667, 0, 4)
            }

            should("find Jaro distance if values are completely different") {
                val result = KStringSim.Builder()
                    .values("test", "brook")
                    .strategy(StrategyType.JARO_DISTANCE)
                    .compare()

                result shouldBe CompareResult.Jaro(0.0, 0, 0)
            }

            should("find Jaro distance if values has different order") {
                val result = KStringSim.Builder()
                    .values("test", "tset")
                    .strategy(StrategyType.JARO_DISTANCE)
                    .compare()

                result shouldBe CompareResult.Jaro(0.9166666666666666, 1, 4)
            }
        }

        context("Levenshtein strategy") {
            should("find Levenshtein distance if values are same") {
                val result = KStringSim.Builder()
                    .values("Hello", "Hello")
                    .strategy(StrategyType.LEVENSHTEIN)
                    .compare()

                result shouldBe CompareResult.Levenshtein(1.0, 0)
            }

            should("find Levenshtein distance if values are different") {
                val result = KStringSim.Builder()
                    .values("Hello", "Hella")
                    .strategy(StrategyType.LEVENSHTEIN)
                    .compare()

                result shouldBe CompareResult.Levenshtein(0.8, 1)
            }

            should("find Levenshtein distance if values are completely different") {
                val result = KStringSim.Builder()
                    .values("test", "brook")
                    .strategy(StrategyType.LEVENSHTEIN)
                    .compare()

                result shouldBe CompareResult.Levenshtein(0.0, 5)
            }
        }
    }
}
