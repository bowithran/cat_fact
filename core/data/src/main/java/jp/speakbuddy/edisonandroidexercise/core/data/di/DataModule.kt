package jp.speakbuddy.edisonandroidexercise.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepositoryImpl
import jp.speakbuddy.edisonandroidexercise.core.datastore.FactDataStore
import jp.speakbuddy.edisonandroidexercise.core.network.retrofit.FactApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Singleton
    @Provides
    fun provideFactRepository(
        factApi: FactApi,
        factDataStore: FactDataStore,
    ): FactRepository {
        return FactRepositoryImpl(
            factApi,
            factDataStore,
        )
    }
}