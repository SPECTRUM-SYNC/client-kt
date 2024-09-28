package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import spectrum.fittech.componentes.BotaoTelaContato
import spectrum.fittech.componentes.BotaoTelaPerfil
import spectrum.fittech.ui.theme.FittechTheme

class TelaContato : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    TelaContato(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaContato(name: String, modifier: Modifier = Modifier) {
    var userInput by remember { mutableStateOf("") } // Usar string em vez de TextFieldValue
    val chatMessages = remember { mutableStateListOf("Olá! Como posso te ajudar?", "1. Problemas de login", "2. Suporte técnico", "3. Falar com atendente") }
    val scrollState = remember { ScrollState(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(horizontal = 16.dp)
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            // Botão voltar
            BotaoTelaContato()
        }

        // Caixa de chat com as mensagens
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(8.dp)
                .verticalScroll(scrollState)
        ) {
            Column {
                chatMessages.forEach { message ->
                    Text(
                        text = message,
                        style = TextStyle (color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada do usuário
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = userInput,
                onValueChange = { newValue ->
                    userInput = newValue // Atualiza diretamente a string
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(Color.White)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Botão de Enviar
            androidx.compose.material3.Button(
                onClick = {
                    val selectedOption = userInput.toIntOrNull()
                    if (selectedOption != null) {
                        when (selectedOption) {
                            1 -> chatMessages.add("Você escolheu: Problemas de login. Por favor, tente redefinir sua senha.")
                            2 -> chatMessages.add("Você escolheu: Suporte técnico. Verifique se seu dispositivo está conectado à internet.")
                            3 -> chatMessages.add("Você escolheu: Falar com atendente. Aguarde, estamos transferindo sua chamada.")
                            else -> chatMessages.add("Opção inválida. Por favor, escolha um número de 1 a 3.")
                        }
                        userInput = "" // Limpa o campo após o envio
                    } else {
                        chatMessages.add("Por favor, insira um número válido.")
                    }
                },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3B47)
            ),
                ) {
                Text(text = "Enviar"
                    ,style = TextStyle(
                        color = Color.White
                    ))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaContatoPreview() {
    FittechTheme {
        TelaContato("Android")
    }
}