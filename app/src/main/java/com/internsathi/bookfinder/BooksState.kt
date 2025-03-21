package com.internsathi.bookfinder

import com.internsathi.bookfinder.data.Books

sealed interface BooksUiState {
    data class Success(val books:Books) : BooksUiState
    data object Error: BooksUiState
    data object Loading: BooksUiState
}