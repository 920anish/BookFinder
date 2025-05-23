package com.internsathi.bookfinder.data.network

import com.internsathi.bookfinder.model.Books
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksApiService: BooksApiService
) {
    private val API_KEY = "AIzaSyA9pLSdd8uUKi9_6q78qdkMLD3V0UrjiMg"

    suspend fun getBooksByCategory(category:String , maxResults:Int ) : Books {

        return booksApiService.getBooksByCategory("subject:$category" , maxResults , API_KEY)
    }

}