package spectrum.fittech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.viewModel.TreinoService.TreinoViewModel
import spectrum.fittech.componentes.ModalDescanso
import spectrum.fittech.componentes.ModalFinal
import spectrum.fittech.componentes.ModalInfoExercicio
import spectrum.fittech.ui.theme.FittechTheme
import spectrum.fittech.utils.treinos.Treino

class TelaVideo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VideoRun(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController

                    )
                }
            }
        }
    }
}

@Composable
fun VideoRun(
    listaTreino: List<Treino>? = null,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    treinoVw: TreinoViewModel = viewModel()
) {

    val context = LocalContext.current

    var treinoDaVez by remember { mutableIntStateOf(0) };

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components { add(GifDecoder.Factory()) }.build()


    val showDialogInfo = remember { mutableStateOf(false) }
    val showDialogDescanso = remember { mutableStateOf(false) }
    val showDialogFim = remember { mutableStateOf(false) }


    // Modal informações
    if (showDialogInfo.value) {
        listaTreino?.get(treinoDaVez)?.let {
            ModalInfoExercicio(
                showDialogInfo, it

            )
        }
    }

    // Modal descanso
    if (showDialogDescanso.value) {
        listaTreino?.get(treinoDaVez)?.let {
            ModalDescanso(
                showDialogDescanso, it, treinoDaVez, listaTreino.size

            )
        }
    }

    // Modal fim
    if (showDialogFim.value) {
        listaTreino?.get(treinoDaVez)?.let {
            ModalFinal(
                showDialogDescanso, navController

            )
        }
    }


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            if (listaTreino != null) {
                AsyncImage(
                    model = listaTreino.get(treinoDaVez).backgroundImage,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),
                    contentScale = ContentScale.Crop,
                    imageLoader = imageLoader
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(Color(0xFF2C2C2E), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("android.resource://spectrum.fittech/raw/setaesquerda")
                                        .decoderFactory(SvgDecoder.Factory())
                                        .build()
                                ),
                                contentDescription = "Ranking",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }
                    }
                }

//                Segunda column
                Column(
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(
                                topStart = 40.dp,
                                topEnd = 40.dp
                            )
                        )
                        .clip(
                            RoundedCornerShape(
                                topStart = 40.dp,
                                topEnd = 40.dp
                            )
                        )
                        .background(colorResource(R.color.background_card_dark))
                        .padding(horizontal = 32.dp)
                        .padding(innerPadding)
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (listaTreino != null) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = listaTreino[treinoDaVez].nome,
                                modifier = Modifier.clickable {
                                    showDialogInfo.value = true
                                },
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline,
                                    fontSize = 25.sp
                                )
                            )

                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("android.resource://spectrum.fittech/raw/info")
                                        .decoderFactory(SvgDecoder.Factory())
                                        .build()
                                ),
                                contentDescription = "informação",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        showDialogInfo.value = true
                                    },
                                tint = Color.White
                            )
                        }
                    }

                    if (listaTreino != null) {
                        Text(
                            text = listaTreino[treinoDaVez].repeticao,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        )
                    }

                    if (listaTreino != null) {
                        if (listaTreino.size == (treinoDaVez + 1)) {

                            Button(
                                onClick = {
                                    treinoVw.atualizarStatusTreino(
                                        id = IdUserManager.getId(context),
                                        token = TokenManager.getToken(context),
                                        listaTreino = listaTreino
                                    )
                                    showDialogFim.value = true;

                                },
                                colors = ButtonDefaults.buttonColors(colorResource(R.color.success)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                            ) {
                                Text(
                                    stringResource(id = R.string.txt_button_finalizar_treino),
                                    color = Color.White
                                )
                            }
                        } else {
                            Button(
                                onClick = {
                                    treinoDaVez++
                                    showDialogDescanso.value = true
                                },
                                colors = ButtonDefaults.buttonColors(colorResource(R.color.fire)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                            ) {
                                Text(
                                    stringResource(id = R.string.txt_button_exercicio_concluido),
                                    color = Color.White
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
fun TelaVideoPreview() {
    FittechTheme {
        VideoRun(
            navController = rememberNavController()
        )
    }
}