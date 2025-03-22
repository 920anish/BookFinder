package com.internsathi.bookfinder.model

sealed interface BooksUiState {
    data class Success(val books:Books) : BooksUiState
    data object Error: BooksUiState
    data object Loading: BooksUiState
}