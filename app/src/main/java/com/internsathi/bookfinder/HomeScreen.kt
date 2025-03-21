package com.internsathi.bookfinder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internsathi.bookfinder.data.Book
import com.internsathi.bookfinder.data.Books

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

@Composable
fun BookScreen(books: Books , modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {

            items (books.items){
                book ->
                BookCard(book)
            }

    }



}

@Composable
fun BookCard(book : Book) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Text(book.id)
        Text(book.volumeInfo.title)

    }

}


@Composable
fun ErrorScreen(retryAction: () -> Unit) {

    Button(
        onClick = retryAction
    ) {
        Text("Retry")
    }

}



