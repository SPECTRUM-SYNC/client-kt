package spectrum.fittech.componentes

import android.net.Uri
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import spectrum.fittech.R
import spectrum.fittech.backend.builder.gson
import spectrum.fittech.utils.treinos.Treino

@Composable
fun PreviaTreino(
    nomeTreino: String? = null,
    listaTreino: List<Treino>? = null,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {


    // Lista de IDs de recursos de imagem
    val backgroundImages = listOf(
        R.mipmap.flexao,
        R.mipmap.esteira,
        R.mipmap.alongamento
    )

    val currentBackground = remember { mutableStateOf(backgroundImages.random()) }


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            // Imagem de fundo
            Image(
                painter = painterResource(currentBackground.value),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                contentScale = ContentScale.Crop
            )

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
                        .fillMaxHeight(0.8f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                    ) {
                        nomeTreino?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        if (listaTreino != null) {
                            Text(
                                text = stringResource(R.string.txt_home_treino, listaTreino.size),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = colorResource(id = R.color.failed)
                                )
                            )
                        }
                    }

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(30.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .width(110.dp)
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
                                    .padding(10.dp),
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

                                Text(text = "60 min", style = TextStyle(color = Color.White))
                            }
                        }


                        Box(
                            modifier = Modifier
                                .width(110.dp)
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
                                    .padding(10.dp),
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
                                    modifier = Modifier.size(24.dp),
                                    tint = colorResource(R.color.fire)
                                )

                                Text(text = "350 kcal", style = TextStyle(color = Color.White))
                            }
                        }
                    }

                    Text(
                        text = stringResource(R.string.txt_sobre_treino),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                    ) {
                        Text(
                            text = when (nomeTreino) {
                                "Treino Diário" -> stringResource(R.string.txt_tipo_treino_diario)
                                "Musculação" -> stringResource(R.string.txt_tipo_musculacao)
                                "Alongamento" -> stringResource(R.string.txt_tipo_alongamento)
                                else -> stringResource(R.string.txt_tipo_geral)
                            },
                            style = TextStyle(
                                color = Color.White,
                                textAlign = TextAlign.Start,
                                fontSize = 18.sp
                            )
                        )
                    }

                    Text(
                        text = stringResource(R.string.txt_lista_treino),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    )

                    LazyColumn(
                        modifier = modifier
                            .height(400.dp)
                            .fillMaxWidth()
                    ) {
                        if (listaTreino != null) {
                            items(count = listaTreino.size) { index ->
                                val treino = listaTreino[index]


                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                        .shadow(
                                            elevation = 10.dp,
                                            shape = RoundedCornerShape(13.dp)
                                        )
                                        .clip(RoundedCornerShape(13.dp))
                                        .background(color = colorResource(id = R.color.background_card)),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = treino.image,
                                        contentDescription = treino.nome,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(0.4f)
                                            .clip(RoundedCornerShape(13.dp))
                                            .shadow(
                                                8.dp,
                                                shape = RoundedCornerShape(13.dp)
                                            ),
                                        contentScale = ContentScale.Crop
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            text = treino.nome,
                                            style = TextStyle(color = Color.White),
                                            fontSize = 18.sp
                                        )
                                        Text(
                                            text = treino.repeticao,
                                            style = TextStyle(
                                                color = colorResource(R.color.failed),
                                                fontSize = 15.sp
                                            )
                                        )
                                    }
                                }


                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val jsonListaTreino = gson.toJson(listaTreino)
                            val encodedJson = Uri.encode(jsonListaTreino)
                            navController.navigate("ExercicioEmExecucao/$encodedJson")
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.fire)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                    ) {
                        Text(stringResource(id = R.string.txt_button_iniciar_treino))
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
        }
    }
}


// Modal para visualização das informações sobre determinado exercicio
@Composable
fun ModalInfoExercicio(isDialogOpen: MutableState<Boolean>, exercicio: Treino) {

    Dialog(onDismissRequest = { isDialogOpen.value = false }) {
        Box(
            modifier = Modifier
                .height(425.dp)
                .fillMaxWidth()
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(25.dp))
                .background(color = colorResource(id = R.color.background_card))
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = exercicio.nome,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_treino_info_serie),
                        style = TextStyle(
                            color = colorResource(R.color.failed),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = exercicio.repeticao,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_treino_info_instrucao),
                        style = TextStyle(
                            color = colorResource(R.color.failed),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                }
                Text(
                    text = exercicio.tecnica,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_treino_info_beneficio),
                        style = TextStyle(
                            color = colorResource(R.color.failed),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                }
                Text(
                    text = exercicio.beneficios,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )


                Row {
                    Button(
                        onClick = { isDialogOpen.value = false },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.fire)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                    ) {
                        Text(stringResource(id = R.string.txt_button_fechar))
                    }
                }
            }
        }
    }
}


@Composable
fun ModalDescanso(
    isDialogOpen: MutableState<Boolean>,
    exercicio: Treino,
    exercicioVez: Int,
    exercicioTotal: Int
) {
    var timerValue by remember { mutableStateOf(20) }
    var isTimerRunning by remember { mutableStateOf(false) }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(isTimerRunning) {
        while (timerValue > 0) {
            delay(1000)
            timerValue--
        }
        isTimerRunning = false
        isDialogOpen.value = false
    }

    Dialog(onDismissRequest = { isDialogOpen.value = false }) {
        Box(
            modifier = Modifier
                .height(425.dp)
                .fillMaxWidth()
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(25.dp))
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background_card)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_treino_descanso),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    UserLevelProgressBar(exercicioVez, exercicioTotal)
                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_proximo_treino),
                            style = TextStyle(
                                color = colorResource(R.color.failed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = exercicio.nome,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    LaunchedEffect(timerValue) {
                        scale.animateTo(1.2f, animationSpec = tween(durationMillis = 200))
                        scale.animateTo(1f, animationSpec = tween(durationMillis = 200))
                    }

                    Text(
                        text = "00:$timerValue",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp
                        ),
                        modifier = Modifier.scale(scale.value)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        onClick = {
                            timerValue += 20
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.day)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                    ) {
                        Text(
                            stringResource(id = R.string.txt_button_descanso),
                            color = Color.DarkGray
                        )
                    }

                    Button(
                        onClick = {
                            isDialogOpen.value = false
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.fire)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                    ) {
                        Text(
                            stringResource(id = R.string.txt_button_fechar),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ModalFinal(isDialogOpen: MutableState<Boolean>, navController: NavHostController) {
    var timerValue by remember { mutableStateOf(7) }
    var isTimerRunning by remember { mutableStateOf(false) }

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components { add(GifDecoder.Factory()) }.build()

    LaunchedEffect(isTimerRunning) {
        while (timerValue > 0) {
            delay(1000)
            timerValue--
        }
        isTimerRunning = false
        isDialogOpen.value = false
        navController.navigate("home")
    }

    Dialog(onDismissRequest = { isDialogOpen.value = false }) {
        Box(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(25.dp))
                .fillMaxSize()
                .background(color = colorResource(id = R.color.finaly)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = R.mipmap.girafa,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    imageLoader = imageLoader
                )

                Spacer(modifier = Modifier.height(8.dp))
                UserLevelProgressBar(10, 10)
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_parabens),
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                }
            }
        }
    }
}

