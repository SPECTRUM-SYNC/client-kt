package spectrum.fittech

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetError
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import spectrum.fittech.ui.theme.FittechTheme

class TelaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaPer(viewModel: UsuarioViewModel = viewModel(), modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var usuarioGet by remember { mutableStateOf<UsuarioGet?>(null) }
    var openDialogSuccess by remember { mutableStateOf(false) }
    var openDialogError by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    var messageResponse by remember { mutableStateOf("") }
    var carregado by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        // Atualiza imageUri quando uma imagem é selecionada
        imageUri = uri
    }

    LaunchedEffect(viewModel) {
        usuarioGet = viewModel.obterUsuario(
            IdUserManager.getId(context),
            token = TokenManager.getToken(context)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            // Botão voltar
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFF2C2C2E), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = {
                    val home = Intent(context, Home::class.java)
                    context.startActivity(home)
                }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(context)
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
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .border(BorderStroke(1.dp, Color.Gray), shape = CircleShape)
                    .clickable { pickImageLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUri ?: usuarioGet?.img,
                    contentDescription = "user",
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    error = painterResource(R.mipmap.user),
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.BottomEnd)
                        .background(Color(0xFF2C2C2E), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(context)
                                .data("android.resource://spectrum.fittech/raw/camera")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Camera",
                        modifier = Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            }

            if (imageUri != null) {

                Button(
                    enabled = !carregado,
                    onClick = {
                        carregado = true
                        coroutineScope.launch {
                            val inputStream = context.contentResolver.openInputStream(imageUri!!)
                            val imageBytes = inputStream?.use { it.readBytes() }

                            if (imageBytes != null) {
                                viewModel.atualizarImagem(
                                    id = IdUserManager.getId(context),
                                    token = TokenManager.getToken(context),
                                    imagem = imageBytes
                                ) { success, message ->
                                    if (success) {
                                        openDialogSuccess = true
                                        messageResponse = message
                                    } else {
                                        openDialogError = true
                                        messageResponse = message
                                    }

                                    coroutineScope.launch {
                                        delay(6000)
                                        usuarioGet = viewModel.obterUsuario(
                                            IdUserManager.getId(context),
                                            token = TokenManager.getToken(context)
                                        )
                                        IdUserManager.saveUserPic(context, usuarioGet?.img)
                                        imageUri = null
                                        carregado = false
                                    }
                                }
                            } else {
                                openDialogError = true
                                messageResponse = "Erro ao ler a imagem"
                                carregado = false
                            }
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = if (carregado) "Processando imagem..." else "Salvar imagem")
                }
            }

        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // Primeira linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )

            // Primeira Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 8.dp)
                    .clickable {
                        val editarPerfil = Intent(context, TelaEditarPerfil::class.java)
                        context.startActivity(editarPerfil)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.txt_editar_perfil),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/setadireita")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Editar Perfil",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }

            // Segunda linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )

            // Segunda Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 8.dp)
                    .clickable {
                        val politica = Intent(context, TelaPoliticaPrivacidade::class.java)
                        context.startActivity(politica)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.txt_politica_privacidade),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/setadireita")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Politica de Privacidade",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }

            // Terceira linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )

            // Terceira Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 8.dp)
                    .clickable {
                        val conf = Intent(context, TelaConfiguracao::class.java)
                        context.startActivity(conf)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.txt_configuracoes),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("android.resource://spectrum.fittech/raw/setadireita")
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        ),
                        contentDescription = "Configuracoes",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }

            // Quarta linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )
        }


        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // Primeira linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )

            // Row Sair
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.txt_sair),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFF2424),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable {
                        //   Limpa o token e nome do usuário
                        IdUserManager.clearAll(context = context)

                        val telaLogin = Intent(context, TelaLogin::class.java)
                        context.startActivity(telaLogin)
                    }
                )
            }

            // Segunda linha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF2C2C2E))
            )
        }

    }

    if (openDialogSuccess) {
        SweetSuccess(
            message = messageResponse,
            duration = Toast.LENGTH_SHORT,
            padding = PaddingValues(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        )

        openDialogSuccess = false
    }

// Toast de erro
    if (openDialogError) {
        openDialogError = false
        SweetError(
            message = messageResponse,
            duration = Toast.LENGTH_SHORT,
            padding = PaddingValues(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Perfil() {
    FittechTheme {
        TelaPer()
    }
}