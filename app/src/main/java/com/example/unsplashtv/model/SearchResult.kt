package com.example.unsplashtv.model

data class SearchResult(
    val results: List<Photo>,
    val total_pages: Int,
    val total: Int
)
