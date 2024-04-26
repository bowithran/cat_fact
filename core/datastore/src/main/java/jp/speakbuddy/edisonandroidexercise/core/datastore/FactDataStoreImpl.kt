package jp.speakbuddy.edisonandroidexercise.core.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FactDataStoreImpl @Inject constructor(
    private val store: DataStore<SavedFacts>
): FactDataStore {
    override val fact: Flow<SavedFacts> = store.data

    override suspend fun saveFact(text: String, length: Int) {
        store.updateData {
            it.toBuilder()
                .addFact(
                    Fact.newBuilder()
                        .setText(text)
                        .setLength(length)
                    .build()
                )
                .build()
        }
    }
}