package com.example.unsplashtv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun UnsplashTVTheme(
        isInDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit,
) {
    val colorScheme = if (isInDarkTheme) {
        darkColorScheme(
                primary = Purple80,
                secondary = PurpleGrey80,
                tertiary = Pink80,
                background = DarkBlack,
                surface = DarkGray
        )
    } else {
        lightColorScheme(
                primary = Purple40,
                secondary = PurpleGrey40,
                tertiary = Pink40,
                background = WhiteFF,
                surface = PurpleGrey80
        )
    }
    MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
    )
}