package com.internsathi.bookfinder.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.internsathi.bookfinder.model.FavouriteBook
import com.internsathi.bookfinder.viewmodel.BooksViewModel

@Composable
fun FavouriteDetailScreen(id: String, modifier: Modifier, viewModel: BooksViewModel) {
    val book = viewModel.getBookById(id).collectAsStateWithLifecycle(initialValue = FavouriteBook(
        id = "" , title = "" , authors = "" , imageUrl = "" , publishedDate = "" , description = ""
    ))
    val imageUrl = book.value.imageUrl.toString()
    Column(
        modifier = modifier.fillMaxSize().padding(12.dp) ,

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Book thumbnail",
                modifier = Modifier
                    .weight(1.5F)
                    .size(124.dp),
                contentScale = ContentScale.Fit
            )

            Column(modifier = Modifier.weight(3F)) {
                Text(
                    text = book.value.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = book.value.authors ?: "Unknown Author",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Published: ${book.value.publishedDate}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                )
            }

        }
        HorizontalDivider()
        Text(
            text = book.value.description,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            modifier = Modifier
                .padding(top = 12.dp)
                .verticalScroll(rememberScrollState())
        )

    }

}