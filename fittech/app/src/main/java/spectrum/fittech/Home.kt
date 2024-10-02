package spectrum.fittech

import ReceitaRun
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.builder.gson
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.componentes.ModalDescanso
import spectrum.fittech.componentes.ModalFinal
import spectrum.fittech.componentes.ModalPeso
import spectrum.fittech.componentes.PreviaTreino
import spectrum.fittech.componentes.TelaRankingPerfil
import spectrum.fittech.componentes.UserLevelProgressBar
import spectrum.fittech.ui.theme.FittechTheme
import spectrum.fittech.utils.treinos.Treino
import spectrum.fittech.utils.treinos.ganharMassa
import spectrum.fittech.utils.treinos.opcoesTreinos
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Home") {
                    composable("Home") {
                        HomeRun(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController
                        )
                    }
                    composable("TelaGraficos") { TelaGraficosRun(navController = navController) }
                    composable("Ranking") { RankingRun(navController = navController) }
                    composable("TelaPerfil") { TelaPer() }
                    composable("Receita") { ReceitaRun(navController = navController) }

                    composable("PreviaTreino/{nomeTreino}/{listaTreino}") { backStackEntry ->
                        val nomeTreino = backStackEntry.arguments?.getString("nomeTreino")
                        val jsonListaTreino = backStackEntry.arguments?.getString("listaTreino")

                        val listaTreino = jsonListaTreino?.let {
                            gson.fromJson(it, Array<Treino>::class.java).toList()
                        } ?: emptyList()

                        PreviaTreino(
                            navController = navController,
                            nomeTreino = nomeTreino,
                            listaTreino = listaTreino
                        )
                    }

                    composable("ExercicioEmExecucao/{listaTreino}") { backStackEntry ->
                        val jsonListaTreino = backStackEntry.arguments?.getString("listaTreino")

                        val listaTreino = jsonListaTreino?.let {
                            gson.fromJson(it, Array<Treino>::class.java).toList()
                        } ?: emptyList()

                        VideoRun(
                            navController = navController,
                            listaTreino = listaTreino
                        )
                    }
                    composable("TelaRankingPerfil/{userId}") { backStackEntry ->
                        TelaRankingPerfil(
                            navController = navController,
                            userId = backStackEntry.arguments?.getString("userId")
                        )
                    }
                }
            }
        }
    }
}

fun saudacaoAtual(): String {
    val horaAtual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    return when {
        horaAtual in 18..23 || horaAtual in 0..5 -> "Boa noite."
        horaAtual in 5..11 -> "Bom dia."
        else -> "Boa tarde."
    }
}

@Composable
fun HomeRun(viewModel: UsuarioViewModel = viewModel(), modifier: Modifier = Modifier, navController: NavHostController) {
    val gson = Gson()
    val context = LocalContext.current

    // LaunchedEffect para executar a função apenas uma vez
    LaunchedEffect(Unit) {
        viewModel.obterUsuario(IdUserManager.getId(context), token = TokenManager.getToken(context))
        IdUserManager.saveUserPic(context, viewModel.getUsuarioGet().value?.img)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, modifier, "Home", context) },
        modifier = modifier.navigationBarsPadding()
    ) { innerPadding ->
        val dataAtual = Calendar.getInstance().time
        val dataFormat = SimpleDateFormat("EEE dd MMM", Locale("pt", "BR"))
        val dataFormatada = dataFormat.format(dataAtual)

        // Container rolável
        Column(
            modifier = Modifier
                .background(Color(0xFF1C1C1E))
                .padding(horizontal = 32.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Saudações
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 48.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Olá ${IdUserManager.getUserName(LocalContext.current)},",
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = saudacaoAtual(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
            }

            // Plano de Treino
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_plano_treino),

                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )

                    Text(
                        text = dataFormatada,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFFFF3B47)
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable {
                            val jsonListaTreino = gson.toJson(ganharMassa)
                            val encodedNomeTreino = Uri.encode("Treino Diário")
                            val encodedJson = Uri.encode(jsonListaTreino)
                            navController.navigate("PreviaTreino/$encodedNomeTreino/$encodedJson")
                        }

                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.esteira),
                        contentDescription = "Esteira",
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.6f)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_treino_diario),
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "30 MINUTOS",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color(0xFFFF6E77)
                            )
                        )

                        Row {
                            Text(
                                text = "STATUS:",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.White
                                )
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "EM ANDAMENTO",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Yellow
                                )
                            )
                        }
                    }
                }
            }

            // Categorias de Treino
            Column {
                Text(
                    text = stringResource(id = R.string.txt_categorias_treino),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                LazyRow(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { index ->
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .height(200.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.flexao),
                                contentDescription = "Flexão",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .alpha(0.6f)
                            )
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "Aprendendo Treinos Básicos ${index + 1}",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "Treinos para Iniciantes",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = Color(0xFFFF6E77)
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Treinos Extras
            Column {
                Text(
                    text = stringResource(id = R.string.txt_treinos_extras),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(opcoesTreinos.size) { index ->
                        val treino = opcoesTreinos[index]

                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .height(200.dp)
                                .clickable {
                                    val jsonListaTreino = gson.toJson(treino.treinos)
                                    val encodedNomeTreino = Uri.encode(treino.nome)
                                    val encodedJson = Uri.encode(jsonListaTreino)
                                    navController.navigate("PreviaTreino/$encodedNomeTreino/$encodedJson")
                                }
                        ) {
                            AsyncImage(
                                model = treino.image,
                                contentDescription = treino.nome,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.9f)
                                    .alpha(0.5f)
                                    .clip(RoundedCornerShape(18.dp))
                                    .shadow(
                                        8.dp,
                                        shape = RoundedCornerShape(18.dp)
                                    ), contentScale = ContentScale.Crop

                            )
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = treino.nome,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "Treino Extra",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = Color(0xFFFF6E77)
                                    )
                                )

                                Row {
                                    Text(
                                        text = "STATUS:",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            color = Color.White
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "CONCLUIDO",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            color = Color.Green
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGraficos() {
    FittechTheme {
        HomeRun(
            navController = rememberNavController()
        )
    }
}
