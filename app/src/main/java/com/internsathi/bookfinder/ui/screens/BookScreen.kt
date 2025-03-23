package com.internsathi.bookfinder.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.internsathi.bookfinder.R
import com.internsathi.bookfinder.model.Book
import com.internsathi.bookfinder.model.Books

@Composable
fun BookScreen(books: Books, modifier: Modifier = Modifier, navController: NavHostController) {
    LazyColumn(modifier = modifier) {

        items(books.items) { book ->
            BookCard(book)
        }

    }


}

@Composable
fun BookCard(book: Book) {
    val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replaceFirst("http" ,"https")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                onClick = {},

            )
        ,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "thumbnail image of book",
                placeholder = painterResource(R.drawable.placeholder),
                modifier = Modifier.size(
                    124.dp
                ).weight(1F),
                contentScale = ContentScale.Fit

            )
            Column (modifier = Modifier.weight(2F)){
                Text(book.volumeInfo.title , style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))
                Text(book.volumeInfo.authors?.joinToString(",").toString() , style = MaterialTheme.typography.labelSmall)
            }

            IconButton(onClick = {} , modifier = Modifier.weight(1F)) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Favourite Icon"
                )
            }

        }



    }

}