package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.dtos.AtualizarUsuarioPerfil
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import spectrum.fittech.componentes.BotaoTelaPerfil
import spectrum.fittech.ui.theme.FittechTheme

class TelaEditarPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    TelaEditarPerfilContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaEditarPerfilContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val model: UsuarioViewModel = viewModel() // Obter uma instância do ViewModel

    // State para armazenar o usuário
    var usuarioGet by remember { mutableStateOf<UsuarioGet?>(null) }
    var isLoading by remember { mutableStateOf(true) } // Estado de carregamento

    // Chama a função suspensa dentro de LaunchedEffect
    LaunchedEffect(Unit) {
        // Obter os dados do usuário ao iniciar a tela
        usuarioGet = model.obterUsuario(IdUserManager.getId(context), TokenManager.getToken(context))
        isLoading = false // Atualiza o estado de carregamento
    }

    // Define os campos de texto inicialmente como vazios
    var nome by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var meta by remember { mutableStateOf("") }
    var nivelCondicao by remember { mutableStateOf("") }

    // Preenche os campos apenas se o usuário estiver carregado e se os campos estiverem vazios
    LaunchedEffect(usuarioGet) {
        usuarioGet?.let {
            if (nome.isEmpty()) nome = it.nome
            if (altura.isEmpty()) altura = it.altura?.toString() ?: ""
            if (dataNascimento.isEmpty()) dataNascimento = it.dataNascimento
            if (meta.isEmpty()) meta = it.meta
            if (nivelCondicao.isEmpty()) nivelCondicao = it.nivelCondicao
        }
    }

    Column(
        modifier = modifier
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
            BotaoTelaPerfil()
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagem com borda circular
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .border(BorderStroke(1.dp, Color.Unspecified), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = usuarioGet?.img,
                    contentDescription = "user",
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    error = painterResource(R.mipmap.user),
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Campo de nome
            TextField(
                label = { Text(text = "Nome") },
                value = nome,
                onValueChange = { digitadoNome -> nome = digitadoNome },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Campo de altura
            TextField(
                label = { Text(text = stringResource(id = R.string.txt_altura)) },
                value = altura,
                onValueChange = { digitadoAltura -> altura = digitadoAltura },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Campo de data de nascimento
            TextField(
                label = { Text(text = stringResource(id = R.string.txt_data_nascimento)) },
                value = dataNascimento,
                onValueChange = { digitadoData -> dataNascimento = digitadoData },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )


            // Campo de nível de condição física
            TextField(
                label = { Text(text = stringResource(id = R.string.txt_nivel_condicao)) },
                value = nivelCondicao,
                onValueChange = { digitadoNivelCondicao -> nivelCondicao = digitadoNivelCondicao },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color(0xFFFF3B47),
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val user = AtualizarUsuarioPerfil(
                        nome = nome,
                        altura = altura.toIntOrNull() ?: 0,
                        dataNascimento = dataNascimento,
                        nivelCondicao = nivelCondicao,
                        meta = meta
                    )

                    // Chamar a função de atualização do perfil
                    model.atualizarUsuarioPerfil(
                        id = IdUserManager.getId(context),  // Assumindo que você está pegando o ID do usuário assim
                        token = TokenManager.getToken(context),  // Obtenha o token do usuário
                        usuarioAtualizarPerfil = user,
                        callback = { success, message ->
                            if (success) {
                                // Atualização bem-sucedida
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                context.startActivity(Intent(context, Home::class.java))
                            } else {
                                // Falha na atualização
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(185.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B47))
            ) {
                Text(text = "Salvar", style = TextStyle(color = Color.White))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaEditarPerfil() {
    FittechTheme {
        TelaEditarPerfilContent()
    }
}
