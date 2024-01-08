package jp.speakbuddy.edisonandroidexercise.core.data

import io.mockk.coEvery
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepositoryImpl
import jp.speakbuddy.edisonandroidexercise.core.datastore.FactDataStore
import jp.speakbuddy.edisonandroidexercise.core.datastore.SavedFacts
import jp.speakbuddy.edisonandroidexercise.core.model.BaseFact
import jp.speakbuddy.edisonandroidexercise.core.network.model.FactResponse
import jp.speakbuddy.edisonandroidexercise.core.network.retrofit.FactApi
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FactRepositoryTest {
    private val factApi = mockk<FactApi>()
    private val factDataStore = mockk<FactDataStore>()

    lateinit var factRepository: FactRepository

    private val FACT_FROM_API = "api: Adult cats only meow for humans"
    private val FACT_FROM_LOCAL = "local: Cats make about 100 different sounds"

    @Test
    fun getFact_001() = runTest {
        coEvery {
            factApi.getFact()
        } returns FactResponse(fact = FACT_FROM_API, 20)
        coEvery {
            factDataStore.saveFact(FACT_FROM_API)
        } returns Unit
        factRepository = FactRepositoryImpl(
            factApi = factApi,
            factDataStore = factDataStore
        )
        assertTrue(factRepository.getFact().isSuccess)
        assertEquals(factRepository.getFact().getOrNull(), BaseFact.Fact(FACT_FROM_API))
    }

    @Test
    fun getFact_002() = runTest {
        coEvery {
            factApi.getFact()
        } returns FactResponse(fact = "", 0)
        coEvery {
            factDataStore.saveFact(FACT_FROM_API)
        } returns Unit
        factRepository = FactRepositoryImpl(
            factApi = factApi,
            factDataStore = factDataStore
        )
        assertTrue(factRepository.getFact().isSuccess)
        assertEquals(factRepository.getFact().getOrNull(), BaseFact.Empty)
    }

    @Test
    fun getSavedFacts_001() = runTest {
        val testList = listOf<String>(
            "1: $FACT_FROM_LOCAL",
            "2: $FACT_FROM_LOCAL",
            "3: $FACT_FROM_LOCAL",
            "4: $FACT_FROM_LOCAL",
            "5: $FACT_FROM_LOCAL",
        ).toMutableList()
        coEvery {
            factDataStore.fact
        } returns flow {
            emit(
                SavedFacts.newBuilder().addAllFact(
                    testList
                ).build()
            )
        }

        factRepository = FactRepositoryImpl(
            factApi = factApi,
            factDataStore = factDataStore
        )

        factRepository.getSavedFacts().forEachIndexed { index, fact ->
            assertEquals(fact.text, testList[index])
        }
    }

    @Test
    fun getSavedFacts_002() = runTest {
        val testList = mutableListOf<String>()
        coEvery {
            factDataStore.fact
        } returns flow {
            emit(
                SavedFacts.newBuilder().addAllFact(
                    testList
                ).build()
            )
        }

        factRepository = FactRepositoryImpl(
            factApi = factApi,
            factDataStore = factDataStore
        )

        assertTrue(factRepository.getSavedFacts().isEmpty())
    }
}