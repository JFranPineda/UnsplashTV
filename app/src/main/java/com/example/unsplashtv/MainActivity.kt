package com.example.unsplashtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.example.unsplashtv.ui.theme.UnsplashTVTheme
import com.example.unsplashtv.view.PhotoSearchApp
import com.example.unsplashtv.viewmodel.PhotoViewModel
import com.example.unsplashtv.viewmodel.PhotoViewModelFactory
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    private val viewModel: PhotoViewModel by viewModels { PhotoViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape,
                ) {
                    PhotoSearchApp(viewModel)
                }
            }
        }
    }
}