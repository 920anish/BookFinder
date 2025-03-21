package com.internsathi.bookfinder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
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
}