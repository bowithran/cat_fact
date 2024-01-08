package jp.speakbuddy.edisonandroidexercise.feature.fact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.core.domain.GetFactUseCase
import jp.speakbuddy.edisonandroidexercise.core.model.FactUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val getFactUseCase: GetFactUseCase
) : ViewModel() {
    private val _factUiState = mutableStateOf(FactUiState.DEFAULT)
    val factUiState: State<FactUiState>
        get() = _factUiState

    private val _localFacts = mutableListOf<FactUiState>()
    val localFacts: List<FactUiState>
        get() = _localFacts

    fun updateFact(completion: () -> Unit) = viewModelScope.launch {
        try {
            _factUiState.value = getFactUseCase.getFactState()
        } catch (e: Exception) {
            "something went wrong. error = ${e.message}"
        }.also {
            completion()
        }
    }

    fun getLocalFacts() = viewModelScope.launch {
        try {
            _localFacts.apply {
                clear()
                addAll(getFactUseCase.getAllLocalFacts())
            }
        } catch (e: Exception) {
            "something went wrong. error = ${e.message}"
        }
    }
}
