package jp.speakbuddy.edisonandroidexercise.core.data.repository

import jp.speakbuddy.edisonandroidexercise.core.model.BaseFact

interface FactRepository {
    suspend fun getFact(): Result<BaseFact>
    suspend fun getSavedFacts(): List<BaseFact.Fact>

}