package jp.speakbuddy.edisonandroidexercise.core.model

import org.junit.Assert.assertEquals
import org.junit.Test

class FactKtTest {

    @Test
    fun toFactUiState() {
        val testList = mutableListOf<BaseFact.Fact>()
        val testAnswerList = mutableListOf<FactUiState>()
        for (i in 0 until 10) {
            testList.add(BaseFact.Fact(text = "fact$i", length = i))
            testAnswerList.add(FactUiState(fact = "fact$i", length = i, isMultipleCats = false, isShowLength = false))
        }
        testList.map {
            it.toFactUiState()
        }

        testAnswerList.forEachIndexed { index, uiState ->
            assertEquals(uiState.fact, testList[index].text)
            assertEquals(uiState.length, testList[index].length)
            assertEquals(uiState.isShowLength, false)
            assertEquals(uiState.isMultipleCats, false)
        }

        val testFactShowLength = BaseFact.Fact(text = "The oldest cat to give birth was Kitty who, at the age of 30, gave birth to two kittens. During her life, she gave birth to 218 kittens.", length = 136)
        testFactShowLength.toFactUiState().also { uiState ->
            assertEquals(uiState.fact, testFactShowLength.text)
            assertEquals(uiState.length, testFactShowLength.length)
            assertEquals(uiState.isShowLength, true)
            assertEquals(uiState.isMultipleCats, false)
        }

        val testFactShowCats = BaseFact.Fact(text = "Many cats cannot properly digest cow's milk. Milk and milk products give them diarrhea.", length = 87)
        testFactShowCats.toFactUiState().also { uiState ->
            assertEquals(uiState.fact, testFactShowCats.text)
            assertEquals(uiState.length, testFactShowCats.length)
            assertEquals(uiState.isShowLength, false)
            assertEquals(uiState.isMultipleCats, true)
        }
    }

    @Test
    fun toNullableFaceUiState() {
        val testEmpty = BaseFact.Empty
        assertEquals(testEmpty.toNullableFaceUiState(), null)
    }
}