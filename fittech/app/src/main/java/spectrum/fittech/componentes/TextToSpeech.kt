package spectrum.fittech.componentes

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import java.util.Locale

@Composable
fun AudioSpeech(treino: String, repeticoes: String) {
    val context = LocalContext.current
    val textToSpeechState = remember {
        mutableStateOf<TextToSpeech?>(null)
    }

    // Obter o idioma e país padrão do dispositivo
    val defaultLocale = Locale.getDefault()
    val mensagens = listOf(
        "Vamos começar o treino $treino. Faça $repeticoes",
        "Iniciando o treino $treino. Complete $repeticoes",
        "Prepare-se para o treino $treino. Realize $repeticoes",
        "Hora de treinar! Treino $treino: faça $repeticoes",
        "Vamos lá! Treino $treino. Conclua $repeticoes"
    )

    // Inicialização do TextToSpeech
    LaunchedEffect(Unit) {
        textToSpeechState.value = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeechState.value?.setLanguage(defaultLocale)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("AudioSpeech", "Idioma ${defaultLocale.language}-${defaultLocale.country} não suportado")
                }
            } else {
                Log.e("AudioSpeech", "Falha ao inicializar o TextToSpeech")
            }
        }
    }

    IconButton(onClick = {
        // Sorteia uma mensagem aleatória
        val mensagem = mensagens.random()
        textToSpeechState.value?.speak(mensagem, TextToSpeech.QUEUE_FLUSH, null, null)
    }) {
        Icon(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("android.resource://spectrum.fittech/raw/audio")
                    .decoderFactory(SvgDecoder.Factory())
                    .build()
            ),
            contentDescription = "Audio",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }

    // Liberar recursos ao sair da tela
    DisposableEffect(Unit) {
        onDispose {
            textToSpeechState.value?.shutdown()
        }
    }
}
