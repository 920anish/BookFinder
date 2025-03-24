package com.internsathi.bookfinder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourite_books")
data class FavouriteBook (

    @PrimaryKey @ColumnInfo(name = "id") val id:String,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "authors") val authors:String?,
    @ColumnInfo(name = "imageUrl")val imageUrl:String?,
    @ColumnInfo(name = "publishedDate")val publishedDate:String,
    @ColumnInfo(name = "description") val description:String,

    )