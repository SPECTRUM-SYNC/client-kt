package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.componentes.BotaoQuestionarioData
import spectrum.fittech.ui.theme.FittechTheme
import kotlin.math.roundToInt

class TelaQuestionarioAltura : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    QuestionarioAltura(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionarioAltura(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var altura by remember { mutableFloatStateOf(170f) } // Altura inicial de 170cm
    val listState =
        rememberLazyListState(initialFirstVisibleItemIndex = 170 - 140) // Começa com 170cm
    var hasScrolledToInitial by remember { mutableStateOf(false) } // Controla a rolagem inicial automática

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(vertical = 32.dp)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título e descrições
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Qual sua altura?",
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Isso nos ajuda a criar seu plano personalizado",
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
        }

        // Seção de escolha de altura
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), // Limitar altura para exibir 7 valores
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // LazyColumn para o scroll vertical
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp), // Define o tamanho que acomoda 7 valores
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(76) { index -> // 76 itens (140cm até 215cm)
                        val currentAltura = index + 140
                        val isSelected = (altura.roundToInt() == currentAltura)

                        // Mostrar a linha divisória antes e depois do valor selecionado
                        if (isSelected) {
                            Divider(color = Color.Gray, thickness = 2.dp)
                        }

                        // Exibição do valor da altura
                        Text(
                            text = "$currentAltura cm",
                            fontSize = if (isSelected) 24.sp else 20.sp, // Tamanho maior para o selecionado
                            color = if (isSelected) Color.White else Color.Gray,
                            textAlign = TextAlign.Center
                        )

                        if (isSelected) {
                            Divider(color = Color.Gray, thickness = 2.dp)
                        }
                    }
                }
            }

            // Atualiza o valor da altura conforme o scroll
            LaunchedEffect(
                listState.firstVisibleItemIndex,
                listState.firstVisibleItemScrollOffset
            ) {
                val index = listState.firstVisibleItemIndex
                altura = (index + 140).toFloat()

                // Verifica se já rolamos para o item inicial
                if (!hasScrolledToInitial) {
                    // Centraliza o item selecionado na área visível
                    listState.scrollToItem((index + 3).coerceAtMost(76)) // Mantém dentro dos limites
                    hasScrolledToInitial = true
                }
            }
        }

        // Botão "Próximo"
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            BotaoQuestionarioData()

            Button(
                onClick = {
                    val telaQuestionarioMeta = Intent(context, TelaQuestionarioMeta()::class.java)
                    context.startActivity(telaQuestionarioMeta)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Bottom)
            ) {
                Text(text = "Próximo")
                Image(
                    painter = painterResource(id = R.mipmap.setadireita),
                    contentDescription = "Seta Direita",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionarioAlturaPreview() {
    FittechTheme {
        QuestionarioAltura("Android")
    }
}