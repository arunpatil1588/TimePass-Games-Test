package com.example.randomdogsassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.randomdogsassignment.ui.theme.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.randomdogsassignment.ui.theme.DogViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val viewModel: DogViewModel = hiltViewModel()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            navToGenerate = { navController.navigate("generate_dogs") },
                            navToGallery = {
                                viewModel.restoreFromCache() // Restore cache before navigation
                                navController.navigate("gallery")
                            }, navController = navController)
                    }
                    composable("generate_dogs") {
                        GenerateDogsScreen(viewModel = viewModel, navController = navController)
                    }
                    composable("gallery") {
                        RecentlyGeneratedDogsScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun TopBar(title: String, navController: NavHostController) {
        TopAppBar(
            title = { androidx.compose.material.Text(text = title) },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }

    @Composable
    fun GenerateDogsScreen(viewModel: DogViewModel, navController: NavHostController) {

        val isLoading = remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(title = "Generate Dogs", navController = navController)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = { viewModel.fetchDogWithRetry() },
                    colors = ButtonDefaults.buttonColors(Color(66, 134, 244))
                ) {
                    Text("Generate!")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (isLoading.value) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Add spacing between items
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(viewModel.dogImages) { imageUrl ->
                        Image(
                            painter = rememberImagePainter(
                                data = imageUrl,
                                builder = {
                                    crossfade(true)
                                    placeholder(R.drawable.baseline_downloading_24)
                                    error(R.drawable.baseline_error_24)
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }


    @Composable
    fun RecentlyGeneratedDogsScreen(viewModel: DogViewModel, navController: NavHostController) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(title = "Recently Generated Dogs", navController = navController)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = { viewModel.clearCache() },
                    colors = ButtonDefaults.buttonColors(Color(66, 134, 244))
                ) {
                    Text("Clear Dogs")
                }
                Spacer(modifier = Modifier.height(16.dp))

                LazyRow( // Change from LazyColumn to LazyRow
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Adds spacing
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(viewModel.dogImages) { imageUrl ->
                        Image(
                            painter = rememberImagePainter(imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp) // Ensure images fit nicely
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

