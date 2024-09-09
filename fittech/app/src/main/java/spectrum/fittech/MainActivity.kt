package spectrum.fittech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.ui.theme.FittechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FittechTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) { innerPadding ->
                    Tela(
                        nome = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Tela(nome: String, modifier: Modifier = Modifier) {
    // Estados para rastrear a seleção de gênero
    val masculinoSelecionado = remember { mutableStateOf(false) }
    val femininoSelecionado = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título e descrições
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Conte um pouco de você!",
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Para uma melhor experiência precisamos",
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White
                )
            )

            Text(
                text = "que você informe seu gênero",
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
        }

        // Seção de escolha de gênero
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Primeiro círculo - Masculino
            Column(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(if (masculinoSelecionado.value) Color(0xFFFF3B47) else Color(0xFF2C2C2E))
                    .clickable {
                        masculinoSelecionado.value = true
                        femininoSelecionado.value = false
                    }
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = if (masculinoSelecionado.value) R.mipmap.homemselected else R.mipmap.homem),
                    contentDescription = "Homem",
                    modifier = modifier.size(48.dp)
                )
                Text(
                    text = "Masculino",
                    modifier = Modifier
                        .padding(top = 16.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        textAlign = TextAlign.Center,
                        color = if (masculinoSelecionado.value) Color.Black else Color.White
                    )
                )
            }

            // Segundo círculo - Feminino
            Column(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(if (femininoSelecionado.value) Color(0xFFFF3B47) else Color(0xFF2C2C2E))
                    .clickable {
                        femininoSelecionado.value = true
                        masculinoSelecionado.value = false
                    }
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = if (femininoSelecionado.value) R.mipmap.mulherselected else R.mipmap.mulher),
                    contentDescription = "Mulher",
                    modifier = modifier.size(48.dp)
                )
                Text(
                    text = "Feminino",
                    modifier = Modifier
                        .padding(top = 16.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        textAlign = TextAlign.Center,
                        color = if (femininoSelecionado.value) Color.Black else Color.White
                    )
                )
            }
        }

        // Botão "Próximo"
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3B47)
                )
            ) {
                Text(text = "Próximo")
                Image(
                    painter = painterResource(id = R.mipmap.setadireita),
                    contentDescription = "Seta Direita",
                    modifier = modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Questionario() {
    FittechTheme {
        Tela(nome = "Android")
    }
}