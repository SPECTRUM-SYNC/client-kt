package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.componentes.BotaoVoltarTelaLogin
import spectrum.fittech.ui.theme.FittechTheme

class TelaEsqueceuSenha : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EsqueceuSenha(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsqueceuSenha(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var emailInvalido by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E)),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            // Botão voltar
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFF2C2C2E), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                BotaoVoltarTelaLogin()
            }

        }

        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(id = R.string.txt_esqueceu_senha),
                style = TextStyle(
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.txt_esqueceu_senha_txt),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 32.dp)
            )

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

        }

        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.txt_tentar_outra_maneira),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFFFF3B47)
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }

            Button(
                onClick = {
                    val telaVerificacao = Intent(context, TelaLogin::class.java)

                    context.startActivity(telaVerificacao)
                },
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(50.dp)
                    .width(185.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3B47)
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_entrar),
                        style = TextStyle(color = Color.White)
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

@Preview(showBackground = true)
@Composable
fun EsqueceuSenhaPreview() {
    FittechTheme {
        EsqueceuSenha("Android")
    }
}