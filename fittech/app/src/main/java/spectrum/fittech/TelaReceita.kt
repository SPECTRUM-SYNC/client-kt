package spectrum.fittech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import spectrum.fittech.componentes.BottomNavigationBar
import spectrum.fittech.componentes.TelaRankingPerfil
import spectrum.fittech.ui.theme.FittechTheme

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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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