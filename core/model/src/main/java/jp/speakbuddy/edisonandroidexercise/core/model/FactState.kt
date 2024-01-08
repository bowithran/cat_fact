package jp.speakbuddy.edisonandroidexercise.core.model

data class FactState(
    val fact: String,
    val length: Int,
    val isMultipleCats: Boolean,
    val isShowLength: Boolean,
) {
    companion object {
        val DEFAULT = FactState(
            "", 0, false, false,
        )
    }
}