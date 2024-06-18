package com.example.unsplashtv.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.unsplashtv.model.Photo
import com.example.unsplashtv.ui.theme.Typography
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CustomTextLabel(photos: List<Photo>, searchQuery: String) {
    var textSearchResult by remember { mutableStateOf("Trending Now") }

    LaunchedEffect(photos) {
        snapshotFlow { photos }
            .distinctUntilChanged()
            .collect { updatedPhotos ->
                textSearchResult = if (searchQuery.isEmpty()) {
                    "Trending Now"
                } else {
                    if (updatedPhotos.isEmpty()) {
                        "No search results for \"$searchQuery\""
                    } else {
                        "Search Results for \"$searchQuery\""
                    }
                }
            }
    }

    Text(
        modifier = Modifier.padding(horizontal = 5.dp, vertical = 0.dp),
        text = textSearchResult, style = Typography.bodyLarge
    )
}