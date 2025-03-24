package com.internsathi.bookfinder.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.internsathi.bookfinder.model.FavouriteBook

@Database(
    entities = [FavouriteBook::class],
    version = 1,
    exportSchema = false
)
abstract class FavouriteBooksDatabase : RoomDatabase() {

    abstract fun favouriteBookDao() : FavouriteBookDao
}