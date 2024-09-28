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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import spectrum.fittech.componentes.BotaoTelaPerfil
import spectrum.fittech.ui.theme.FittechTheme

class TelaEditarPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    TelaEditarPerfil(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaEditarPerfil(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var nome by remember { mutableStateOf("Dalva dos Anjos") }
    var email by remember { mutableStateOf("Dalva145@mail.com") }
    var emailInvalido by remember { mutableStateOf(false) }
    var peso by remember { mutableStateOf("50 KG") }
    var motivacao by remember { mutableStateOf("Ficar forte para ginástica") }
    var meta by remember { mutableStateOf("Ficar definida") }


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
            BotaoTelaPerfil()
        }

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

                // Ícone de câmera no canto inferior direito
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.BottomEnd) // Alinhamento no canto inferior direito
                        .background(Color(0xFF2C2C2E), CircleShape), // Fundo cinza circular para o ícone
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/camera")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Camera",
                        modifier = Modifier.size(24.dp),
                        Color.White
                    )
                }
            }
        }



        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // Campo de nome
            TextField(
                label = { Text(text = "Nome") },
                value = nome,
                onValueChange = { digitadoNome ->
                    nome = digitadoNome
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Campo de email
            TextField(
                label = { Text(stringResource(id = R.string.ipt_email)) },
                value = email,
                onValueChange = { digitadoEmail ->
                    email = digitadoEmail
                    emailInvalido = !digitadoEmail.contains("@")
                },
                isError = emailInvalido,
                supportingText = {
                    if (emailInvalido) {
                        Text(text = stringResource(id = R.string.ipt_email_invalido))
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            // Campo de peso
            TextField(
                label = { Text(text = "Peso") },
                value = peso,
                onValueChange = { digitadoPeso ->
                    peso = digitadoPeso
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Campo de motivação
            TextField(
                label = { Text(text = "Motivação") },
                value = motivacao,
                onValueChange = { digitadoMotivacao ->
                    motivacao = digitadoMotivacao
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Campo de meta
            TextField(
                label = { Text(text = "Meta") },
                value = meta,
                onValueChange = { digitadoMeta ->
                    meta = digitadoMeta
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )


        }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val telaLogin = Intent(context, TelaLogin::class.java)

                    context.startActivity(telaLogin)
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(185.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3B47)
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Salvar"
                        ,style = TextStyle(
                            color = Color.White
                        ))
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun TelaEditarPerfilPreview() {
    FittechTheme {
        TelaEditarPerfil("Android")
    }
}