package spectrum.fittech.componentes

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.TelaLogin
import spectrum.fittech.TelaPerfil


// Botão para voltar para tela de login (fiz para estudos hehehe)
@Composable
fun BotaoVoltarTelaLogin() {
    val context = LocalContext.current

    IconButton(onClick = {
        val telaLogin = Intent(context, TelaLogin::class.java)
        context.startActivity(telaLogin)
    }) {
        Icon(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("android.resource://spectrum.fittech/raw/setaesquerda")
                    .decoderFactory(SvgDecoder.Factory())
                    .build()
            ),
            contentDescription = "Home",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }
}


@Composable
fun BotaoTelaPerfil(){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color(0xFF2C2C2E), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {
            val telaPerfil = Intent(context, TelaPerfil()::class.java)
            context.startActivity(telaPerfil)
        }) {
            Icon(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("android.resource://spectrum.fittech/raw/setaesquerda")
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                ),
                contentDescription = "Home",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    }
}