package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.componentes.BotaoQuestionarioData
import spectrum.fittech.ui.theme.FittechTheme

class TelaQuestionarioMeta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email = intent.getStringExtra("EXTRA_EMAIL")
        val senha = intent.getStringExtra("EXTRA_SENHA")
        val generoMasculino = intent.getBooleanExtra("MASCULINO_SELECIONADO", false)
        val generoFeminino = intent.getBooleanExtra("FEMININO_SELECIONADO", false)
        val dataSelecionada = intent.getStringExtra("DATA_SELECIONADA")
        val pesoUsuario = intent.getStringExtra("PESO_USUARIO")
        val nome = intent.getStringExtra("EXTRA_NOME")
        val foto = intent.getStringExtra("EXTRA_FOTO")
        val altura = intent.getStringExtra("ALTURA_USUARIO")


        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                ) { innerPadding ->
                    QuestionarioMeta(
                        nome = nome,
                        foto = foto,
                        email = email,
                        senha = senha,
                        generoMasculino = generoMasculino,
                        generoFeminino = generoFeminino,
                        dataSelecionada = dataSelecionada,
                        pesoUsuario = pesoUsuario,
                        altura = altura,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionarioMeta(
    nome: String?,
    foto: String?,
    email: String?,
    senha: String?,
    generoMasculino: Boolean?,
    generoFeminino: Boolean?,
    dataSelecionada: String?,
    pesoUsuario: String?,
    altura: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedGoal by remember { mutableStateOf("Ganhar peso") } // Meta inicial

// Lista de metas
    val goals = listOf(
        "Ganhar peso",
        "Perder peso",
        "Ficar definido",
        "Ganhar flexibilidade"
    )

    val listState =
        rememberLazyListState(initialFirstVisibleItemIndex = 0)

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
                text = stringResource(R.string.txt_qual_sua_meta),
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

        // Seção de escolha de meta
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // LazyColumn para o scroll vertical
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Adiciona um espaçamento antes da lista de valores
                    item {
                        Spacer(modifier = Modifier.height(10.dp)) // Ajuste o espaçamento inicial
                    }

                    items(goals.size) { index ->
                        val currentGoal = goals[index]
                        val isSelected = (selectedGoal == currentGoal)

                        // Mostrar a linha divisória antes e depois do valor selecionado
                        if (isSelected) {
                            Divider(color = Color.Gray, thickness = 2.dp)
                        }

                        // Exibição da meta
                        Text(
                            text = currentGoal,
                            fontSize = if (isSelected) 24.sp else 20.sp, // Tamanho maior para o selecionado
                            color = if (isSelected) Color.White else Color.Gray,
                            textAlign = TextAlign.Center
                        )

                        if (isSelected) {
                            Divider(color = Color.Gray, thickness = 2.dp)
                        }
                    }

                    // Adiciona um espaçamento no final da lista para permitir rolagem completa
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }

            // Atualiza a meta selecionada conforme o scroll
            LaunchedEffect(
                listState.firstVisibleItemIndex,
                listState.firstVisibleItemScrollOffset
            ) {
                val index = listState.firstVisibleItemIndex
                selectedGoal = goals.getOrElse(index) { "Ganhar peso" }
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
                    if (selectedGoal.isNotEmpty()) {
                        val goalValue =
                            if (selectedGoal == "Ganhar peso" || selectedGoal == "Ficar definido") {
                                "GanharMassa"
                            } else {
                                "PerderPeso"
                            }

                        val telaQuestionarioAtividade =
                            Intent(context, TelaQuestionarioAtividade()::class.java).apply {
                                putExtra("MASCULINO_SELECIONADO", generoMasculino)
                                putExtra("FEMININO_SELECIONADO", generoFeminino)
                                putExtra("EXTRA_EMAIL", email)
                                putExtra("EXTRA_SENHA", senha)
                                putExtra("EXTRA_NOME", nome)
                                putExtra("EXTRA_FOTO", foto)
                                putExtra("DATA_SELECIONADA", dataSelecionada)
                                putExtra("PESO_USUARIO", pesoUsuario)
                                putExtra("ALTURA_USUARIO", altura.toString())
                                putExtra("META", goalValue)
                                putExtra("META_FAKE", selectedGoal)
                            }
                        context.startActivity(telaQuestionarioAtividade)
                    } else {
                        Toast.makeText(
                            context,
                            "Por favor, selecione uma meta para avançar.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
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
fun QuestionarioMetaPreview() {
    FittechTheme {
        QuestionarioMeta(
            nome = "", foto = "", "", "", null, null, "", "", ""
        )
    }
}