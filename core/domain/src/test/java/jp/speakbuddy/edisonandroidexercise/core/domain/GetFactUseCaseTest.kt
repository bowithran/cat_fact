package jp.speakbuddy.edisonandroidexercise.core.domain

import io.mockk.coEvery
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.core.model.BaseFact
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class GetFactUseCaseTest {
    private val factRepository = mockk<FactRepository>()

    lateinit var useCase: GetFactUseCase

    private val FACT_FROM_API = "api: Adult cats only meow for humans"
    private val FACT_FROM_LOCAL = "local: Cats make about 100 different sounds"

    @Test
    fun getFactState_001() = runTest {
        coEvery {
            factRepository.getFact()
        } returns Result.success(BaseFact.Fact(FACT_FROM_API))
        useCase = GetFactUseCaseImpl(factRepository)
        assertEquals(useCase.getFactState().fact, FACT_FROM_API)
    }

    @Test
    fun getFactState_002() = runTest {
        val testList = listOf<BaseFact.Fact>(
            BaseFact.Fact("getFactState_002 test: $FACT_FROM_LOCAL"),
        )
        coEvery {
            factRepository.getFact()
        } returns Result.success(BaseFact.Empty)
        coEvery {
            factRepository.getSavedFacts()
        } returns testList
        useCase = GetFactUseCaseImpl(factRepository)
        assertEquals(useCase.getFactState().fact, "getFactState_002 test: $FACT_FROM_LOCAL")
    }

    @Test
    fun getFactState_003() = runTest {
        val testList = listOf<BaseFact.Fact>(
            BaseFact.Fact("getFactState_003 test: $FACT_FROM_LOCAL"),
        )
        coEvery {
            factRepository.getFact()
        } returns Result.failure(Exception())
        coEvery {
            factRepository.getSavedFacts()
        } returns testList
        useCase = GetFactUseCaseImpl(factRepository)
        assertEquals(useCase.getFactState().fact, "getFactState_003 test: $FACT_FROM_LOCAL")
    }

    @Test
    fun getAllLocalFacts_001() = runTest {
        val testList = listOf<BaseFact.Fact>(
            BaseFact.Fact("getAllLocalFacts_001 test: $FACT_FROM_LOCAL"),
            BaseFact.Fact("getAllLocalFacts_001 test: $FACT_FROM_LOCAL"),
            BaseFact.Fact("getAllLocalFacts_001 test: $FACT_FROM_LOCAL"),
        )
        coEvery {
            factRepository.getSavedFacts()
        } returns testList
        useCase = GetFactUseCaseImpl(factRepository)
        assertEquals(useCase.getAllLocalFacts().size, 1)
        assertEquals(useCase.getAllLocalFacts()[0].fact, "getAllLocalFacts_001 test: $FACT_FROM_LOCAL")
    }
}