package com.example.safeflow.HomePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safeflow.Data.MarsPhoto
import com.example.safeflow.network.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class MarsViewModel : ViewModel() {
    private val _photos = MutableStateFlow<List<MarsPhoto>>(emptyList())
    val photos: StateFlow<List<MarsPhoto>> = _photos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val marsPhotos = NetworkModule.marsApiService.getPhotos("1000") // Example SOL value
                _photos.value = marsPhotos
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load Mars photos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}