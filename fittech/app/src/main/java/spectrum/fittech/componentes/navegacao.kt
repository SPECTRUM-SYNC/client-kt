package spectrum.fittech.componentes

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.R
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel


// Barra de navegação
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier,
    telaAtual: String,
    context: Context
) {
    val viewModel: UsuarioViewModel = viewModel() // Obter uma instância do ViewModel

    var usuarioGet by remember { mutableStateOf<UsuarioGet?>(null) }


    // LaunchedEffect para executar a função apenas uma vez
    LaunchedEffect(viewModel) {
        usuarioGet =  viewModel.obterUsuario(IdUserManager.getId(context), token = TokenManager.getToken(context))
        IdUserManager.saveUserName(context, usuarioGet?.nome)
        IdUserManager.saveUserPic(context, usuarioGet?.img)
    }


    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFF2C2C2E))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1C1C1E))
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("Home") }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/home")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp),
                        tint = if (telaAtual == "Home") Color.White else Color.DarkGray
                    )
                }

                IconButton(onClick = { navController.navigate("Ranking") }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/ranking")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Ranking",
                        modifier = Modifier.size(24.dp),
                        tint = if (telaAtual == "Ranking") Color.White else Color.DarkGray
                    )
                }

                IconButton(onClick = { navController.navigate("TelaGraficos") }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/dashboards")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Dashboards",
                        modifier = Modifier.size(24.dp),
                        tint = if (telaAtual == "Dashboards") Color.White else Color.DarkGray
                    )
                }

                IconButton(onClick = { navController.navigate("Receita") }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/nutri")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Receita",
                        modifier = Modifier.size(24.dp),
                        tint = if (telaAtual == "Receita") Color.White else Color.DarkGray
                    )
                }

                IconButton(onClick = { navController.navigate("TelaPerfil") }) {
                    AsyncImage(
                        model = usuarioGet?.img ?: R.mipmap.user,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)

                    )
                }
            }
        }
    }
}