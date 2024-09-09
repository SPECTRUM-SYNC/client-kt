package spectrum.fittech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.ui.theme.FittechTheme
import java.text.MessageFormat.Field
import androidx.compose.ui.layout.ContentScale

class TelaLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FittechTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    TelaLog (
                        name = "Dalva",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaLog(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var emailInvalido by remember { mutableStateOf(false) }
    var senha by remember { mutableStateOf("") }
    var senhaInvalida by remember { mutableStateOf(false) }
    var textWidth by remember { mutableStateOf(0f) }

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
                        .height(450.dp)
                        .clip(
                            GenericShape { size, _ ->
                                moveTo(0f, 0f)
                                lineTo(size.width, 0f)
                                lineTo(size.width, size.height * 0.9f)
                                lineTo(0f, size.height)
                                close()
                            }
                        )

                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.backgroundlogin),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
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
                                    text = "Login",
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
                                text = "Cadastrar",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            )
                        }

                        // Imagem com borda circular
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .border(
                                    BorderStroke(1.dp, Color.White),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.dalva),
                                contentDescription = "Dalva",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                        }
                    }

                    // Coluna contendo textos
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(top = 300.dp)
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(
                            text = "Bem-vinda de volta,",
                            style = TextStyle(
                                fontSize = 32.sp,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "$name",
                            style = TextStyle(
                                fontSize = 32.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
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
                label = { Text("Email") },
                value = email,
                onValueChange = { digitadoEmail ->
                    email = digitadoEmail
                    emailInvalido = !digitadoEmail.contains("@")
                },
                isError = emailInvalido,
                supportingText = {
                    if (emailInvalido) {
                        Text(text = "O e-mail inserido está inválido.")
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
                    focusedIndicatorColor =Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Campo de senha
            TextField(
                label = { Text("Senha") },
                value = senha,
                onValueChange = { digitadaSenha ->
                    senha = digitadaSenha
                    senhaInvalida = digitadaSenha.length < 3
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = senhaInvalida,
                supportingText = {
                    if (senhaInvalida) {
                        Text(text = "A senha inserida está inválida.")
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
                    focusedIndicatorColor =Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Texto "Esqueci a Senha"
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Esqueci a Senha",
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

            Button(
                onClick = { /* Ação ao clicar */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3B47)
                )
            ) {
                Text(text = "Próximo")
                Image(
                    painter = painterResource(id = R.mipmap.setadireita),
                    contentDescription = "Seta Direita",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Login() {
    FittechTheme {
        TelaLog("Dalva")
    }
}