package com.internsathi.bookfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SignalWifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(retryAction: () -> Unit, onNavigateToFavourite: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.outlineVariant),
            contentAlignment = Alignment.Center

        ) {
            Icon(
                imageVector = Icons.Outlined.SignalWifiOff,
                contentDescription = "No Internet Icon",
                modifier = Modifier.size(44.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("Error Fetching Books" , style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(18.dp))
        Row{
            OutlinedButton(
                onClick = retryAction,
                modifier = Modifier.padding(8.dp),


            ) {

                Text("Try Again" , style = MaterialTheme.typography.bodyMedium)

            }

            Button(
                modifier = Modifier.padding(8.dp),
                onClick = onNavigateToFavourite

            ) {

                Text("Offline Books" ,style = MaterialTheme.typography.bodySmall)
            }
        }

    }


}

