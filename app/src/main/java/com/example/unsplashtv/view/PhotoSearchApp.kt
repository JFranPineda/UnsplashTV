package com.example.unsplashtv.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.example.unsplashtv.viewmodel.PhotoViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.unsplashtv.ui.theme.DarkGray
import com.example.unsplashtv.ui.theme.Typography
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTvMaterial3Api::class)
@Composable
fun PhotoSearchApp(viewModel: PhotoViewModel) {
    val photos by viewModel.photos.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var textSearchResult by remember { mutableStateOf("Trending Now") }

    fun onSearchHandle(searchQuery: String) {
        if (searchQuery.isEmpty()) {
            viewModel.getRandomPhotos()
        } else {
            viewModel.searchPhotos(query = searchQuery, page = 1)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getRandomPhotos()
    }

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

    Scaffold(
        containerColor = DarkGray,
        topBar = {
            TopAppBar(title = { Text("Haystack Photos", style = Typography.titleLarge) })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SearchBar(
                    query = searchQuery,
                    onQueryChanged = { searchQuery = it },
                    onSearch = { onSearchHandle(searchQuery) }
                )
                Text(textSearchResult, style = Typography.bodyLarge)
                PhotoGrid(photos = photos, onLoadMore = { viewModel.loadMorePhotos(searchQuery) })
            }
        }
    )
}