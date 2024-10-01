package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.componentes.BotaoQuestionarioPeso
import spectrum.fittech.ui.theme.FittechTheme
import kotlin.math.roundToInt

class TelaQuestionarioPeso : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                ) { innerPadding ->
                    QuestionarioPeso(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionarioPeso(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var peso by remember { mutableFloatStateOf(75f) } // Peso inicial de 75kg
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = 75 - 30) // Começa com 75kg
    var userInteracted by remember { mutableStateOf(false) } // Controla se o usuário interagiu

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
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Qual seu peso?",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Você pode mudar isso!",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
        }

        // Seção de escolha de peso
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Exibição do peso atual
            Text(
                text = buildAnnotatedString {
                    append("${peso.roundToInt()}")
                    withStyle(style = SpanStyle(fontSize = 24.sp)) {
                        append("kg")
                    }
                },
                fontSize = 64.sp,
                style = TextStyle(
                    color = Color.White
                )
            )

            // Indicador de peso com LazyRow
            Box(modifier = Modifier.fillMaxWidth()) {
                // Desenhar a linha central
                Canvas(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(4.dp)
                        .height(144.dp),
                    onDraw = {
                        drawRect(Color.White)
                    }
                )

                LazyRow(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(144.dp), // Altura suficiente para as linhas de diferentes tamanhos
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(204) { index -> // 204 itens (30kg até 200kg)
                        val currentPeso = index + 30
                        val isSelected = (peso.roundToInt() == currentPeso)

                        val lineHeight = when {
                            currentPeso % 5 == 0 -> 64.dp // Linha grande para pesos múltiplos de 5
                            else -> 32.dp // Linha normal para os outros pesos
                        }


                        // Desenho da linha vertical
                        Canvas(
                            modifier = Modifier
                                .width(if (isSelected) 4.dp else 2.dp)
                                .height(lineHeight),
                            onDraw = {
                                drawRect(if (isSelected) Color(0xFFFF3B47) else Color(0xFFFF3B47))
                            }
                        )
                    }
                }
            }

            // Atualiza o valor do peso conforme o scroll
            LaunchedEffect(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset) {
                if (userInteracted) {
                    val index = listState.firstVisibleItemIndex
                    peso = (index + 30).toFloat()
                }
            }

            // Detecta quando o usuário interage
            LaunchedEffect(listState.isScrollInProgress) {
                if (listState.isScrollInProgress) {
                    userInteracted = true
                }
            }
        }

        // Botão "Próximo"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            BotaoQuestionarioPeso()

            Button(
                onClick = {
                    val telaQuestionarioAltura = Intent(context, TelaQuestionarioAltura::class.java)
                    context.startActivity(telaQuestionarioAltura)
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
fun QuestionarioPesoPreview() {
    FittechTheme {
        QuestionarioPeso()
    }
}