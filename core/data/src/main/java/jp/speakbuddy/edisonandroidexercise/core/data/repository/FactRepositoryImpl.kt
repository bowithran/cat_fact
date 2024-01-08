package jp.speakbuddy.edisonandroidexercise.core.data.repository

import jp.speakbuddy.edisonandroidexercise.core.datastore.FactDataStore
import jp.speakbuddy.edisonandroidexercise.core.model.BaseFact
import jp.speakbuddy.edisonandroidexercise.core.network.model.toFact
import jp.speakbuddy.edisonandroidexercise.core.network.retrofit.FactApi
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val factApi: FactApi,
    private val factDataStore: FactDataStore,
) : FactRepository {
    override suspend fun getFact(): Result<BaseFact> {
        return runCatching {
            factApi.getFact().toFact()
        }.onSuccess {
            if (it is BaseFact.Fact) {
                factDataStore.saveFact(it.text)
            }
            Result.success(it)
        }.onFailure {
            Result.failure<Throwable>(it)
        }
    }

    override suspend fun getSavedFacts(): List<BaseFact.Fact> {
        return try {
            factDataStore.fact.first().factList
                .map { BaseFact.Fact(it) }
                .toList()
        } catch (e: Exception) {
            listOf()
        }
    }
}