package com.example.randomdogsassignment.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController



@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = { Text(text = title) }
    )
}


@Composable
fun HomeScreen(navToGenerate: () -> Unit, navToGallery: () -> Unit,navController: NavHostController) {
    TopBar(title = "Random Dogs App")

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center // Centers content in the Box
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centers buttons horizontally
            verticalArrangement = Arrangement.spacedBy(16.dp) // Adds spacing between buttons
        ) {
            Button(
                onClick = navToGenerate,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(66, 134, 244))
            ) {
                Text("Generate Dogs")
            }
            Button(
                onClick = navToGallery,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(66, 134, 244))
            ) {
                Text("My Recently Generated Dogs")
            }
        }
    }
}

