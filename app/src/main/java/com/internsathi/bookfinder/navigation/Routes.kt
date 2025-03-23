package com.internsathi.bookfinder.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Favourite

@Serializable
data class Detail(val id:String)
