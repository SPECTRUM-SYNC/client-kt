import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.R
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.saudacaoAtual
import spectrum.fittech.ui.theme.FittechTheme
import spectrum.fittech.utils.receita.receitas
import spectrum.fittech.utils.treinos.ganharMassa
import spectrum.fittech.utils.treinos.opcoesTreinos

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
fun ReceitaRun(modifier: Modifier = Modifier, navController: NavHostController) {
    var showPopup by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, modifier, "Receita") },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Saudações
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 20.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.txt_h1_receita),
                    style = TextStyle(
                        fontSize = 26.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )


                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color(0xFF2C2C2E), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        showPopup = true
                    }) {
                        Icon(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("android.resource://spectrum.fittech/raw/have")
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

            // Plano de Treino
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                LazyColumn(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height(900.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(receitas.size) { index ->
                        val receita = receitas[index]
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)

                        ) {
                            AsyncImage(
                                model = receita.image,
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
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                                    .padding(8.dp)
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
                                    text = receita.horario,
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


    if (showPopup) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { showPopup = false }
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Aqui podemos gerar a receitinha do usuario!", fontSize = 18.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            showPopup = false
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.fire)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                    ) {
                        Text("Fechar", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReceitaPreview() {
    FittechTheme {
        ReceitaRun(
            navController = rememberNavController()
        )
    }
}