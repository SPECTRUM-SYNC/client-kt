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
import spectrum.fittech.Questionario
import spectrum.fittech.TelaConfiguracao
import spectrum.fittech.TelaLogin
import spectrum.fittech.TelaPerfil
import spectrum.fittech.TelaQuestionarioAtividade
import spectrum.fittech.TelaQuestionarioData
import spectrum.fittech.TelaQuestionarioMeta
import spectrum.fittech.TelaQuestionarioPeso


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

@Composable
fun BotaoTelaContato(){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color(0xFF2C2C2E), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {
            val telaPerfil = Intent(context, TelaConfiguracao()::class.java)
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

@Composable
fun BotaoQuestionarioData(){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color(0xFF2C2C2E), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {
            val telaQuestionario = Intent(context, Questionario()::class.java)
            context.startActivity(telaQuestionario)
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

@Composable
fun BotaoQuestionarioPeso(){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color(0xFF2C2C2E), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {
            val telaQuestionarioData = Intent(context, TelaQuestionarioData()::class.java)
            context.startActivity(telaQuestionarioData)
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

@Composable
fun BotaoQuestionarioMeta(){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color(0xFF2C2C2E), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {
            val telaQuestionarioMeta = Intent(context, TelaQuestionarioPeso()::class.java)
            context.startActivity(telaQuestionarioMeta)
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

@Composable
fun BotaoQuestionarioAtividade(){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color(0xFF2C2C2E), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {
            val telaQuestionarioAtividade = Intent(context, TelaQuestionarioMeta()::class.java)
            context.startActivity(telaQuestionarioAtividade)
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

@Composable
fun BotaoQuestionarioDescanso(){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color(0xFF2C2C2E), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {
            val telaQuestionarioDescanso = Intent(context, TelaQuestionarioAtividade()::class.java)
            context.startActivity(telaQuestionarioDescanso)
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