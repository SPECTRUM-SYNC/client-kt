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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    composable("Home") { HomeRun(
                        name = "Dalva",
                        modifier = Modifier.fillMaxSize(),
                        navController = navController
                    ) }
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
fun HomeRun(name: String, modifier: Modifier = Modifier, navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        val dataAtual = Calendar.getInstance().time
        val dataFormat = SimpleDateFormat("EEE dd MMM", Locale("pt", "BR"))
        val dataFormatada = dataFormat.format(dataAtual)

        // Container rolável
        Column(
            modifier = modifier
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
                modifier = Modifier.padding(top = 48.dp, bottom = 48.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Olá $name,",
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
                        text = "Plano de Treino",
                        style = TextStyle(
                            fontSize = 20.sp,
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
                        modifier = Modifier.fillMaxSize()
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
                    text = "Categorias de Treino",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.flexao),
                        contentDescription = "Flexão",
                        modifier = Modifier.fillMaxSize()
                            .alpha(0.6f)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Aprendendo Treinos Básicos",
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

            // Treinos Extras
            Column {
                Text(
                    text = "Treinos Extras",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.alongamento),
                        contentDescription = "Alongamento",
                        modifier = Modifier.fillMaxSize()
                            .alpha(0.5f)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Alongamento",
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
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(Color(0xFF2C2C2E))
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1C1C1E))
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigate("Home") }
            ) {
                Icon(
                    painter = painterResource(id = R.mipmap.homeselected),
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            }

            IconButton(onClick = { navController.navigate("Home") }) {
                Icon(
                    painter = painterResource(id = R.mipmap.ranking),
                    contentDescription = "Ranking",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }

            IconButton(onClick = { navController.navigate("Home") }) {
                Icon(
                    painter = painterResource(id = R.mipmap.dashboards),
                    contentDescription = "Dashboards",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }

            IconButton(onClick = { navController.navigate("Home") }) {
                Icon(
                    painter = painterResource(id = R.mipmap.nutri),
                    contentDescription = "Nutri",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }

            IconButton(onClick = { navController.navigate("Home") }) {
                Image(
                    painter = painterResource(id = R.mipmap.dalva),
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FittechTheme {
        HomeRun(name = "Dalva", navController = rememberNavController())
    }
}