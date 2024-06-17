package com.example.unsplashtv.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashtv.model.Photo
import com.example.unsplashtv.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoViewModel: ViewModel() {
    private val repository = PhotoRepository()

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> get() = _photos

    fun getRandomPhotos() {
        viewModelScope.launch {
            val response = repository.getRandomPhotos()
            _photos.value = response
            Log.d("PhotoViewModel", "Fetched photos: ${response.size}")
        }
    }

    fun searchPhotos(query: String) {
        viewModelScope.launch {
            val response = repository.searchPhotos(query)
            _photos.value = response.results
            Log.d("PhotoViewModel", "Searched photos: ${response.results.size}")
        }
    }
}