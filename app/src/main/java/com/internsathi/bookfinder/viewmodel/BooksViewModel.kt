package com.internsathi.bookfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.internsathi.bookfinder.data.network.BooksRepository
import com.internsathi.bookfinder.data.local.OfflineFavouriteBooksRepository
import com.internsathi.bookfinder.data.local.UserPreferencesRepository
import com.internsathi.bookfinder.model.BooksUiState
import com.internsathi.bookfinder.model.FavouriteBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val offlineFavouriteBooksRepository: OfflineFavouriteBooksRepository

) : ViewModel() {

    //TODO refactor to remove duplicacy

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
                val result = booksRepository.getBooksByCategory("comedy" , 10)
                _comedyBooksState.value = BooksUiState.Success(result)
            } catch (_: Exception) {
                _comedyBooksState.value = BooksUiState.Error
            }
        }
    }

    // stateflow maa convert gareko ,
    //for theme
    var isLightMode: StateFlow<Boolean> = userPreferencesRepository.isLightMode.stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(5000),
        true
    )

    fun switchTheme() {
        viewModelScope.launch {
            userPreferencesRepository.saveThemePreferences(!isLightMode.value)
        }
    }


    //for room db
    val allFavouriteBooks = offlineFavouriteBooksRepository.getAllFavouriteBooksStream()

    fun getBookById(id:String)  = offlineFavouriteBooksRepository.getFavouriteBookByIdSream(id)

    fun insertFavouriteBook(book : FavouriteBook) {
        viewModelScope.launch(Dispatchers.IO) {
            offlineFavouriteBooksRepository.insertFavouriteBook(book)
        }
    }
    fun deleteFavouriteBook(book : FavouriteBook) {
        viewModelScope.launch(Dispatchers.IO) {
            offlineFavouriteBooksRepository.deleteFavouriteBook(book)
        }
    }


}