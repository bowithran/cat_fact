package jp.speakbuddy.edisonandroidexercise.core.domain

import jp.speakbuddy.edisonandroidexercise.core.model.FactUiState

interface GetFactUseCase {
    /**
     * - get a new fact from api
     *  - if failed get random one from local data
     * ※ Don't know exactly from where
     */
    suspend fun getFactState(): FactUiState

    /**
     * - get all local saved facts
     */
    suspend fun getAllLocalFacts(): List<FactUiState>
}