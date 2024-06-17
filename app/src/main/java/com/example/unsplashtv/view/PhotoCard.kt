package com.example.unsplashtv.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unsplashtv.model.Photo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import formatDate
import androidx.compose.ui.Alignment
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.unsplashtv.ui.theme.Typography

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PhotoCard(photo: Photo) {
    val tagsText: String = photo.tags?.joinToString(", ") { it.title } ?: "No Tags"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp)
        ) {
            photo.urls?.small_s3?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(8.dp)), // Optional: Apply rounded corners
                    contentScale = ContentScale.Crop,
                    contentDescription = photo.alt_description,
                    onError = { error ->
                        Log.e("PhotoCard", "Failed to load image: $error")
                    }
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = tagsText,
                    modifier = Modifier.fillMaxWidth(),
                    style = Typography.labelLarge
                )
                Text(
                    text = "${photo.user.username} / ${formatDate(photo.created_at)}",
                    modifier = Modifier.fillMaxWidth(),
                    style = Typography.labelLarge
                )
            }
        }
    }
}