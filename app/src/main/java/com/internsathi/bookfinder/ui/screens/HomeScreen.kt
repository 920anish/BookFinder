package com.internsathi.bookfinder.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.internsathi.bookfinder.model.BooksUiState
import com.internsathi.bookfinder.viewmodel.BooksViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    retryAction: () -> Unit,
    viewModel: BooksViewModel,
    onNavigateToDetail : (String , String , List<String>? , String , String? , String?) -> Unit,

    ) {


    //later view model bata , for now temp
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(
        "Technology", "Horror", "Comedy"
    )


    val techUiState = viewModel.techBooksState.collectAsStateWithLifecycle()
    val horrorUiState = viewModel.horrorBooksState.collectAsStateWithLifecycle()
    val comedyUiState = viewModel.comedyBooksState.collectAsStateWithLifecycle()

    Column (
        modifier = modifier.fillMaxSize(),
    ){
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

       //selected tab ko book list
        val state = when (selectedTabIndex) {
            0 -> techUiState.value
            1 -> horrorUiState.value
            2 -> comedyUiState.value
            else -> techUiState.value
        }

        when (state) {

            is BooksUiState.Loading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center))
            is BooksUiState.Error -> ErrorScreen(retryAction)
            is BooksUiState.Success -> BookScreen(
                books = state.books,
                onNavigateToDetail = onNavigateToDetail
            )
        }

    }
}






