package com.internsathi.bookfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.internsathi.bookfinder.navigation.Navigation
import com.internsathi.bookfinder.ui.utils.BottomNavBar
import com.internsathi.bookfinder.ui.theme.BookFinderTheme
import com.internsathi.bookfinder.viewmodel.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }

            val viewModel: BooksViewModel by viewModels()
            val isLightMode = viewModel.isLightMode.collectAsState()
            BookFinderTheme(darkTheme = !isLightMode.value ) {
                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Book Finder") },
                            actions = {
                                IconButton(
                                    onClick = {
                                        viewModel.switchTheme()
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (!isLightMode.value)Icons.Filled.LightMode else Icons.Default.DarkMode,
                                        contentDescription = "Exit to app Icon"
                                    )
                                }
                            }

                        )
                    },
                    bottomBar = {
                        BottomNavBar(navController)
                    }
                ) { innerPadding ->
                    Navigation(navController,innerPadding , viewModel , snackbarHostState)
                }
            }
        }
    }
}

