package com.internsathi.bookfinder.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen(retryAction: () -> Unit) {

    Button(
        onClick = retryAction,
        modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)
    ) {
        Text("Retry")
    }

}
