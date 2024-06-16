package com.example.unsplashtv.service

import com.example.unsplashtv.model.Photo
import com.example.unsplashtv.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"
const val UNSPLASH_ACCESS_KEY = "sS9cEwmEp0k0rrJ77qxHI8tbK1eigzb24C7-zPDmbMM"

interface UnsplashApiService {
    @GET("photos/random")
    suspend fun getRandomPhotos(
        @Query("client_id") clientId: String = UNSPLASH_ACCESS_KEY,
        @Query("count") count: Int = 30
    ): List<Photo>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("client_id") clientId: String = UNSPLASH_ACCESS_KEY,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): SearchResult
}