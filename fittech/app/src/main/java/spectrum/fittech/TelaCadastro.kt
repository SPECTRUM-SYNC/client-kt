package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import spectrum.fittech.componentes.google.GoogleSignUpScreen
import spectrum.fittech.ui.theme.FittechTheme

class TelaCadastro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FittechTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    TelaCad(
                        name = "Novato",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCad(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var emailInvalido by remember { mutableStateOf(false) }
    var senha by remember { mutableStateOf("") }
    var senhaInvalida by remember { mutableStateOf(false) }
    var nome by remember { mutableStateOf("") }
    var nomeInvalida by remember { mutableStateOf(false) }
    var senhaRepetida by remember { mutableStateOf("") }
    var senhaInvalidaRepetida by remember { mutableStateOf(false) }
    var textWidth by remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
    val senhaValidaRegex =
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{8,}")
    val context = LocalContext.current

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
                painter = painterResource(id = R.mipmap.backgroundcadastro),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.5f),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_login),
                        modifier = Modifier.clickable {
                            val telaLogin = Intent(context, TelaLogin::class.java)
                            context.startActivity(telaLogin)
                        },
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )

                    Column {
                        Text(
                            text = stringResource(id = R.string.txt_cadastrar),
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
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(top = with(LocalConfiguration.current) { screenHeightDp.dp * 0.33f })
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.txt_ola, name),
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = stringResource(id = R.string.txt_entre_com_info),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState())

        ) {
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

            TextField(
                label = { Text(stringResource(id = R.string.ipt_nome)) },
                value = nome,
                onValueChange = { digitadoNome ->
                    nome = digitadoNome
                    nomeInvalida = digitadoNome.isNotBlank() && digitadoNome.length < 3
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isError = nomeInvalida,
                supportingText = {
                    if (nomeInvalida) {
                        Text(text = stringResource(id = R.string.ipt_nome_invalida))
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
                        Text(text = stringResource(id = R.string.ipt_senha_invalida))
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

            TextField(
                label = { Text(stringResource(id = R.string.ipt_senha_novamente)) },
                value = senhaRepetida,
                onValueChange = { digitadaSenhaRepetida ->
                    senhaRepetida = digitadaSenhaRepetida
                    senhaInvalidaRepetida = senha != senhaRepetida || senha.length < 8
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = senhaInvalidaRepetida,
                supportingText = {
                    if (senhaInvalidaRepetida) {
                        Text(text = stringResource(id = R.string.ipt_senha_nao_correspondem))
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

            // Botões de Cadastro
            Row(
                modifier = Modifier
                    .fillMaxSize()
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
                    GoogleSignUpScreen()
                }

                Button(
                    onClick = {
                        // Verificação de campos preenchidos
                        if (email.isNotEmpty() && !emailInvalido &&
                            senha.isNotEmpty() && !senhaInvalida &&
                            senhaRepetida.isNotEmpty() && !senhaInvalidaRepetida &&
                            nome.isNotEmpty() && !nomeInvalida
                        ) {

                            val telaQuestionario = Intent(context, Questionario::class.java).apply {
                                putExtra("EXTRA_EMAIL", email)
                                putExtra("EXTRA_SENHA", senha)
                                putExtra("EXTRA_NOME", nome)
                                putExtra("EXTRA_FOTO", "")
                            }
                            context.startActivity(telaQuestionario)

                        } else {
                            Toast.makeText(
                                context,
                                "Por favor, preencha todos os campos corretamente.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF3B47)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.btn_cadastrar),
                            style = TextStyle(
                                color = Color.White,
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
}

@Preview(showBackground = true)
@Composable
fun TelaCadastroPreview() {
    FittechTheme {
        TelaCad("Novato")
    }
}