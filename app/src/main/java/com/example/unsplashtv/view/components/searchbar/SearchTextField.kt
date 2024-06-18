package com.example.unsplashtv.view.components.searchbar

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchTextField(query: String, onQueryChanged: (String) -> Unit, modifier:Modifier) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        label = { Text(text = "Search") },
        modifier = modifier
    )
}