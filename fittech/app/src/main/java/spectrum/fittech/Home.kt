package spectrum.fittech

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.componentes.TelaRankingPerfil
import spectrum.fittech.ui.theme.FittechTheme
import java.text.SimpleDateFormat
import java.util.Calendar
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
fun HomeRun(modifier: Modifier = Modifier, navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, modifier, "Home") },
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
                    text = "Olá Dalva,",
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
                            text = "Dia 01 - Cárdio",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "07:00 - 08:00 AM",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color(0xFFFF6E77)
                            )
                        )
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

                LazyRow(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(5) { index ->
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .height(200.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.alongamento),
                                contentDescription = "Alongamento",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .alpha(0.5f)
                            )
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "Alongamento ${index + 1}",
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
                            }
                        }
                    }
                }
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
