import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log

fun createSpeechRecognizerIntent(): Intent {
    return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
    }
}

fun createSpeechRecognitionListener(
    onQueryChanged: (String) -> Unit,
    onEndOfSpeechCallback: () -> Unit
): android.speech.RecognitionListener {
    return object : android.speech.RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {
            Log.d("SpeechRecognizer", "Ready for speech")
        }

        override fun onBeginningOfSpeech() {
            Log.d("SpeechRecognizer", "Beginning of speech")
        }

        override fun onRmsChanged(rmsdB: Float) {}

        override fun onBufferReceived(buffer: ByteArray?) {}

        override fun onEndOfSpeech() {
            onEndOfSpeechCallback()
            Log.d("SpeechRecognizer", "End of speech")
        }

        override fun onError(error: Int) {
            onEndOfSpeechCallback()
            Log.e("SpeechRecognizer", "Error: $error")
        }

        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                onQueryChanged(matches[0])
            }
        }

        override fun onPartialResults(partialResults: Bundle?) {}

        override fun onEvent(eventType: Int, params: Bundle?) {}
    }
}

fun toggleListening(
    speechRecognizer: SpeechRecognizer,
    speechRecognizerIntent: Intent,
    isListening: Boolean
) {
    if (isListening) {
        speechRecognizer.stopListening()
    } else {
        speechRecognizer.startListening(speechRecognizerIntent)
    }
}