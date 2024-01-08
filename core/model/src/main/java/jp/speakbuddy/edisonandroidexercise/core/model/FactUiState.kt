package jp.speakbuddy.edisonandroidexercise.core.model

data class FactUiState(
    val fact: String,
    val length: Int,
    val isMultipleCats: Boolean,
    val isShowLength: Boolean,
) {
    companion object {
        val DEFAULT = FactUiState(
            "", 0, false, false,
        )
    }
}