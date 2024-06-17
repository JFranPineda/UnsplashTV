package com.example.unsplashtv.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.unsplashtv.ui.theme.Aqua80
import com.example.unsplashtv.ui.theme.WhiteFF
import androidx.compose.material3.Card
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    val isExpanded = remember { mutableStateOf(false) }
    val fractionWidth by animateFloatAsState(
        targetValue = if (isExpanded.value) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "WidthFractionTransition"
    )
    val boxModifier = if (isExpanded.value) {
        Modifier
            .fillMaxWidth(fractionWidth)
            .clickable { isExpanded.value = !isExpanded.value }
    } else {
        Modifier
            .size(56.dp)
            .clickable { isExpanded.value = !isExpanded.value }
    }

    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        if (isExpanded.value) {
            ExpandedSearchBar(
                query = query,
                onQueryChanged = onQueryChanged,
                onSearch = onSearch,
                onCollapse = { isExpanded.value = false }
            )
        } else {
            Card(
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = WhiteFF,
                    modifier = Modifier
                        .size(56.dp)
                        .clickable { isExpanded.value = true }
                        .background(Aqua80)
                )
            }
        }
    }
}