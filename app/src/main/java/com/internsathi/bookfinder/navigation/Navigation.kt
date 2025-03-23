package com.internsathi.bookfinder.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.internsathi.bookfinder.ui.screens.DetailScreen
import com.internsathi.bookfinder.ui.screens.FavouriteScreen
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
            HomeScreen(
                modifier = Modifier.padding(innerPaddingValues),
                viewModel = viewModel,
                navController = navController,
                retryAction =viewModel::getAllBooks
            )
        }
        composable<Favourite> {
            FavouriteScreen()
        }

        composable<Detail> {
            DetailScreen()
        }

    }


}