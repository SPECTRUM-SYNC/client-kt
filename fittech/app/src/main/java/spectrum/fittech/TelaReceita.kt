package spectrum.fittech.ui.receita

import ReceitaViewModel
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import spectrum.fittech.R
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.builder.gson
import spectrum.fittech.backend.dtos.Receita
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.componentes.PreviaReceita
import spectrum.fittech.ui.theme.FittechTheme
import spectrum.fittech.utils.treinos.ganharMassa

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

                    composable("previaReceita/{jsonReceita}") { backStackEntry ->
                        val jsonReceita = backStackEntry.arguments?.getString("jsonReceita")?.let {
                            Uri.decode(it)
                        }

                        val receita = jsonReceita?.let {
                            gson.fromJson(it, Receita::class.java)
                        }

                        if (receita != null) {
                            PreviaReceita(
                                navController = navController,
                                receita = receita
                            )
                        }
                    }


                }
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ReceitaRun(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: UsuarioViewModel = viewModel(),
    receitaViewModel: ReceitaViewModel = viewModel()
) {
    var showPopup by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var usuarioGet by remember { mutableStateOf<UsuarioGet?>(null) }
    val listarReceita = remember { mutableStateListOf<Receita>() }

    val isLoading = remember { mutableStateOf(true) } // Controle de carregamento
    val isLoadingPost = remember { mutableStateOf(false) } // Controle de cadastro
    val errorMessage = remember { mutableStateOf<String?>(null) } // Mensagem de erro

    LaunchedEffect(viewModel) {
        val token = TokenManager.getToken(context)
        val userId = IdUserManager.getId(context)

        try {
            // Obter usuário
            usuarioGet = viewModel.obterUsuario(userId, token)

            // Obter receitas
            receitaViewModel.listarReceitas(token = token, id = userId)?.let {
                listarReceita.clear()
                listarReceita.addAll(it)
                isLoading.value = false
            } ?: run {
                errorMessage.value = "Erro ao carregar receitas"
                isLoading.value = false
            }
        } catch (e: Exception) {
            errorMessage.value = "Falha na conexão. Tente novamente."
            isLoading.value = false
        }
    }
    val usuarioLogado: Long? = usuarioGet?.id?.toLong()
    val objetivoUsuario: String? = usuarioGet?.objetivo.toString()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier,
                "Receita",
                context
            )
        },
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
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Refeições Recomendadas para você:",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(1f),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .fillMaxSize()
                        .background(Color(0xFF2C2C2E), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { showPopup = true }) {
                        Text("+", color = Color.White, fontSize = 24.sp)
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

            }
            // Lista de receitas
            if (isLoading.value) {

                CircularProgressIndicator(color = Color.White)
            } else if (errorMessage.value != null) {
                Text(
                    text = errorMessage.value ?: "Erro desconhecido",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            } else if (listarReceita.isNotEmpty()) {

                LazyColumn(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height(900.dp)
                        .fillMaxWidth()
                        .padding(28.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Suas receitas", color = Color.White, fontSize = 20.sp,
                            modifier = Modifier.padding(start = 32.dp)
                        )
                    }

                    items(listarReceita) { receita ->
                        ReceitaCard(receita = receita, navController = navController)
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
    }

    // Popup para geração de receitas
    if (showPopup) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { if (!isLoadingPost.value) showPopup = false },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF1C1C1E), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Criaremos cinco receitas para você:",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Spacer(modifier = Modifier.height(16.dp))
                    if (isLoadingPost.value) {
                        CircularProgressIndicator(color = Color.White)

                    } else {
                        Button(
                            onClick = {
                                isLoadingPost.value = true

                                if (usuarioLogado != null && objetivoUsuario != null) {
                                    val token = TokenManager.getToken(context)

                                    if (!token.isNullOrEmpty()) {
                                        GlobalScope.launch {
                                            val receitaPost = receitaViewModel.fetchReceitas(
                                                usuarioLogado!!,
                                                objetivoUsuario,
                                                token
                                            )

                                            withContext(Dispatchers.Main) {
                                                if (!receitaPost.isNullOrEmpty()) {
                                                    Toast.makeText(
                                                        context,
                                                        "Receitas geradas com sucesso",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    listarReceita.clear()
                                                    listarReceita.addAll(receitaPost)
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Erro ao gerar receitas",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }

                                                isLoading.value = false
                                                showPopup = false
                                            }
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Token inválido",
                                            Toast.LENGTH_SHORT
                                        ).show()
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
fun ReceitaCard(receita: Receita, navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable {
                val jsonReceita = gson.toJson(receita)
                val encodedJson = Uri.encode(jsonReceita)
                navController.navigate("previaReceita/$encodedJson")
            }


    ) {
        AsyncImage(
            model = receita.img,
            contentDescription = receita.nome,
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
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .padding(8.dp)
        ) {
            Icon(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data("android.resource://spectrum.fittech/raw/info")
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                ),
                contentDescription = "Info",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .padding(8.dp)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = receita.nome,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = receita.tipo,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFFFF6E77)
                )
            )


            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(40.dp)
                        .background(
                            colorResource(R.color.background_card),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("android.resource://spectrum.fittech/raw/hours")
                                    .decoderFactory(SvgDecoder.Factory())
                                    .build()
                            ),
                            contentDescription = "tempo",
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(R.color.day)
                        )

                        Text(
                            text = "${receita.tempoPreparo.split(" ").first()} min",
                            style = TextStyle(color = Color.White)
                        )
                    }
                }


                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(40.dp)
                        .background(
                            colorResource(R.color.background_card),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("android.resource://spectrum.fittech/raw/kcal")
                                    .decoderFactory(SvgDecoder.Factory())
                                    .build()
                            ),
                            contentDescription = "tempo",
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(R.color.fire)
                        )

                        Text(
                            text = "${receita.calorias} kcal",
                            style = TextStyle(color = Color.White)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReceitaRunPreview() {
    ReceitaRun(
        modifier = Modifier,
        navController = rememberNavController(),
    )
}
