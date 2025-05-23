package com.internsathi.bookfinder.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.internsathi.bookfinder.data.network.BooksApiService
import com.internsathi.bookfinder.data.local.FavouriteBookDao
import com.internsathi.bookfinder.data.local.FavouriteBooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
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

    //database instance
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context : Context) : FavouriteBooksDatabase {
        return Room.
                databaseBuilder(context = context,
                    FavouriteBooksDatabase::class.java,
                    "favourite_books")
                    .build()
    }

    //Dao ko instance
    @Provides
    @Singleton
    fun provideFavouriteBookDao(favouriteBooksDatabase: FavouriteBooksDatabase) : FavouriteBookDao {
        return favouriteBooksDatabase.favouriteBookDao()
    }
}