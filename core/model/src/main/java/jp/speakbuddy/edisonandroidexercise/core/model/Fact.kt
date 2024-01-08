package jp.speakbuddy.edisonandroidexercise.core.model

sealed class BaseFact {
    data class Fact(
        val text: String,
    ): BaseFact() {
        val length: Int = text.length
    }

    object Empty: BaseFact()
}

fun BaseFact.Fact.toFactState(): FactState = FactState(
    fact = this.text,
    length = this.length,
    isMultipleCats = this.text.contains("cats"),
    isShowLength = this.length > 100,
)

fun BaseFact.toNullableFaceState(): FactState? {
    return when(this) {
        is BaseFact.Empty -> {
            null
        }
        is BaseFact.Fact -> {
            toFactState()
        }
    }
}