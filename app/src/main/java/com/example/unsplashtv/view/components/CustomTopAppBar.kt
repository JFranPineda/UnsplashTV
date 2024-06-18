package com.example.unsplashtv.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.unsplashtv.ui.theme.Aqua80
import com.example.unsplashtv.ui.theme.Typography
import com.example.unsplashtv.ui.theme.WhiteFF

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTvMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    val customTopAppBarColors = TopAppBarColors(
        containerColor = Aqua80,
        scrolledContainerColor = Color(0xFF3700B3),
        navigationIconContentColor = Color.White,
        titleContentColor = Color.White,
        actionIconContentColor = Color.White
    )

    TopAppBar(
        modifier = Modifier.padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 10.dp),
        title = {
            Text(
                text = "Haystack Photos",
                style = Typography.titleLarge,
                color = WhiteFF
            )
        },
        colors = customTopAppBarColors
    )
}