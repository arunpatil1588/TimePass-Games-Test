package com.example.randomdogsassignment.ui.theme

import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<DogResponse>
}

