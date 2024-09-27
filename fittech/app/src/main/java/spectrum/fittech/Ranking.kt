package spectrum.fittech

import android.os.Bundle
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.componentes.RankingUser
import spectrum.fittech.componentes.TelaRankingPerfil
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
fun RankingRun(modifier: Modifier = Modifier, navController: NavHostController) {

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, modifier, "Ranking") },
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
                    Image(
                        painter = painterResource(id = R.mipmap.dalva),
                        contentDescription = "user",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = "Dalva Anjos",
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(start = 25.dp)
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
                    text = stringResource(id = R.string.texto_ranking),
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                Text(
                    text = "5",
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
                    text = "575",
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = colorResource(id = R.color.failed)
                    ),
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            UserLevelProgressBar(level = 3, maxLevel = 100)
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

            //Ranking

            RankingUser(
                navController = navController,
                nome = "Dalva Anjos",
                posicao = 1,
                level = 3,
                maxLevel = 100,
                foto = 0,
                color = colorResource(id = R.color.gold),
                userId = "1"
            )


            RankingUser(
                navController = navController,
                nome = "Catia damasceno",
                posicao = 2,
                level = 7,
                maxLevel = 100,
                foto = 0,
                color = colorResource(id = R.color.silver),
                userId = "2"
            )


            RankingUser(
                navController = navController,
                nome = "Chupa caua",
                posicao = 3,
                level = 3,
                maxLevel = 100,
                foto = 0,
                color = colorResource(id = R.color.bronze),
                userId = "3"
            )

            RankingUser(
                navController = navController,
                nome = "Gabriel me mama",
                posicao = 4,
                level = 3,
                maxLevel = 100,
                foto = 0,
                color = colorResource(id = R.color.platinum),
                userId = "4"
            )

            RankingUser(
                navController = navController,
                nome = "AAAAAiiiiiinnn Diogoooo",
                posicao = 4,
                level = 3,
                maxLevel = 100,
                foto = 0,
                color = colorResource(id = R.color.platinum),
                userId = "5"
            )
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