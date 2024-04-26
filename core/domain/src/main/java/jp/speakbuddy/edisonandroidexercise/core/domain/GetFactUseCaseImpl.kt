package jp.speakbuddy.edisonandroidexercise.core.domain

import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.core.model.FactUiState
import jp.speakbuddy.edisonandroidexercise.core.model.toFactUiState
import jp.speakbuddy.edisonandroidexercise.core.model.toNullableFaceUiState
import javax.inject.Inject

class GetFactUseCaseImpl @Inject constructor(
    private val factRepository: FactRepository,
) : GetFactUseCase {

    override suspend fun getFactState(): FactUiState {
        return factRepository.getFact()
            .fold(
                onSuccess = {
                    it.toNullableFaceUiState() ?: getRandomSavedFacts()
                },
                onFailure = {
                    getRandomSavedFacts()
                }
            )
    }

    override suspend fun getAllLocalFacts(): List<FactUiState> {
        return factRepository.getSavedFacts()
            .distinctBy { it }
            .map {
                it.toFactUiState()
            }
    }

    private suspend fun getRandomSavedFacts(): FactUiState {
        return factRepository.getSavedFacts().takeIf {
            it.isEmpty().not()
        }?.let { list ->
            (0 until list.count()).random().let {
                list[it].toFactUiState()
            }
        } ?: throw Exception("remote and local all failed...")
    }
}