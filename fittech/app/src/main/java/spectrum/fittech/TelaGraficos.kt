package spectrum.fittech

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.dtos.DiasTreino
import spectrum.fittech.backend.dtos.HistoricoPeso
import spectrum.fittech.backend.dtos.TreinoCountDto
import spectrum.fittech.backend.viewModel.HistoricoPesoService.HistoricoPesoViewModel
import spectrum.fittech.backend.viewModel.TreinoService.TreinoViewModel
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.componentes.DayItem
import spectrum.fittech.componentes.ModalPeso
import spectrum.fittech.componentes.charts.BarChart
import spectrum.fittech.componentes.charts.LineGraph
import spectrum.fittech.ui.theme.FittechTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TelaGraficos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "TelaGraficos") {
                    composable("TelaGraficos") {
                        TelaGraficosRun(
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
fun TelaGraficosRun(
    treinoViewModel: TreinoViewModel = viewModel(),
    historicoPesoViewModel: HistoricoPesoViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val context = LocalContext.current
    val diasDaSemana = listOf("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb")
    val listaTreino = remember { mutableStateListOf<DiasTreino>() }
    val diaAtual = LocalDate.now().dayOfMonth
    val listaCountTreino = remember { mutableStateListOf<TreinoCountDto>() }

    var historicoPesoList by remember { mutableStateOf(emptyList<HistoricoPeso>()) }
    LaunchedEffect(Unit) {

        listaTreino.clear()
        listaCountTreino.clear()

        treinoViewModel.listarTreino(
            token = TokenManager.getToken(context),
            id = IdUserManager.getId(context)
        )?.map { item ->
            val dataTreino = item.dataTreino

            val date = LocalDate.parse(dataTreino, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            // Obtem o dia do mês
            val diaDoMes = date.dayOfMonth

            DiasTreino(
                dataTreino = diasDaSemana[date.dayOfWeek.value % 7],
                diaTreino = diaDoMes.toString(),
                status = item.status,
                diaAtual = diaAtual == diaDoMes
            )
        }?.reversed().let { newList ->
            if (newList != null) {
                listaTreino.addAll(newList)
            }
        }

        treinoViewModel.listarTreinoPorDiaDaSemana(
            token = TokenManager.getToken(context),
            id = IdUserManager.getId(context)
        )?.let { item ->
            listaCountTreino.addAll(item)
        }

        val historicoPesoListResult = historicoPesoViewModel.historicoGrafico(
            token = TokenManager.getToken(context),
            IdUserManager.getId(context)
        )


        historicoPesoList = historicoPesoListResult ?: emptyList()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier,
                "Dashboards",
                context
            )
        },
        modifier = modifier.navigationBarsPadding()
    ) { innerPadding ->

        val showDialog = remember { mutableStateOf(false) }

        // Modal
        if (showDialog.value) {
            ModalPeso(showDialog, IdUserManager.getId(context)!!, TokenManager.getToken(context)!!)
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
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 30.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    text = stringResource(
                        id = R.string.saudacao_grafico,
                        IdUserManager.getUserName(context)!!
                    ),
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.White
                    )
                )
            }

            // Primeiro card geral
            Column {
                Text(
                    text = stringResource(id = R.string.txt_desenvolvimento_geral),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                        .clip(RoundedCornerShape(25.dp))
                        .background(color = colorResource(id = R.color.background_card))
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("android.resource://spectrum.fittech/raw/treino")
                                        .decoderFactory(SvgDecoder.Factory())
                                        .build()
                                ),
                                contentDescription = stringResource(id = R.string.txt_treino),
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.CenterHorizontally),
                                tint = Color.Unspecified
                            )
                            Text(
                                text = stringResource(id = R.string.txt_treino),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "1",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color(0xFFFF6E77)
                                )
                            )
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("android.resource://spectrum.fittech/raw/refeicao")
                                        .decoderFactory(SvgDecoder.Factory())
                                        .build()
                                ),
                                contentDescription = stringResource(id = R.string.txt_refeicao),
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.CenterHorizontally),
                                tint = Color.Unspecified
                            )
                            Text(
                                text = stringResource(id = R.string.txt_refeicao),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "1",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color(0xFFFF6E77)
                                )
                            )
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("android.resource://spectrum.fittech/raw/kcal")
                                        .decoderFactory(SvgDecoder.Factory())
                                        .build()
                                ),
                                contentDescription = stringResource(id = R.string.txt_caloria),
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.CenterHorizontally),
                                tint = colorResource(id = R.color.fire)
                            )
                            Text(
                                text = stringResource(id = R.string.txt_caloria),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "1",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color(0xFFFF6E77)
                                )
                            )
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.txt_historico_treino),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                        .clip(RoundedCornerShape(25.dp))
                        .background(color = colorResource(id = R.color.background_card))
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        if (listaTreino.isNotEmpty()) {
                            listaTreino.forEach { diaTreino ->
                                // Define as propriedades com base no status
                                val (dayName, iconRes, iconTint) = when (diaTreino.status) {

                                    "Descanso" -> Triple(
                                        R.string.txt_segunda,
                                        "android.resource://spectrum.fittech/raw/descanso",
                                        colorResource(id = R.color.rest)
                                    )

                                    "Treino" -> Triple(
                                        R.string.txt_terca,
                                        "android.resource://spectrum.fittech/raw/dia_treino",
                                        Color.White
                                    )

                                    "Feito" -> Triple(
                                        R.string.txt_quinta,
                                        "android.resource://spectrum.fittech/raw/feito",
                                        colorResource(id = R.color.success)
                                    )

                                    else -> Triple(
                                        R.string.txt_sexta,
                                        "android.resource://spectrum.fittech/raw/nao_feito",
                                        colorResource(id = R.color.failed)
                                    )
                                }

                                // Plota o DayItem
                                DayItem(
                                    dayName = diaTreino.dataTreino,
                                    dayNumber = diaTreino.diaTreino,
                                    iconRes = iconRes,
                                    iconTint = iconTint,
                                    isBold = diaTreino.diaAtual
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Gráfico de linhas de peso
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_historico_peso),
                        modifier = modifier.align(Alignment.CenterVertically),
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Button(
                        onClick = {
                            if (historicoPesoList[0].dataPostagem != LocalDate.now().toString()){
                                showDialog.value = true
                                Log.e("peso", historicoPesoList[0].dataPostagem +"Data postagem")
                            }
                            Log.e("peso", "data do histtórico = a data hoje")
                            Toast.makeText(context, "Você já registrou seu peso hoje", Toast.LENGTH_SHORT).show()
                                  },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.fire)),
                        modifier = Modifier
                            .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                    ) {
                        Text(stringResource(id = R.string.txt_button_peso))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                        .clip(RoundedCornerShape(25.dp))
                        .background(color = colorResource(id = R.color.background_card))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .padding(8.dp)
                    ) {
                        val x = historicoPesoList.map {
                            val dataPostagem =
                                LocalDate.parse(
                                    it.dataPostagem,
                                    DateTimeFormatter.ISO_LOCAL_DATE
                                )
                            dataPostagem.format(DateTimeFormatter.ofPattern("dd/MM"))
                        }
                        val y = historicoPesoList.map { it.peso.toFloat() }
                        val y2 = historicoPesoList.map { it.pesoMeta.toFloat() }


                        if (historicoPesoList.isNotEmpty()) {
                            LineGraph(
                                x,
                                y,
                                y2,
                                stringResource(id = R.string.txt_peso),
                                stringResource(id = R.string.txt_peso_ideal),
                                modifier.fillMaxWidth()
                            )
                        } else {
                            Text(text = "Carregando seu histórico de peso... Por favor, aguarde ou registre seu peso caso ainda não tenha feito isso.")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Gráfico de barra sobre os treinos
            Column {
                Text(
                    text = stringResource(id = R.string.txt_historico_treino),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                        .clip(RoundedCornerShape(25.dp))
                        .background(color = colorResource(id = R.color.background_card))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .padding(8.dp)
                    ) {
                        val xLabels = listaCountTreino.map { it.diaDaSemana }
                        val yValues = listaCountTreino.map { it.quantidadeTreinos.toFloat() }

                        if (listaCountTreino.isNotEmpty()) {
                            BarChart(
                                xData = xLabels,
                                yData = yValues,
                                dataLabel = stringResource(id = R.string.txt_dias),
                                modifier.fillMaxWidth()
                            )
                        } else {
                            Text(text = "Carregando seu histórico de treinos da semana...")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    FittechTheme {
        TelaGraficosRun(
            navController = rememberNavController()
        )
    }
}
