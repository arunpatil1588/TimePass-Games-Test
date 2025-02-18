package com.example.randomdogsassignment.ui.theme

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(private val apiService: DogApiService) {
    suspend fun fetchDogImage(): String? {
        return try {
            val response = apiService.getRandomDogImage()
            if (response.isSuccessful) response.body()?.imageUrl else null
        } catch (e: Exception) {
            null
        }
    }
}