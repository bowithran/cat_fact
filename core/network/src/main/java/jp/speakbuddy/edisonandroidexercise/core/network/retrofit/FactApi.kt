package jp.speakbuddy.edisonandroidexercise.core.network.retrofit

import jp.speakbuddy.edisonandroidexercise.core.network.model.FactResponse
import retrofit2.http.GET

interface FactApi {
    @GET("fact")
    suspend fun getFact(): FactResponse
}