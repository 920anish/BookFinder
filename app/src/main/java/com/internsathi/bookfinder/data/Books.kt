package com.internsathi.bookfinder.data

import kotlinx.serialization.Serializable

@Serializable
data class  Books(
    val items: List<Book>
)


@Serializable
data class Book (
    val id:String,
    val volumeInfo: VolumeInfo
)


@Serializable
data class VolumeInfo(
    val title:String,
    val publishedDate:String,
    val authors: List<String> ? = null,
    val imageLinks : ImageLinks ? = null
)

@Serializable
data class ImageLinks(
    val thumbnail :String ? = null
)