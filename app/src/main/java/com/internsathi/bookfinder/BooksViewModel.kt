package com.internsathi.bookfinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _techBooksState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val techBooksState = _techBooksState.asStateFlow()


    private val _comedyBooksState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val comedyBooksState = _comedyBooksState.asStateFlow()


    private val _horrorBooksState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val horrorBooksState = _horrorBooksState.asStateFlow()


    init {
        getAllBooks()
    }


    fun getAllBooks() {
        fetchTechnologyBooks()
        fetchHorrorBooks()
        fetchComedyBooks()
    }

    private fun fetchTechnologyBooks() {
        viewModelScope.launch {
            _techBooksState.value = BooksUiState.Loading
            try {
                val result = booksRepository.getBooksByCategory("technology" , 3)
                _techBooksState.value = BooksUiState.Success(result)
            } catch (_: Exception) {
                _techBooksState.value = BooksUiState.Error
            }
        }
    }

    private fun fetchHorrorBooks() {
        viewModelScope.launch {
            _horrorBooksState.value = BooksUiState.Loading
            try {
                val result = booksRepository.getBooksByCategory("horror" , 7)
                _horrorBooksState.value = BooksUiState.Success(result)
            } catch (_: Exception) {
                _horrorBooksState.value = BooksUiState.Error
            }
        }
    }

    private fun fetchComedyBooks() {
        viewModelScope.launch {
            _comedyBooksState.value = BooksUiState.Loading
            try {
                val result = booksRepository.getBooksByCategory("comedy" , 5)
                _comedyBooksState.value = BooksUiState.Success(result)
            } catch (_: Exception) {
                _comedyBooksState.value = BooksUiState.Error
            }
        }
    }


}