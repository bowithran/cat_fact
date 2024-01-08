package jp.speakbuddy.edisonandroidexercise.feature.fact

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking

class FactViewModel : ViewModel() {
    fun updateFact(completion: () -> Unit): String =
        runBlocking {
            try {
                //todo akisa
//                FactServiceProvider.provide().getFact().fact
                ""
            } catch (e: Throwable) {
                "something went wrong. error = ${e.message}"
            }.also { completion() }
        }
}
