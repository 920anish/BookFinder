package com.internsathi.bookfinder.data

import com.internsathi.bookfinder.model.FavouriteBook
import kotlinx.coroutines.flow.Flow

interface FavouriteBooksRepository  {
    //Dao Ko functions map gareko , to later implement in offline repo

    suspend fun insertFavouriteBook(favouriteBook: FavouriteBook)

    suspend fun deleteFavouriteBook(favouriteBook: FavouriteBook)

    fun getFavouriteBookByIdSream(id:String) : Flow<FavouriteBook>

    fun getAllFavouriteBooksStream() : Flow<List<FavouriteBook>>

}