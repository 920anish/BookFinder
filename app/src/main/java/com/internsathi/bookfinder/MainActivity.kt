package com.internsathi.bookfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.internsathi.bookfinder.ui.theme.BookFinderTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: BooksViewModel by viewModels()
            val techUiState = viewModel.techBooksState.collectAsStateWithLifecycle()
            val isLightMode = viewModel.isLightMode.collectAsState()
            BookFinderTheme(darkTheme = !isLightMode.value ) {
                Scaffold(
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
                                        imageVector = if (isLightMode.value)Icons.Filled.LightMode else Icons.Default.DarkMode,
                                        contentDescription = "Exit to app Icon"
                                    )
                                }
                            }

                        )
                    }
                ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        techUiState = techUiState,
                        retryAction = viewModel::getAllBooks
                    )
                }
            }
        }
    }
}

