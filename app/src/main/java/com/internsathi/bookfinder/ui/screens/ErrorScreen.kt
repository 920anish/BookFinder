package com.internsathi.bookfinder.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorScreen(retryAction: () -> Unit) {

    Button(
        onClick = retryAction
    ) {
        Text("Retry")
    }

}
