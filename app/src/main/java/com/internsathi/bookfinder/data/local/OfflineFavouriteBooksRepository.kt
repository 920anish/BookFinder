package com.internsathi.bookfinder.data.local

import com.internsathi.bookfinder.model.FavouriteBook
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFavouriteBooksRepository @Inject constructor(
    private val favouriteBookDao: FavouriteBookDao
) : FavouriteBooksRepository {

    override suspend fun insertFavouriteBook(favouriteBook: FavouriteBook)  = favouriteBookDao.insert(favouriteBook)

    override suspend fun deleteFavouriteBook(favouriteBook: FavouriteBook) = favouriteBookDao.delete(favouriteBook)

    override fun getFavouriteBookByIdSream(id: String): Flow<FavouriteBook> = favouriteBookDao.getFavouriteBookById(id)

    override fun getAllFavouriteBooksStream(): Flow<List<FavouriteBook>> = favouriteBookDao.getAllFavouriteBooks()

}