package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.componentes.BotaoTelaPerfil
import spectrum.fittech.ui.theme.FittechTheme

class TelaConfiguracao : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    TelaConfiguracao(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaConfiguracao(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 48.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão voltar
            BotaoTelaPerfil()

            Text(
                text = "Configurações",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(horizontal = screenWidth * 0.14f)
            )

        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // Primeira linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )

            // Primeira Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 8.dp)
                    .clickable {
                        val notificacao = Intent(context, TelaNotificacao::class.java)
                        context.startActivity(notificacao)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notificações",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(onClick = { }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/setadireita")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Notificações",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }

            // Segunda linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )

            // Segunda Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 8.dp).
                clickable {
                    val idioma = Intent(context, TelaIdioma::class.java)
                    context.startActivity(idioma)
                },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Idioma",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(onClick = { }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/setadireita")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Idioma",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }

            // Terceira linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )

            // Terceira Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Contate-nos",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(onClick = { }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/setadireita")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Contate-nos",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }

            // Quarta linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun TelaConfiguracaoPreview() {
    FittechTheme {
        TelaConfiguracao("Android")
    }
}