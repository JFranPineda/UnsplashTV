package com.example.unsplashtv.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

@Composable
fun ExpandedSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onCollapse: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            label = { Text(text = "Search") },
            modifier = Modifier.weight(1f)
        )
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
}