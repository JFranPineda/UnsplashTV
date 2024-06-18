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
    private var currentPage: Int = 1
    private var isFetching = false

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> get() = _photos

    fun getRandomPhotos() {
        viewModelScope.launch {
            val response = repository.getRandomPhotos()
            _photos.value = response
        }
    }

    fun searchPhotos(query: String, page: Int = 1) {
        if (isFetching || query.isEmpty()) return
        isFetching = true
        viewModelScope.launch {
            val response = repository.searchPhotos(query = query, page = page)
            if (page == 1) {
                _photos.value = response.results
            } else {
                _photos.value += response.results
            }
            currentPage = page
            isFetching = false
        }
    }

    fun loadMorePhotos(query: String) {
        searchPhotos(query = query, page = currentPage + 1)
    }
}