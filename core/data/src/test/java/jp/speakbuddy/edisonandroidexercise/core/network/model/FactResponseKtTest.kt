package jp.speakbuddy.edisonandroidexercise.core.network.model

import jp.speakbuddy.edisonandroidexercise.core.model.BaseFact
import org.junit.Assert.*

import org.junit.Test

class FactResponseKtTest {

    @Test
    fun toFact() {
        val testList = mutableListOf<FactResponse>()
        val testAnswerList = mutableListOf<BaseFact.Fact>()
        for (i in 0 until 10) {
            testList.add(FactResponse(fact = "fact$i", length = i))
            testAnswerList.add(BaseFact.Fact("fact$i", length = i))
        }
        testList.map {
            it.toFact()
        }

        testAnswerList.forEachIndexed { index, fact ->
            assertEquals(fact.text, testList[index].fact)
            assertEquals(fact.length, testList[index].length)
        }

        val testEmpty = FactResponse(fact = "", length = 0)
        assertEquals(testEmpty.toFact(), BaseFact.Empty)
    }
}