package com.internsathi.bookfinder.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.internsathi.bookfinder.ui.screens.HomeScreen
import com.internsathi.bookfinder.viewmodel.BooksViewModel


@Composable
fun Navigation(
    navController: NavHostController,
    innerPaddingValues: PaddingValues,
    viewModel: BooksViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        composable <Home> {
            val techUiState = viewModel.techBooksState.collectAsStateWithLifecycle()
            HomeScreen(
                modifier = Modifier.padding(innerPaddingValues),
                techUiState = techUiState,
                retryAction =viewModel::getAllBooks
            )
        }

    }


}