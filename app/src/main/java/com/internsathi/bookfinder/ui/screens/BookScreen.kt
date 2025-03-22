package com.internsathi.bookfinder.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internsathi.bookfinder.model.Book
import com.internsathi.bookfinder.model.Books
import com.internsathi.bookfinder.ui.TabItem

@Composable
fun BookScreen(books: Books, modifier: Modifier = Modifier) {

    val tabs = listOf(
        TabItem(
            "Technology",
            Icons.Filled.Science,
            Icons.Outlined.Science,
        ),


        )

    LazyColumn(modifier = modifier) {

        items(books.items) { book ->
            BookCard(book)
        }

    }


}

@Composable
fun BookCard(book: Book) {

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