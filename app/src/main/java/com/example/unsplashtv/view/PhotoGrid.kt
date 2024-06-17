package com.example.unsplashtv.view

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.unsplashtv.model.Photo
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.unit.dp

@Composable
fun PhotoGrid(photos: List<Photo>) {
    Log.d("PhotoGrid", "Photos received: $photos")
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 320.dp)
    ) {
        items(photos) { photo ->
            PhotoCard(photo = photo)
        }
    }
}