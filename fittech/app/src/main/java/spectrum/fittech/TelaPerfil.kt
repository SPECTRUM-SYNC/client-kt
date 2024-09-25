package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.ui.theme.FittechTheme

class TelaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaPer(modifier: Modifier = Modifier) {
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            // Botão voltar
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFF2C2C2E), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = {
                    val home = Intent(context, Home::class.java)
                    context.startActivity(home)
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

        // Outras colunas de conteúdo
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagem com borda circular
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .border(
                        BorderStroke(1.dp, Color.Unspecified),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.dalva),
                    contentDescription = "user",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = "Dalva",
                style = TextStyle(
                    fontSize = 32.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
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
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.txt_editar_perfil),
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
                        contentDescription = "Editar Perfil",
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
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.txt_politica_privacidade),
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
                        contentDescription = "Politica de Privacidade",
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
                    text = stringResource(id = R.string.txt_configuracoes),
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
                        contentDescription = "Configuracoes",
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

            // Row Sair
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.txt_sair),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFF2424),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable {
                        val telaLogin = Intent(context, TelaLogin::class.java)
                        context.startActivity(telaLogin)
                    }
                )
            }

            // Segunda linha
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
fun Perfil() {
    FittechTheme {
        TelaPer()
    }
}