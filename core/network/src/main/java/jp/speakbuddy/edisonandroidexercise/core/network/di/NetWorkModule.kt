package jp.speakbuddy.edisonandroidexercise.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.core.network.retrofit.FactApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetWorkModule {

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://catfact.ninja/")
        .addConverterFactory(
            Json.asConverterFactory(
                "application/json".toMediaType()
            )
        )
        .build()

    @Singleton
    @Provides
    fun provideFactApi(
        retrofit: Retrofit
    ): FactApi = retrofit.create(FactApi::class.java)
}