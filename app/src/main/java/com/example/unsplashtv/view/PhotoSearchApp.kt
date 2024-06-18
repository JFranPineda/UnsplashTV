package com.example.unsplashtv.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.unsplashtv.viewmodel.PhotoViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unsplashtv.ui.theme.DarkGray
import com.example.unsplashtv.view.components.CustomTextLabel
import com.example.unsplashtv.view.components.CustomTopAppBar
import com.example.unsplashtv.view.components.SearchBar
import com.example.unsplashtv.view.components.photolayout.PhotoGrid

@Composable
fun PhotoSearchApp(viewModel: PhotoViewModel) {
    val photos by viewModel.photos.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
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

    Scaffold(
        containerColor = DarkGray,
        topBar = { CustomTopAppBar() },
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
                CustomTextLabel(photos = photos, searchQuery = searchQuery)
                PhotoGrid(photos = photos, onLoadMore = { viewModel.loadMorePhotos(searchQuery) })
            }
        }
    )
}