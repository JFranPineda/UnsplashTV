package com.example.unsplashtv.viewmodel

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

    private val _searchResults = MutableStateFlow<List<Photo>>(emptyList())
    val searchResults: StateFlow<List<Photo>> get() = _searchResults

    fun getRandomPhotos() {
        viewModelScope.launch {
            val response = repository.getRandomPhotos()
            _photos.value = response
        }
    }

    fun searchPhotos(query: String) {
        viewModelScope.launch {
            val response = repository.searchPhotos(query)
            _searchResults.value = response.results
        }
    }
}