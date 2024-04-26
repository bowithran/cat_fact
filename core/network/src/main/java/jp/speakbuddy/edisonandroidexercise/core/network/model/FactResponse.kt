package jp.speakbuddy.edisonandroidexercise.core.network.model

import jp.speakbuddy.edisonandroidexercise.core.model.BaseFact
import kotlinx.serialization.Serializable

@Serializable
data class FactResponse(
    val fact: String,
    val length: Int
)

fun FactResponse.toFact(): BaseFact {
    return if (fact.isEmpty()) {
        BaseFact.Empty
    } else {
        BaseFact.Fact(fact, length)
    }
}