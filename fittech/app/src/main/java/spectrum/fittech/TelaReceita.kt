package spectrum.fittech.ui.receita

import ReceitaViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.dtos.Receita
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.ui.theme.FittechTheme
import java.time.format.TextStyle

class TelaReceita : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Receita") {
                    composable("Receita") {
                        ReceitaRun(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReceitaRun(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: UsuarioViewModel = viewModel(),
    receitaViewModel: ReceitaViewModel = viewModel()
) {
    var showPopup by remember { mutableStateOf(false) }
    val receitas by receitaViewModel.receitas.observeAsState(initial = emptyList())
    var qtdRefeicoes by remember { mutableStateOf(1) }
    val context = LocalContext.current
    var usuarioGet by remember { mutableStateOf<UsuarioGet?>(null) }

    LaunchedEffect(viewModel) {
        usuarioGet = viewModel.obterUsuario(
            IdUserManager.getId(context),
            token = TokenManager.getToken(context)
        )
    }

    val usuarioLogado: Long? = usuarioGet?.id?.toLong()
    val objetivoUsuario: String? = usuarioGet?.objetivo.toString()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, modifier, "Receita", context) },
        modifier = Modifier.navigationBarsPadding()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(Color(0xFF1C1C1E))
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cabeçalho e botão
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Suas Refeições de Hoje:",
                    fontSize = 20.sp,
                    modifier = Modifier .padding(top = 16.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color(0xFF2C2C2E), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { showPopup = true }) {
                        Text("+", color = Color.White, fontSize = 24.sp)
                    }
                }
            }

            // Lista de receitas
            if (receitas.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(receitas) { receita ->
                        ReceitaCard(receita = receita)
                    }
                }
            } else {
                Text(
                    text = "Nenhuma receita criada.",
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )

            }
        }

        // Popup para geração de receitas
        if (showPopup) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { showPopup = false },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF1C1C1E), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Escolha o número de refeições:",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Button(onClick = { if (qtdRefeicoes > 1) qtdRefeicoes-- }) {
                                Text("-")
                            }
                            Text(text = "$qtdRefeicoes", fontSize = 18.sp, modifier = Modifier .padding(top = 12.dp), color = Color.White)
                            Button(onClick = { if (qtdRefeicoes < 5) qtdRefeicoes++ }) {
                                Text("+")
                            }

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                showPopup = false
                                if (usuarioLogado != null && objetivoUsuario != null) {
                                    val token = TokenManager.getToken(context)

                                    if (!token.isNullOrEmpty()) {
                                        receitaViewModel.fetchReceitas(
                                            usuarioLogado!!,
                                            objetivoUsuario,
                                            qtdRefeicoes,
                                            token
                                        )
                                    } else {
                                        Toast.makeText(context, "Token inválido ou não encontrado", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6E77))
                        ) {
                            Text("Gerar Receitas")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReceitaCard(receita: Receita) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFF2C2C2E), RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = receita.nome, color = Color.White, fontSize = 20.sp)
            Text(
                text = "Calorias: ${receita.calorias}, Tempo: ${receita.tempoDePreparo} min",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Text(text = "Tipo: ${receita.tipo}", color = Color.Gray, fontSize = 16.sp)
        }
    }
}