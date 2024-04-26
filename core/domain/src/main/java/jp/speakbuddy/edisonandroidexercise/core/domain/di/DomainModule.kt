package jp.speakbuddy.edisonandroidexercise.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.core.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.core.domain.GetFactUseCase
import jp.speakbuddy.edisonandroidexercise.core.domain.GetFactUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DomainModule {

    @Singleton
    @Provides
    fun provideGetFactUseCase(
        factRepository: FactRepository
    ): GetFactUseCase {
        return GetFactUseCaseImpl(
            factRepository
        )
    }
}