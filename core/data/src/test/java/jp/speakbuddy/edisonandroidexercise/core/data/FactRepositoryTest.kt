package jp.speakbuddy.edisonandroidexercise.core.data

import io.mockk.coEvery
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepositoryImpl
import jp.speakbuddy.edisonandroidexercise.core.datastore.Fact
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
            factDataStore.saveFact(FACT_FROM_API, 20)
        } returns Unit
        factRepository = FactRepositoryImpl(
            factApi = factApi,
            factDataStore = factDataStore
        )
        assertTrue(factRepository.getFact().isSuccess)
        assertEquals(factRepository.getFact().getOrNull(), BaseFact.Fact(FACT_FROM_API, 20))
    }

    @Test
    fun getFact_002() = runTest {
        coEvery {
            factApi.getFact()
        } returns FactResponse(fact = "", 0)
        coEvery {
            factDataStore.saveFact(FACT_FROM_API, 0)
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
        val testList = mutableListOf<Fact>()
        for (i in 0 until 5) {
            Fact.newBuilder().setText("$i: $FACT_FROM_LOCAL").setLength(i).build()
        }
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
            assertEquals(fact.text, testList[index].text)
            assertEquals(fact.length, testList[index].length)
        }
    }

    @Test
    fun getSavedFacts_002() = runTest {
        val testList = mutableListOf<Fact>()
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