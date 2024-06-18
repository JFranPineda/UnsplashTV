package com.example.unsplashtv.view.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.unsplashtv.ui.theme.WhiteFF

@Composable
fun SearchTextField(query: String, onQueryChanged: (String) -> Unit, modifier:Modifier) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        label = { Text(text = "Search") },
        modifier = modifier
    )
}