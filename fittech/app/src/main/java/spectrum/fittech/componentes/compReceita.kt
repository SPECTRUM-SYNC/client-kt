package spectrum.fittech.componentes

import android.graphics.fonts.FontStyle
import android.net.Uri
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.R
import spectrum.fittech.backend.builder.gson
import spectrum.fittech.backend.dtos.Receita
import spectrum.fittech.utils.treinos.Treino

@Composable
fun PreviaReceita(
    receita: Receita,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            // Imagem de fundo
            AsyncImage(
                model = receita.img,
                contentDescription = receita.nome,
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
                        Text(
                            text = receita.nome,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = receita.tipo,
                            style = TextStyle(
                                color = colorResource(id = R.color.fire),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

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

                                Text(
                                    text = "${receita.tempoPreparo.split(" ").first()} min",
                                    style = TextStyle(color = Color.White)
                                )
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

                                Text(
                                    text = "${receita.calorias} kcal",
                                    style = TextStyle(color = Color.White)
                                )
                            }
                        }
                    }

                    Text(
                        text = "Igredientes",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth(),
                        style = TextStyle(
                            color = colorResource(R.color.failed),
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                    ) {
                        Text(
                            text = receita.ingredientes.joinToString(", "),
                            style = TextStyle(
                                color = Color.White,
                                textAlign = TextAlign.Start,
                                fontSize = 18.sp
                            )
                        )
                    }


                    Text(
                        text = "Modo de Preparo",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth(),
                        style = TextStyle(
                            color = colorResource(R.color.failed),
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp
                        )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                    ) {
                        Text(
                            text = receita.modoPreparo,
                            style = TextStyle(
                                color = Color.White,
                                textAlign = TextAlign.Start,
                                fontSize = 18.sp
                            )
                        )
                    }

                    Text(
                        text = "Macros e Micros",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth(),
                        style = TextStyle(
                            color = colorResource(R.color.failed),
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp
                        )
                    )

                    PreviewMacro((receita.calorias?.replace("g", "")?.toIntOrNull() ?: 5), "Calorias", 100)
                    PreviewMacro((receita.proteina?.replace("g", "")?.toIntOrNull() ?: 5), "Proteínas", 10)
                    PreviewMacro((receita.carboidratos?.replace("g", "")?.toIntOrNull() ?: 5), "Carboidratos", 10)
                    PreviewMacro((receita.gorduras?.replace("g", "")?.toIntOrNull() ?: 5), "Gorduras", 5)
                    PreviewMacro((receita.acucar?.replace("g", "")?.toIntOrNull() ?: 5), "Açúcar", 5)


                }
            }
        }
    }
}

@Composable
fun PreviewMacro(macroValor: Int, macroNome: String, intervalo: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = macroNome,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp

                )
            )

            Text(
                text = "$macroValor g",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold

                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(6) {
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(50.dp)
                        .background(
                            if (macroValor >= (it + 1) * intervalo) Color.Red else Color.White,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(bottom = 8.dp)
                )
            }

        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}