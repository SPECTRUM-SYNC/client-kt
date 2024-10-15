package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.componentes.BotaoQuestionarioData
import spectrum.fittech.ui.theme.FittechTheme

class TelaQuestionarioAtividade : ComponentActivity() {
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
        val meta = intent.getStringExtra("META")
        val metaFake = intent.getStringExtra("META_FAKE")

        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                ) { innerPadding ->
                    QuestionarioAtividade(
                        nome = nome,
                        foto = foto,
                        email = email,
                        senha = senha,
                        generoMasculino = generoMasculino,
                        generoFeminino = generoFeminino,
                        dataSelecionada = dataSelecionada,
                        pesoUsuario = pesoUsuario,
                        altura = altura,
                        meta = meta,
                        metaFake = metaFake,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionarioAtividade(
    nome: String?,
    foto: String?,
    email: String?,
    senha: String?,
    generoMasculino: Boolean?,
    generoFeminino: Boolean?,
    dataSelecionada: String?,
    pesoUsuario: String?,
    altura: String?,
    meta: String?,
    metaFake: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedGoal by remember { mutableStateOf("Iniciante") } // Meta inicial

    var showPopup by remember { mutableStateOf(false) }


// Lista de metas
    val goals = listOf(
        "Iniciante",
        "Intermediário",
        "Avançado",
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
                text = stringResource(R.string.txt_qual_seu_nivel_atividade),
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
                selectedGoal = goals.getOrElse(index) { "Iniciante" }
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
//                        Como os treinos são baseados, no basico. Peço que mantenha essa valor tá bom

                        showPopup = true
                    } else {
                        Toast.makeText(
                            context,
                            "Por favor, selecione seu nível de atividade para avançar.",
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
                Text(text = stringResource(R.string.txt_ver_dados))
                Image(
                    painter = painterResource(id = R.mipmap.setadireita),
                    contentDescription = "Seta Direita",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }


    if (showPopup) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { showPopup = false }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.Center)
                    .background(
                        colorResource(R.color.background_card),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(R.string.txt_seus_dados),
                        fontSize = 18.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_nome_user),
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        if (nome != null) {
                            Text(
                                text = nome,
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    color = Color.White,
                                ),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_email_user),
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        if (email != null) {
                            Text(
                                text = email,
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    color = Color.White,
                                ),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_sexo_user),
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        Text(
                            text = if (generoMasculino == true) "Masculino" else "Feminino",
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.White,
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_altura_user),
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        Text(
                            text = "$altura CM",
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.White,
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_idade_user),
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        if (dataSelecionada != null) {
                            Text(
                                text = dataSelecionada,
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    color = Color.White,
                                ),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_peso_user),
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        Text(
                            text = "$pesoUsuario KG",
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.White,
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_meta_user),
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        if (metaFake != null) {
                            Text(
                                text = metaFake,
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    color = Color.White,
                                ),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_atividade_user),
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        Text(
                            text = selectedGoal,
                            style = TextStyle(
                                fontSize = 17.sp,
                                color = Color.White,
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {

//                            Dados a serem cadastrados
//                            nome, foto, email, senha, generoMasculino OR generoFeminino, dataSelecionada, altura, pesoUsuario, meta, "Basico"
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.fire)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                    ) {
                        Text(stringResource(R.string.txt_finalizar_cadastro), color = Color.Black)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionarioAtividadePreview() {
    FittechTheme {
        QuestionarioAtividade(
            nome = "", foto = "", "", "", null, null, "", "", "", "", "",
        )
    }
}