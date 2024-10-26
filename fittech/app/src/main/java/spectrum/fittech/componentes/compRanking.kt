package spectrum.fittech.componentes

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.async
import spectrum.fittech.R
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import java.time.LocalDate
import java.time.Period


// Barra de progresso
@Composable
fun UserLevelProgressBar(level: Int, maxLevel: Int) {
    val progress = if (maxLevel > 0) level / maxLevel.toFloat() else 0f


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "${(progress * 100).toInt()}%",
            style = TextStyle(
                fontSize = 18.sp,
                color = colorResource(id = R.color.failed)
            ),
        )

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(18.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = colorResource(id = R.color.fire),
        )
    }
}


// card para mostrar os usuarios do ranking
@Composable
fun RankingUser(
    navController: NavController,
    nome: String,
    posicao: Int,
    level: Int,
    maxLevel: Int,
    foto: String?,
    color: Color,
    userId: String

) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                navController.navigate("TelaRankingPerfil/${userId}")
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(55.dp)
                    .offset(x = (20).dp, y = (-5).dp)

                    .border(
                        BorderStroke(2.dp, color),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = foto,
                    contentDescription = "user",
                    error = painterResource(id = R.mipmap.user),
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = stringResource(id = R.string.texto_ranking_posicao, posicao),
                modifier = Modifier.offset(x = (30).dp, y = (6).dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    color = color,
                    fontWeight = FontWeight.SemiBold
                ),
            )

            Text(
                text = nome,
                modifier = Modifier
                    .offset(x = (70).dp, y = (6).dp)
                    .fillMaxWidth(0.5f),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                ),
            )

            Text(
                text = stringResource(id = R.string.texto_ranking_posicao_ponto, maxLevel, level),
                modifier = Modifier.offset(x = (90).dp, y = (6).dp),
                style = TextStyle(
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    color = color,
                ),
            )
        }
    }
}


@Composable
fun TelaRankingPerfil(
    viewModel: UsuarioViewModel = viewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier,
    userId: String?
) {
    var usuarioGet by remember { mutableStateOf<UsuarioGet?>(null) }

    val context = LocalContext.current
    val calcularNivel =
        ((usuarioGet?.pontuacao ?: 0).toDouble() / 100).toInt() + 1
    val progressBar = usuarioGet?.pontuacao ?: (0 % 100)

    LaunchedEffect(viewModel) {
        try {
            val obterUsuario = async {
                if (userId != null) {
                    usuarioGet = viewModel.obterUsuario(
                        userId.toInt(), token = TokenManager.getToken(context)
                    )
                }
            }

            obterUsuario.await()
        } catch (e: Exception) {
            Log.d("Ranking", "RankingRun: ${e.message}")
        }
    }

    val dataNascimento = usuarioGet?.dataNascimento ?: ""
    val idade = if (dataNascimento.isNotEmpty()) {
        val nascimento = LocalDate.parse(dataNascimento)
        val hoje = LocalDate.now()
        Period.between(nascimento, hoje).years
    } else {
        0
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1C1C1E)),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            // Botão voltar
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
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
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
                            model = usuarioGet?.img ?: R.mipmap.user,
                            contentDescription = "user",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                        )
                    }

                    usuarioGet?.let {
                        Text(
                            text = it.nome,
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

                UserLevelProgressBar(level = progressBar.toInt(), maxLevel = 100)

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
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

                    Spacer(modifier = Modifier.width(10.dp))

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
                    horizontalArrangement = Arrangement.Start,
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = (usuarioGet?.pontuacao ?: 0).toInt().toString(),
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = colorResource(id = R.color.failed)
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.texto_ranking_idade),
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text(
                        text = "$idade",
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = colorResource(id = R.color.failed)
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.texto_ranking_objetivo),
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "${usuarioGet?.objetivo?.objetivo}",
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = colorResource(id = R.color.failed)
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.texto_ranking_meta),
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text(
                        text = usuarioGet?.meta?.replace(
                            Regex("([a-z])([A-Z])"),
                            "$1 $2"
                        ) ?: "Nenhuma meta definida",
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = colorResource(id = R.color.failed)
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.texto_ranking_treino),
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text(
                        text = "Meditação",
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = colorResource(id = R.color.failed)
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}



