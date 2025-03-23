package com.internsathi.bookfinder.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.internsathi.bookfinder.navigation.Favourite
import com.internsathi.bookfinder.navigation.Home


@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home" , Icons.Filled.Home ,Icons.Outlined.Home , Home),
        BottomNavItem("Favourite" ,Icons.Default.Favorite ,Icons.Default.FavoriteBorder,Favourite )
    )


    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        items.forEach {
            item ->
            val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                } },
                icon = {
                    Icon(if(isSelected)item.selectedIcon else item.unselectedIcon, "navigation icon")
                },
                label = { Text(item.title) },
            )
        }
    }


}