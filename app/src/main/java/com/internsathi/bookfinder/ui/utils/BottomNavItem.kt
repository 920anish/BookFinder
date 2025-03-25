package com.internsathi.bookfinder.ui.utils

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem <T : Any>(
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: T,
)