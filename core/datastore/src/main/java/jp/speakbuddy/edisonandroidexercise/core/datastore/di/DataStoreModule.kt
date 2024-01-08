package jp.speakbuddy.edisonandroidexercise.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.core.datastore.FactDataStore
import jp.speakbuddy.edisonandroidexercise.core.datastore.FactDataStoreImpl
import jp.speakbuddy.edisonandroidexercise.core.datastore.FactsSerializer
import jp.speakbuddy.edisonandroidexercise.core.datastore.SavedFacts
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providesSavedFactsDataStore(
        @ApplicationContext context: Context,
    ): DataStore<SavedFacts> =
        DataStoreFactory.create(
            serializer = FactsSerializer,
        ) {
            context.dataStoreFile(fileName = "saved_facts.pb")
        }

    @Provides
    @Singleton
    internal fun providesFactDataStore(
        dataStore: DataStore<SavedFacts>
    ): FactDataStore = FactDataStoreImpl(dataStore)
}