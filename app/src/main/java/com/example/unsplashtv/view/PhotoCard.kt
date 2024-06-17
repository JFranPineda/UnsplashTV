package com.example.unsplashtv.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unsplashtv.model.Photo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
fun PhotoCard(photo: Photo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Image(
                painter = rememberImagePainter(photo.urls.small),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text("Created At: ${photo.created_at}")
            Text("User: ${photo.user.username}")
            Text("Tags: ${photo.tags?.joinToString(", ") { it.title } ?: "No Tags"}")
        }
    }
}