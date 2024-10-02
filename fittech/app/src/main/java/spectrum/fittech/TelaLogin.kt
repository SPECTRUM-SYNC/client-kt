package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetError
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetInfo
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetSuccess
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.dtos.UsuarioLogin
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel
import spectrum.fittech.ui.theme.FittechTheme

class TelaLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FittechTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    TelaLog(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaLog(viewModel: UsuarioViewModel = viewModel(), modifier: Modifier = Modifier) {


    var email by remember { mutableStateOf("") }
    var emailInvalido by remember { mutableStateOf(false) }
    var senha by remember { mutableStateOf("") }
    var senhaInvalida by remember { mutableStateOf(false) }
    var textWidth by remember { mutableStateOf(0f) }
    val senhaValidaRegex =
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{8,}")

    val context = LocalContext.current


    var openDialogSuccess by remember { mutableStateOf(false) }
    var openDialogError by remember { mutableStateOf(false) }
    var openDialogInfo by remember { mutableStateOf(false) }

    var messageResponse by remember { mutableStateOf("") }



    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalConfiguration.current) { screenHeightDp.dp * 0.5f })
                .clip(
                    GenericShape { size, _ ->
                        moveTo(0f, 0f)
                        lineTo(size.width, 0f)
                        lineTo(size.width, size.height * 0.8f)
                        lineTo(0f, size.height)
                        close()
                    }
                )

        ) {
            Image(
                painter = painterResource(id = R.mipmap.backgroundlogin),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                contentScale = ContentScale.Crop
            )

            // Conteúdo da tela (Rows e Columns)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Primeiro Row contendo Login e Cadastrar
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.txt_login),
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            modifier = Modifier.onGloballyPositioned { coordinates: LayoutCoordinates ->
                                textWidth = coordinates.size.width.toFloat()
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .width(with(LocalDensity.current) { textWidth.toDp() })
                                .height(4.dp)
                                .background(Color(0xFFFF3B47), shape = RectangleShape)
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.txt_cadastrar),
                        modifier = Modifier.clickable {
                            val telaCadastro = Intent(context, TelaCadastro::class.java)
                            context.startActivity(telaCadastro)
                        },
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                }
            }

            // Coluna contendo textos
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(top = with(LocalConfiguration.current) { screenHeightDp.dp * 0.33f })
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.txt_bem_vindo_login),
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = Color.White
                    )
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            // Campo de email
            TextField(
                label = { Text(stringResource(id = R.string.ipt_email)) },
                value = email,
                onValueChange = { digitadoEmail ->
                    email = digitadoEmail
                    emailInvalido = !digitadoEmail.contains("@")
                },
                isError = emailInvalido,
                supportingText = {
                    if (emailInvalido) {
                        Text(text = stringResource(id = R.string.ipt_email_invalido))
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color.White,
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Campo de senha
            TextField(
                label = { Text(stringResource(id = R.string.ipt_senha)) },
                value = senha,
                onValueChange = { digitadaSenha ->
                    senha = digitadaSenha
                    senhaInvalida = !senhaValidaRegex.matches(digitadaSenha)
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = senhaInvalida,
                supportingText = {
                    if (senhaInvalida) {
                        Text(text = stringResource(id = R.string.ipt_senha_invalida_login))
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color.White,
                    cursorColor = Color(0xFFFF3B47),
                    focusedLabelColor = Color(0xFFFF3B47),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Texto "Esqueci a Senha"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val telaEsqueceuSenha = Intent(context, TelaEsqueceuSenha::class.java)

                        context.startActivity(telaEsqueceuSenha)
                    },
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.txt_esqueci_senha),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFFFF3B47)
                    )
                )
            }
        }

        // Botões "Login"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp)
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2C2C2E))
                    .clickable { }
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.google),
                    contentDescription = "Google",
                    modifier = Modifier.size(24.dp)
                )
            }

            if (email.isNotEmpty() && senhaValidaRegex.matches(senha)) {
                Button(
                    onClick = {
                        openDialogInfo = true
                        viewModel.loginUsuario(
                            UsuarioLogin(email, senha),
                            context
                        ) { success, message ->
                            if (success) {
                                openDialogSuccess = true
                                messageResponse = message
                            } else {
                                openDialogError = true
                                messageResponse = message
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF3B47)
                    ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.btn_login), style = TextStyle(
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(0.dp))
                        Image(
                            painter = painterResource(id = R.mipmap.setadireita),
                            contentDescription = "Seta Direita",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }

    if (openDialogSuccess) {
        SweetSuccess(
            message = messageResponse,
            duration = Toast.LENGTH_SHORT,
            padding = PaddingValues(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        )

        Handler(Looper.getMainLooper()).postDelayed({
            val home = Intent(context, Home::class.java)
            context.startActivity(home)
        }, 2000)

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

    if (openDialogInfo) {
        openDialogInfo = false
        SweetInfo(
            message = "Analisando dados. Por favor, aguarde...",
            duration = Toast.LENGTH_SHORT,
            padding = PaddingValues(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Login() {
    FittechTheme {
        TelaLog()
    }
}