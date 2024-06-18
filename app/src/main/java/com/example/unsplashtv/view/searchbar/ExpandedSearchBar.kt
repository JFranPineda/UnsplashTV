package com.example.unsplashtv.view.searchbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
        SearchTextField(query = query, onQueryChanged = onQueryChanged, modifier = Modifier.weight(1f))
        SearchButton(onSearch = onSearch, onCollapse = onCollapse)
        VoiceSearchButton(onVoiceResult = onQueryChanged)
    }
}