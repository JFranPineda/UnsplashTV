package com.example.unsplashtv.view.components.searchbar

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.unsplashtv.utils.RequestMicrophonePermission
import java.util.Locale

@Composable
fun VoiceSearchButton(onVoiceResult: (String) -> Unit) {
    RequestMicrophonePermission()
    val context = LocalContext.current
    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("VoiceSearchButton", "Received result with resultCode: ${result.resultCode}")
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            results?.firstOrNull()?.let { speechResult ->
                onVoiceResult(speechResult)
            }
        } else {
            Log.e("VoiceSearchButton", "Speech recognition failed with resultCode: ${result.resultCode}")
            Toast.makeText(context, "Speech Recognition failed", Toast.LENGTH_SHORT).show()
        }
    }

    Button(onClick = {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            Toast.makeText(context, "Speech not Available", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")
            }
            speechRecognizerLauncher.launch(intent)
        }
    }) {
        Text("Voice Search")
    }
}