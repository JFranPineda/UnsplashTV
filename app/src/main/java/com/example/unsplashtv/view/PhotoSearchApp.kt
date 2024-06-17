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
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.unsplashtv.ui.theme.Black00
import com.example.unsplashtv.ui.theme.DarkGray
import com.example.unsplashtv.ui.theme.Red800
import com.example.unsplashtv.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTvMaterial3Api::class)
@Composable
fun PhotoSearchApp(viewModel: PhotoViewModel) {
    val photos by viewModel.photos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRandomPhotos()
    }

    Scaffold(
        containerColor = DarkGray,
        topBar = {
            TopAppBar(title = { Text("Trending Now", style = Typography.titleLarge) })
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                PhotoGrid(photos = photos)
            }
        }
    )
}