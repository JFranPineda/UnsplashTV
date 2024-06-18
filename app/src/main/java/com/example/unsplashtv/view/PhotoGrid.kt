package com.example.unsplashtv.view

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.unsplashtv.model.Photo
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Composable
fun PhotoGrid(photos: List<Photo>, onLoadMore: () -> Unit) {
    Log.d("PhotoGrid", "Photos received: $photos")
    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 320.dp),
        state = gridState
    ) {
        items(photos) { photo ->
            PhotoCard(photo = photo)
        }
    }

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo }
            .filter { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
                Log.d("PhotoGrid", "totalItems: $totalItems, lastVisibleItemIndex: $lastVisibleItemIndex")
                (lastVisibleItemIndex == totalItems - 1) && lastVisibleItemIndex > -1
            }
            .collect {
                Log.d("PhotoGrid", "Launching coroutineScope onLoadMode...")
                coroutineScope.launch { onLoadMore() }
            }
    }

}