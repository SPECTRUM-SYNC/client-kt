package spectrum.fittech

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.coroutines.async
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.componentes.RankingUser
import spectrum.fittech.componentes.UserLevelProgressBar
import spectrum.fittech.ui.theme.FittechTheme

class Ranking : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Ranking") {
                    composable("Ranking") {
                        RankingRun(
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
fun RankingRun(
    viewModel: UsuarioViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val context = LocalContext.current
    val calcularNivel =
        ((viewModel.getUsuarioGet().value?.pontuacao ?: 0).toDouble() / 100).toInt() + 1
    val progressBar = viewModel.getUsuarioGet().value?.pontuacao ?: 0 % 100


    LaunchedEffect(viewModel) {
        try {
            val obterUsuario = async {
                viewModel.obterUsuario(
                    IdUserManager.getId(context),
                    token = TokenManager.getToken(context)
                )
            }
            val obterRanking =
                async { viewModel.obterRankUsuariosTop3(token = TokenManager.getToken(context)) }

            obterUsuario.await()
            obterRanking.await()
        } catch (e: Exception) {
            Log.d("Ranking", "RankingRun: ${e.message}")
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier,
                "Ranking",
                context
            )
        },
        modifier = modifier.navigationBarsPadding()
    ) { innerPadding ->

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .border(
                            BorderStroke(1.dp, Color.Unspecified),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = viewModel.getUsuarioGet().value?.img ?: R.mipmap.user,
                        contentDescription = "user",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                }

                viewModel.getUsuarioGet().value?.nome?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .padding(start = 25.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.texto_ranking),
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    ),
                )

                Text(
                    text = (calcularNivel).toString(),
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = colorResource(id = R.color.failed),
                    ),
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.texto_ranking_ponto),
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                Text(
                    text = (viewModel.getUsuarioGet().value?.pontuacao ?: 0).toInt().toString(),
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = colorResource(id = R.color.failed)
                    ),
                )

            }
            Spacer(modifier = Modifier.height(12.dp))
            UserLevelProgressBar(level = progressBar.toInt(), maxLevel = 100)

            //Ranking
            if (viewModel.getUsuarioListGet().isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.texto_ranking_h1),
                        style = TextStyle(
                            fontSize = 22.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
                RankingUser(
                    navController = navController,
                    nome = viewModel.getUsuarioListGet()[0].nome,
                    posicao = 1,
                    level = ((viewModel.getUsuarioListGet()[0].pontuacao
                        ?: 0).toDouble() / 100).toInt() + 1,
                    maxLevel = viewModel.getUsuarioListGet()[0].pontuacao.toInt(),
                    foto = viewModel.getUsuarioListGet()[0].img,
                    color = colorResource(id = R.color.gold),
                    userId = viewModel.getUsuarioListGet()[0].id.toString()
                )

                RankingUser(
                    navController = navController,
                    nome = viewModel.getUsuarioListGet()[1].nome,
                    posicao = 2,
                    level = ((viewModel.getUsuarioListGet()[1].pontuacao
                        ?: 0).toDouble() / 100).toInt() + 1,
                    maxLevel = viewModel.getUsuarioListGet()[1].pontuacao.toInt(),
                    foto = R.mipmap.dalva.toString(),
                            color = colorResource(id = R.color.silver),
                    userId = viewModel.getUsuarioListGet()[1].id.toString()
                )

                RankingUser(
                    navController = navController,
                    nome = viewModel.getUsuarioListGet()[2].nome,
                    posicao = 3,
                    level = ((viewModel.getUsuarioListGet()[2].pontuacao
                        ?: 0).toDouble() / 100).toInt() + 1,
                    maxLevel = viewModel.getUsuarioListGet()[2].pontuacao.toInt(),
                    foto = R.mipmap.dalva.toString(),
                    color = colorResource(id = R.color.bronze),
                    userId = viewModel.getUsuarioListGet()[2].id.toString()
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RankingPreview() {
    FittechTheme {
        RankingRun(
            navController = rememberNavController()

        )
    }
}