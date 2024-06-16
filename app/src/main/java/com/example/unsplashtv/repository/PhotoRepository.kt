package com.example.unsplashtv.repository

import com.example.unsplashtv.model.Photo
import com.example.unsplashtv.model.SearchResult
import com.example.unsplashtv.service.RetrofitInstance

class PhotoRepository {
    private val api = RetrofitInstance.api

    suspend fun getRandomPhotos(): List<Photo> = api.getRandomPhotos()
    suspend fun searchPhotos(query: String, page: Int = 1): SearchResult = api.searchPhotos(query, page = page)
}