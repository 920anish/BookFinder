package com.internsathi.bookfinder.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.internsathi.bookfinder.model.FavouriteBook
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteBookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favouriteBook: FavouriteBook)

    @Delete
    suspend fun delete(favouriteBook: FavouriteBook)

    @Query("SELECT * FROM favourite_books Where id = :id ")
    fun getFavouriteBookById(id:String) : Flow<FavouriteBook>

    @Query("SELECT * FROM FAVOURITE_BOOKS ")
    fun getAllFavouriteBooks() : Flow<List<FavouriteBook>>

}