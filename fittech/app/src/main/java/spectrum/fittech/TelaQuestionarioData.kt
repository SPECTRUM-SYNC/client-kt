package spectrum.fittech

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.componentes.BotaoQuestionarioData
import spectrum.fittech.ui.theme.FittechTheme
import java.util.Calendar

class TelaQuestionarioData : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val senha = intent.getStringExtra("EXTRA_SENHA")
        val generoMasculino = intent.getStringExtra("MASCULINO_SELECIONADO")
        val generoFeminino = intent.getStringExtra("FEMININO_SELECIONADO")
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    QuestionarioData(
                        name = "Android",
                        email = email,
                        senha = senha,
                        generoMasculino = generoMasculino,
                        generoFeminino = generoFeminino,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionarioData(name: String, email: String?, senha: String?, generoMasculino: String?, generoFeminino: String?, modifier: Modifier = Modifier) {
    var selectedDate by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = TextFieldValue("$dayOfMonth/${month + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(vertical = 32.dp)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título e descrições
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Qual a sua idade?",
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Isso nos ajuda a criar seu plano personalizado",
                style = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White
                )
            )

        }

        // Seção de escolha de data
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = { Text("Selecionar Data de Nascimento") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface
                ),
                trailingIcon = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "Selecionar Data")
                    }
                }
            )
        }

        // Botão "Próximo"
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            BotaoQuestionarioData()

            Button(
                onClick = {
                    if (selectedDate.text.isNotEmpty()) {
                        val telaQuestionarioPeso = Intent(context, TelaQuestionarioPeso()::class.java).apply {
                            putExtra("MASCULINO_SELECIONADO", generoMasculino)
                            putExtra("FEMININO_SELECIONADO", generoFeminino)
                            putExtra("EXTRA_EMAIL", email)
                            putExtra("EXTRA_SENHA", senha)
                            putExtra("DATA_SELECIONADA", selectedDate.text)
                        }
                        context.startActivity(telaQuestionarioPeso)
                    }else{
                        Toast.makeText(context, "Por favor, selecione uma data de nascimento para avançar.", Toast.LENGTH_SHORT).show()
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3B47)
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Bottom)
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
fun QuestionarioDataPreview() {
    FittechTheme {
        QuestionarioData("Android", email = "", senha = "", generoMasculino = "", generoFeminino = "")
    }
}