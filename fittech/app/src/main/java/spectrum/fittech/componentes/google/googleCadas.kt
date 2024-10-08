package spectrum.fittech.componentes.google

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetError
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetInfo
import spectrum.fittech.Questionario
import spectrum.fittech.R
import spectrum.fittech.backend.dtos.UsuarioLoginGoogle
import spectrum.fittech.backend.viewModel.UsuarioService.UsuarioViewModel

@Composable
fun GoogleSignUpScreen() {
    var account by remember { mutableStateOf<GoogleSignInAccount?>(null) }
    val viewModel: UsuarioViewModel = viewModel()
    val context = LocalContext.current

    var openDialogError by remember { mutableStateOf(false) }
    var openDialogInfo by remember { mutableStateOf(false) }

    var messageResponse by remember { mutableStateOf("") }

    // Configurações do Google Sign-In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    // Launcher para a atividade de login
    val launcher: ActivityResultLauncher<Intent> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                account = task.getResult(ApiException::class.java)
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        account?.let {
            val nome = it.displayName
            val email = it.email
            val foto = it.photoUrl

            LaunchedEffect(email) {
                viewModel.loginUsuarioGoogle(
                    UsuarioLoginGoogle(email, nome),
                    context
                ) { success, message ->
                    if (!success) {

                        val telaQuestionario = Intent(context, Questionario::class.java).apply {
                            putExtra("EXTRA_EMAIL", email)
                            putExtra("EXTRA_SENHA", "Madalena13#")
                            putExtra("EXTRA_NOME", nome)
                            putExtra("EXTRA_FOTO", foto)
                        }
                        context.startActivity(telaQuestionario)
                    } else {
                        openDialogError = true
                        messageResponse = message
                    }

                    googleSignInClient.signOut()
                    account = null
                }
            }

        } ?: run {
            Image(
                painter = painterResource(id = R.mipmap.google),
                contentDescription = "Google",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        val signInIntent: Intent = googleSignInClient.signInIntent
                        launcher.launch(signInIntent)
                    }
            )
        }
    }

    // Exibe o diálogo de erro
    if (openDialogError) {
        openDialogError = false
        SweetError(
            message = "Já existe um usuário com este e-mail.",
            duration = Toast.LENGTH_SHORT,
            padding = PaddingValues(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        )
    }

    // Exibe o diálogo de informação
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