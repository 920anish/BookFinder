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

    // fetch garda book ko config ko data class
    private data class BookCategory(
        val name: String,
        val maxResults: Int,
        val stateFlow: MutableStateFlow<BooksUiState>
    )

    private val bookCategories = listOf(
        BookCategory("technology", 3, MutableStateFlow(BooksUiState.Loading)),
        BookCategory("horror", 7, MutableStateFlow(BooksUiState.Loading)),
        BookCategory("comedy", maxResults = 10, stateFlow = MutableStateFlow(BooksUiState.Loading))
    )

    // read only part expose gareko
    val techBooksState = bookCategories[0].stateFlow.asStateFlow()
    val horrorBooksState = bookCategories[1].stateFlow.asStateFlow()
    val comedyBooksState = bookCategories[2].stateFlow.asStateFlow()

    // Initialize by fetching all books
    init {
        getAllBooks()
    }

    // Fetch all books for different categories
    fun getAllBooks() {
        bookCategories.forEach { category ->
            fetchBooksByCategory(category)
        }
    }

    // Generic method to fetch books for a specific category
    private fun fetchBooksByCategory(category: BookCategory) {
        viewModelScope.launch {
            // suru maa loading
            category.stateFlow.value = BooksUiState.Loading

            try {
                //fetch vayesi  success maa data
                val result = booksRepository.getBooksByCategory(
                    category.name,
                    category.maxResults
                )

                category.stateFlow.value = BooksUiState.Success(result)
            } catch (_: Exception) {
                // incase of error , generic for now
                category.stateFlow.value = BooksUiState.Error
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