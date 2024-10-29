package spectrum.fittech

import android.content.Intent
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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.ui.theme.FittechTheme
import android.content.res.Configuration
import java.util.Locale

class TelaIdioma : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    Idioma(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

fun setLocale(activity: ComponentActivity, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = Configuration(activity.resources.configuration)
    config.setLocale(locale)

    activity.resources.updateConfiguration(config, activity.resources.displayMetrics)

    // Recria a Activity para aplicar a alteração de idioma
    activity.recreate()
}

@Composable
fun Idioma(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val idiomas = listOf(
        context.getString(R.string.idioma_portugues),
        context.getString(R.string.idioma_ingles),
        context.getString(R.string.idioma_espanhol),
        context.getString(R.string.idioma_chines),
        context.getString(R.string.idioma_japones),
        context.getString(R.string.idioma_frances),
        context.getString(R.string.idioma_alemao),
        context.getString(R.string.idioma_russo),
        context.getString(R.string.idioma_italiano),
        context.getString(R.string.idioma_polones),
        context.getString(R.string.idioma_holandes),
        context.getString(R.string.idioma_coreano),
        context.getString(R.string.idioma_arabe),
        context.getString(R.string.idioma_catalao),
        context.getString(R.string.idioma_grego),
        context.getString(R.string.idioma_basco),
        context.getString(R.string.idioma_turco),
        context.getString(R.string.idioma_vietnamita),
        context.getString(R.string.idioma_malaio),
        context.getString(R.string.idioma_esperanto),
        context.getString(R.string.idioma_tailandes)
    )

    val textState = remember { mutableStateOf("") }
    val selectedLanguage = remember { mutableStateOf<String?>("Português") }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    // Filtrar idiomas conforme a pesquisa
    val filteredLanguages = idiomas.filter {
        it.contains(textState.value, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão voltar
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFF2C2C2E), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = {
                    val conf = Intent(context, TelaConfiguracao::class.java)
                    context.startActivity(conf)
                }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/setaesquerda")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.txt_idioma),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(horizontal = screenWidth * 0.22f)
            )

        }

        // Barra de pesquisa
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text(stringResource(id = R.string.txt_pesquisar)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 32.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            leadingIcon = {
                if (textState.value.isEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Ícone de Pesquisa",
                        tint = Color.Gray
                    )
                }
            }
        )

        // Lista de idiomas filtrados
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            filteredLanguages.forEach { idioma ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 16.dp)
                        .clickable {
                            selectedLanguage.value = idioma
                            // Alteração de idioma
                            if (idioma == context.getString(R.string.idioma_ingles)) {
                                setLocale(context as ComponentActivity, "en")
                            } else if (idioma == context.getString(R.string.idioma_portugues)) {
                                setLocale(context as ComponentActivity, "pt")
                            }
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = idioma,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )

                    // Exibir ícone de seleção apenas no idioma selecionado
                    if (selectedLanguage.value == idioma) {
                        Icon(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("android.resource://spectrum.fittech/raw/caixaselecao")
                                    .decoderFactory(SvgDecoder.Factory())
                                    .build()
                            ),
                            contentDescription = "Caixa Seleção",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Unspecified
                        )
                    }
                }

                // Linha separadora
                Divider(color = Color(0xFF2C2C2E), thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IdiomaPreview() {
    FittechTheme {
        Idioma()
    }
}