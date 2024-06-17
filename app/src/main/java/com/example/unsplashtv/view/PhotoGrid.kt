package com.example.unsplashtv.view

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.unsplashtv.model.Photo
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
@Composable
fun PhotoGrid(photos: List<Photo>) {
    Log.d("PhotoGrid", "Photos received: $photos")
    LazyColumn {
        items(photos) { photo ->
            PhotoCard(photo = photo)
        }
    }
}