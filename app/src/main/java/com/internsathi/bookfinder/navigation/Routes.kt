package com.internsathi.bookfinder.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Favourite

@Serializable
data class Detail(val id:String  , val title  : String , val authors: List<String>? , val publishedDate : String , val imageUrl: String? , val description:String?  )

@Serializable
object FavouriteDetail