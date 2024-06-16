package com.example.unsplashtv.model

data class Photo(
    val id: String,
    val user: User,
    val urls: Urls,
    val tags: List<Tag>,
    val created_at: String,
    val width: Int,
    val height: Int,
    val alt_description: String,
    val likes: Int
)
