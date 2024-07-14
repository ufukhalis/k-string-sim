# Kotlin String Similarity

---

This is string similarity library for Kotlin which inspired from other String Similarity libraries.
Currently, it contains the following algorithms:

- Levenshtein Distance
- Jaro Distance
- Jaro Winkler Distance

## Usage

---

Add gradle dependency to your `build.gradle` file

```gradle
implementation 'io.github.ufukhalis:k-string-sim:0.1.0'
```

After adding the dependency, you can use the library like below:

```kotlin
val result = KStringSim.Builder()
    .values("hello", "hello")
    .strategy(StrategyType.JARO_WINKLER)
    .compare()
```

Above code will return the similarity score between two strings. You can also use `StrategyType.LEVENSHTEIN` and `StrategyType.JARO` strategies.
