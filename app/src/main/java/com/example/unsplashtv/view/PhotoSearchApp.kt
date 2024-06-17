package com.example.unsplashtv.view

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
import com.example.unsplashtv.viewmodel.PhotoViewModel
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoSearchApp(viewModel: PhotoViewModel) {
    val photos by viewModel.photos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRandomPhotos()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Photo Search") })
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                PhotoGrid(photos = photos)
            }
        }
    )
}