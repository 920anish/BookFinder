package com.internsathi.bookfinder

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_preferences")


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    //retrofit ko instance
    fun provideRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(
            GsonConverterFactory.create())
            .build()

    }

    //API service instance
    @Provides
    @Singleton
    fun provideBooksApiService (retrofit : Retrofit) : BooksApiService {
        return retrofit.create(BooksApiService::class.java)

    }


    //datastore
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}