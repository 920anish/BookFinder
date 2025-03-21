package com.internsathi.bookfinder

import com.internsathi.bookfinder.data.Books
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooksByCategory(
        @Query("q") query:String,
        @Query("maxResults") maxResult:Int,
        @Query("API_KEY") key:String,
    ) : Books

}