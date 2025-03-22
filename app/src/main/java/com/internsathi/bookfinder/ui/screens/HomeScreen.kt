package com.internsathi.bookfinder.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.internsathi.bookfinder.model.BooksUiState

@Composable
fun HomeScreen(modifier: Modifier, techUiState: State<BooksUiState>, retryAction: () -> Unit) {
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        when(val state = techUiState.value) {
            is BooksUiState.Loading -> CircularProgressIndicator()
            is BooksUiState.Error -> ErrorScreen(retryAction)
            is BooksUiState.Success -> BookScreen(
                books = state.books
            )
        }

    }



}






