package com.internsathi.bookfinder.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.internsathi.bookfinder.R
import com.internsathi.bookfinder.model.FavouriteBook
import com.internsathi.bookfinder.viewmodel.BooksViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FavouriteScreen(
    viewModel: BooksViewModel,
    modifier: Modifier = Modifier,
    onNavigateToFavouriteDetail: (String) -> Unit,
    snackbarHostState: SnackbarHostState,

    ) {

    val coroutineScope = rememberCoroutineScope()

    val books = viewModel.allFavouriteBooks.collectAsStateWithLifecycle(initialValue = emptyList())
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        if (books.value.isEmpty()) {
            Spacer(modifier)

            Text(
                "No Books Saved",
                modifier = modifier.align(alignment = Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            Text("Favourite Books", style = MaterialTheme.typography.labelMedium )

            LazyColumn(modifier = Modifier) {

                items(books.value) { book ->
                    FavouriteCard(book , onNavigateToFavouriteDetail , viewModel , coroutineScope , snackbarHostState)
                    Spacer(modifier= Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun FavouriteCard(
    book: FavouriteBook,
    onNavigateToFavouriteDetail: (String) -> Unit,
    viewModel: BooksViewModel,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable(onClick = { onNavigateToFavouriteDetail(book.id) }),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = "Book Thumbnail",
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                modifier = Modifier
                    .weight(1F)
                    .size(124.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.size(12.dp))

            Column(modifier = Modifier.weight(3F)) {
                Text(book.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(book.authors ?: "Unknown Author", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(book.publishedDate, style = MaterialTheme.typography.labelSmall)
            }
            IconButton(
                onClick = {
                    viewModel.deleteFavouriteBook(book)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Book deleted from favourite list")
                    }

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Icon",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
