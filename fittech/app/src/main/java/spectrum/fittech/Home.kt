package spectrum.fittech

import ReceitaRun
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.google.gson.Gson
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetError
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetSuccess
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.builder.gson
import spectrum.fittech.backend.dtos.DiasTreino
import spectrum.fittech.backend.dtos.TreinoResponseDto
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.viewModel.TreinoService.TreinoViewModel
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.componentes.PreviaTreino
import spectrum.fittech.componentes.TelaRankingPerfil
import spectrum.fittech.ui.theme.FittechTheme
import spectrum.fittech.utils.treinos.Treino
import spectrum.fittech.utils.treinos.ganharMassa
import spectrum.fittech.utils.treinos.opcoesTreinos
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
fun HomeRun(
    treinoVw: TreinoViewModel = viewModel(),
    usuarioVw: UsuarioViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val gson = Gson()
    val context = LocalContext.current
    val showPopup = remember { mutableStateOf(false) }

    val dias = listOf("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado")
    val checkedStates =
        remember { mutableStateListOf<Boolean>().apply { repeat(dias.size) { add(false) } } }

    var openDialogSuccess by remember { mutableStateOf(false) }
    var openDialogError by remember { mutableStateOf(false) }

    var messageResponse by remember { mutableStateOf("") }

    var usuarioGet by remember { mutableStateOf<UsuarioGet?>(null) }
    var treinoPrincipalGet by remember { mutableStateOf<TreinoResponseDto?>(null) }

    val listaTreino = remember { mutableStateListOf<TreinoResponseDto>() }

    var processarTreino by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier,
                "Home",
                context
            )
        },
        modifier = modifier
            .navigationBarsPadding()
            .clickable(enabled = showPopup.value) { }
    ) { innerPadding ->
        val dataAtual = Calendar.getInstance().time
        val dataFormat = SimpleDateFormat("EEE dd MMM", Locale("pt", "BR"))
        val dataFormatada = dataFormat.format(dataAtual)

        LaunchedEffect(treinoVw) {
            val token = TokenManager.getToken(context)
            val userId = IdUserManager.getId(context)

            usuarioGet = usuarioVw.obterUsuario(userId, token = token)
            treinoPrincipalGet = treinoVw.validarTreino(token = token, id = userId)

            treinoVw.listarTodosTreinosDoDia(token = token, id = userId)?.let {
                listaTreino.clear()
                listaTreino.addAll(it)
            }

            if (treinoVw.verificarTreino(token, userId) == false) {
                showPopup.value = true
            }
        }


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

                Spacer(modifier = Modifier.height(16.dp))

                if (treinoPrincipalGet != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable(enabled = treinoPrincipalGet!!.status == "Treino") {
                                val jsonListaTreino = gson.toJson(ganharMassa)
                                val encodedNomeTreino = Uri.encode("Treino Diário")
                                val encodedJson = Uri.encode(jsonListaTreino)
                                navController.navigate("PreviaTreino/$encodedNomeTreino/$encodedJson")
                            }
                    ) {

                        Image(
                            painter = if (treinoPrincipalGet!!.status == "Treino" || treinoPrincipalGet!!.status == "Feito") painterResource(
                                id = R.mipmap.esteira
                            ) else painterResource(id = R.mipmap.descanso),
                            contentDescription = "Esteira",
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
                                text = if (treinoPrincipalGet!!.status == "Treino" || treinoPrincipalGet!!.status == "Feito") "Fortalecimento  Muscular" else "Dia de descanso",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Treino Diário",
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
                                    text = if (treinoPrincipalGet!!.status == "Treino") "A Realizar" else if (treinoPrincipalGet!!.status == "Feito") "Você já fez seu treino hoje!" else "Dia de descansar os musculos",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = if (treinoPrincipalGet!!.status == "Treino") Color.Magenta else if (treinoPrincipalGet!!.status == "Feito") Color.Green else Color.White,
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

                if (listaTreino.isNotEmpty()) {
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
                                    .clickable(
                                        enabled = !(listaTreino.any { it.tipoTreino == treino.nome })
                                    ) {
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
                                            text = if (listaTreino.any { it.tipoTreino == treino.nome }) "Concluído" else "A Realizar",
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                color = if (listaTreino.any { it.tipoTreino == treino.nome }) Color.Green else Color.Magenta,
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

    if (showPopup.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(enabled = false) { }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.7f)
                    .align(Alignment.Center)
                    .background(
                        colorResource(R.color.background_card),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp),


                ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(0.dp, 10.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.criacao_treino),
                            fontSize = 18.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = stringResource(R.string.criacao_treino_h2),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = stringResource(R.string.criacao_treino_h2_max2),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    Column {
                        dias.chunked(2).forEach { diasAgrupados ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                diasAgrupados.forEachIndexed { index, dia ->
                                    val globalIndex = dias.indexOf(dia)
                                    Box(
                                        modifier = Modifier
                                            .weight(0.8f)
                                            .size(80.dp)
                                            .padding(10.dp)
                                            .border(
                                                BorderStroke(
                                                    2.dp,
                                                    if (checkedStates[globalIndex]) Color.Green else Color.Gray
                                                ),
                                            )
                                            .clickable {
                                                if (checkedStates.count { it } >= 2 && !checkedStates[globalIndex]) {
                                                    return@clickable
                                                }
                                                checkedStates[globalIndex] =
                                                    !checkedStates[globalIndex]
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            modifier = modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                painter = rememberAsyncImagePainter(
                                                    model = ImageRequest.Builder(LocalContext.current)
                                                        .data(if (checkedStates[globalIndex]) "android.resource://spectrum.fittech/raw/correct" else "android.resource://spectrum.fittech/raw/circle")
                                                        .decoderFactory(SvgDecoder.Factory())
                                                        .build()
                                                ),
                                                contentDescription = "Home",
                                                modifier = Modifier.size(12.dp),
                                                tint = if (checkedStates[globalIndex]) Color.Green else Color.Gray
                                            )

                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = dia,
                                                color = if (checkedStates[globalIndex]) Color.Green else Color.White,
                                                fontWeight = if (checkedStates[globalIndex]) FontWeight.Bold else FontWeight.Normal
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            enabled = checkedStates.count { it } > 0 || processarTreino,
                            onClick = {
                                usuarioGet?.let {
                                    processarTreino = true
                                    treinoVw.cadastrarTreino(
                                        token = TokenManager.getToken(context),
                                        dias = checkedStates,
                                        meta = it.meta,
                                        usuarioId = IdUserManager.getId(context),
                                    ) { success, message ->
                                        if (success) {
                                            openDialogSuccess = true
                                            messageResponse = message
                                        } else {
                                            openDialogError = true
                                            messageResponse = message
                                        }
                                    }
                                }

                                Thread.sleep(3000)
                                navController.navigate("TelaGraficos")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF3B47)
                            ),
                            modifier = Modifier
                                .wrapContentSize()
                                .fillMaxWidth()
                        ) {
                            Text(text = if (processarTreino) stringResource(R.string.processando_treino) else stringResource(R.string.btn_proximo), color = Color.White)
                            Image(
                                painter = painterResource(id = R.mipmap.setadireita),
                                contentDescription = "Seta Direita",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    if (openDialogSuccess) {
        SweetSuccess(
            message = messageResponse,
            duration = Toast.LENGTH_SHORT,
            padding = PaddingValues(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        )

        Handler(Looper.getMainLooper()).postDelayed({
            val home = Intent(context, Home::class.java)
            context.startActivity(home)
        }, 2000)

        openDialogSuccess = false
    }

// Toast de erro
    if (openDialogError) {
        openDialogError = false
        SweetError(
            message = messageResponse,
            duration = Toast.LENGTH_SHORT,
            padding = PaddingValues(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        )
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
