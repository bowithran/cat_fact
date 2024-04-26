package jp.speakbuddy.edisonandroidexercise.core.datastore

import kotlinx.coroutines.flow.Flow

interface FactDataStore {
    val fact: Flow<SavedFacts>
    suspend fun saveFact(text: String, length: Int)
}