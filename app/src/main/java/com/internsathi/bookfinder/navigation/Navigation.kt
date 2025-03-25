package com.internsathi.bookfinder.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.internsathi.bookfinder.ui.screens.DetailScreen
import com.internsathi.bookfinder.ui.screens.FavouriteDetailScreen
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
        startDestination = Home,
//                enterTransition = {
//            EnterTransition.None
//        },
//        exitTransition = {
//            ExitTransition.None
//        },
//        popEnterTransition = {
//            EnterTransition.None
//        },
//        popExitTransition = {
//            ExitTransition.None
//        }
    ) {
        composable <Home> {
            HomeScreen(
                modifier = Modifier.padding(innerPaddingValues),
                onNavigateToFavourite = {
                    navController.navigate(route = Favourite){
                        popUpTo(Home) {saveState = true}
                        launchSingleTop = true
                        restoreState = true

                    }
                },
                viewModel = viewModel,
                retryAction =viewModel::getAllBooks,
                onNavigateToDetail = {
                    id , title , authors , publishedDate , imageUrl , description ->
                    navController.navigate(
                        route = Detail(id , title  , authors, publishedDate , imageUrl , description)
                    ){
                        popUpTo(Home) {saveState = true}
                        launchSingleTop = true
                        restoreState = false
                    }
                }

            )
        }
        composable<Favourite> {
            FavouriteScreen(
                modifier = Modifier.padding(innerPaddingValues),
                viewModel = viewModel,
                onNavigateToFavouriteDetail = {
                    navController.navigate(route = FavouriteDetail){
                        popUpTo(Home) {saveState = true}
                        launchSingleTop = true
                        restoreState = true
                    }

                }

            )
        }

        composable<Detail> {
            val id = it.toRoute<Detail>().id
            val title = it.toRoute<Detail>().title
            val authors = it.toRoute<Detail>().authors
            val publishedDate = it.toRoute<Detail>().publishedDate
            val imageUrl = it.toRoute<Detail>().imageUrl
            val description = it.toRoute<Detail>().description
            DetailScreen(
                modifier = Modifier.padding(innerPaddingValues),
                viewModel = viewModel,
                id =  id ,
                title = title ,
                authors = authors ,
                publishedDate = publishedDate ,
                imageUrl = imageUrl ,
                description = description)
        }


        composable<FavouriteDetail> {
            FavouriteDetailScreen()
        }


    }


}