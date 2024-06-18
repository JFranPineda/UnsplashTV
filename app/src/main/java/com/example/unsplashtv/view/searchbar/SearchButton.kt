package com.example.unsplashtv.view.searchbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchButton(onSearch: () -> Unit, onCollapse: () -> Unit) {
    Button(
        onClick = {
            onSearch()
            onCollapse()
        },
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Text(text = "Search")
    }
}