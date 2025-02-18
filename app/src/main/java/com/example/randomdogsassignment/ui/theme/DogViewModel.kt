package com.example.randomdogsassignment.ui.theme

import androidx.collection.LruCache
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdogsassignment.ui.theme.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DogViewModel @Inject constructor(private val repository: DogRepository) : ViewModel() {

    private val cache = LruCache<Int, String>(20)  // Caches images (20 max)
    private var counter = 0
    var dogImages = mutableStateListOf<String>()
    init {
        restoreFromCache()
    }

    fun fetchDogWithRetry() {
        viewModelScope.launch {
            repeat(3) {
                val imageUrl = repository.fetchDogImage()
                if (imageUrl != null) {
                    cache.put(counter++, imageUrl) // Store in cache
                    dogImages.add(0, imageUrl)    // Update UI list
                    return@launch
                }
                delay(1000) // Retry delay
            }
        }
    }

    private fun getCachedDogs(): List<String> {
        return (0 until counter).mapNotNull { cache.get(it) }
    }

    fun restoreFromCache() {
        dogImages.clear()
        dogImages.addAll(getCachedDogs())
    }

    fun clearCache() {
        cache.evictAll()
        dogImages.clear()
        counter = 0
    }
}
