package spectrum.fittech.backend.viewModel.UsuarioService

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.dtos.AtualizarUsuario
import spectrum.fittech.backend.dtos.AtualizarUsuarioPerfil
import spectrum.fittech.backend.dtos.EnvioEmailUsuario
import spectrum.fittech.backend.dtos.NovoUsuario
import spectrum.fittech.backend.dtos.RespostaEnvioEmail
import spectrum.fittech.backend.dtos.RespostaLogin
import spectrum.fittech.backend.dtos.RespostaRank
import spectrum.fittech.backend.dtos.RespostaRequisicao
import spectrum.fittech.backend.dtos.Usuario
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.dtos.UsuarioLogin
import spectrum.fittech.backend.dtos.UsuarioLoginGoogle
import spectrum.fittech.backend.interfaces.UsuarioInterface
import spectrum.fittech.retroFit.RetroFitService
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UsuarioViewModel : ViewModel() {

    private var usuarioGet = mutableStateOf<UsuarioGet?>(null)
    fun getUsuarioGet() = usuarioGet


    private val _usuarioListGet = mutableStateOf<List<UsuarioGet>>(emptyList())
    val getUsuarioListGet: State<List<UsuarioGet>> = _usuarioListGet

    private val usuarioApi: UsuarioInterface = RetroFitService.usuarioApi()


    // GET
    // Função para obter usuários em ordem alfabética
    fun obterUsuarioOrdemAlfabetica(token: String?) {
        val call = usuarioApi.obterUsuarioOrdemAlfabetica(token = "Bearer $token")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<List<UsuarioGet>> {
                override fun onResponse(
                    call: Call<List<UsuarioGet>>,
                    response: Response<List<UsuarioGet>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")

                        _usuarioListGet.value = emptyList()
                        _usuarioListGet.value = response.body() ?: emptyList()

                        Log.i("api_info", "usuarios = $_usuarioListGet")
                    } else {
                        Log.e("api_error", "Erro ao realizar consulta Usuario: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<List<UsuarioGet>>, t: Throwable) {
                    Log.e(
                        "api_error",
                        "Falha na obtenção Usuário. Código de resposta: ${t.message}"
                    )
                }
            })
        }
    }

    // Função para obter rank de usuários Top 3
    fun obterRankUsuariosTop3(token: String?) {
        val call = usuarioApi.obterUsuarioPontuacaoTop3(token = "Bearer $token")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<List<UsuarioGet>> {
                override fun onResponse(
                    call: Call<List<UsuarioGet>>,
                    response: Response<List<UsuarioGet>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")

                        _usuarioListGet.value = emptyList()
                        _usuarioListGet.value = response.body() ?: emptyList()

                        Log.i("api_info", "usuarios = $_usuarioListGet")
                    } else {
                        Log.e("api_error", "Erro ao realizar consulta Usuario: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<List<UsuarioGet>>, t: Throwable) {
                    Log.e(
                        "api_error",
                        "Falha na obtenção Usuário. Código de resposta: ${t.message}"
                    )
                }
            })
        }
    }

    // Função para obter usuários com status ativo
    fun obterUsuarioStatusAtivo(contaAtiva: Boolean?, token: String?) {
        val call = usuarioApi.obterStatusUsuario(contaAtiva = contaAtiva, token = "Bearer $token")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<List<UsuarioGet>> {
                override fun onResponse(
                    call: Call<List<UsuarioGet>>,
                    response: Response<List<UsuarioGet>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")

                        _usuarioListGet.value = emptyList()
                        _usuarioListGet.value = response.body() ?: emptyList()

                        Log.i("api_info", "usuarios = $_usuarioListGet")
                    } else {
                        Log.e("api_error", "Erro ao realizar consulta Usuario: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<List<UsuarioGet>>, t: Throwable) {
                    Log.e(
                        "api_error",
                        "Falha na obtenção Usuário. Código de resposta: ${t.message}"
                    )
                }
            })
        }
    }

    // Função para obter usuário por ID
    suspend fun obterUsuario(id: Int?, token: String?): UsuarioGet? {
        return withContext(Dispatchers.IO) {
            try {
                val response = usuarioApi.obterUsuario(id, token = "Bearer $token")
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("api_error", "Erro ao realizar consulta Usuario: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("api_error", "Falha na obtenção Usuário: ${e.message}")
                null
            }
        }
    }
    // POST

    // POST: Função para adicionar ranking
    fun adicionarRanking(usuario: String, token: String) {
        val call = usuarioApi.adicionarUsuarioRank(usuario, "Bearer $token")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRank> {
                override fun onResponse(
                    call: Call<RespostaRank>,
                    response: Response<RespostaRank>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Resposta: ${response.body()}")
                        // quando for usar, criar primeiro o atributo RespostaRank e depois atribui-lo aqui
                    } else {
                        Log.e("api_error", "Erro ao cadastrar no rank: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<RespostaRank>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                }
            })
        }
    }

    // POST: Função para envio de email
    fun envioEmail(usuario: EnvioEmailUsuario) {
        val call = usuarioApi.envioEmailRedefinicao(usuario)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaEnvioEmail> {
                override fun onResponse(
                    call: Call<RespostaEnvioEmail>,
                    response: Response<RespostaEnvioEmail>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Resposta: ${response.body()}")
                    } else {
                        Log.e("api_error", "Erro ao enviar email: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<RespostaEnvioEmail>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                }
            })
        }
    }

    // POST: Função para login de usuário
    // Quando for chamado em uma activity chamar pelo contexto "this"
    fun loginUsuario(usuario: UsuarioLogin, context: Context, callback: (Boolean, String) -> Unit) {
        IdUserManager.clearAll(context = context)
        val call = usuarioApi.loginUsuario(usuario)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaLogin> {
                override fun onResponse(
                    call: Call<RespostaLogin>,
                    response: Response<RespostaLogin>
                ) {
                    if (response.isSuccessful) {

                        val token = response.body()?.token
                        val idUsuario = response.body()?.userId
                        val nome = response.body()?.nome

                        IdUserManager.saveId(context, idUsuario)
                        IdUserManager.saveUserName(context, nome)
                        TokenManager.saveToken(context, token)

                        callback(true, "Login realizado com sucesso!")
                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorResponse)
                        val mensagemFinal = if (errorMessage == "Bad credentials") {
                            "Email ou senha inválidos"
                        } else {
                            errorMessage
                        }

                        callback(false, mensagemFinal)
                    }
                }

                override fun onFailure(call: Call<RespostaLogin>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                    callback(false, "Erro na requisição: ${t.message}")
                }
            })
        }
    }


    // POST: Função para login de usuário Google
    // Quando for chamado em uma activity chamar pelo contexto "this"
    fun loginUsuarioGoogle(
        usuario: UsuarioLoginGoogle,
        context: Context,
        callback: (Boolean, String) -> Unit
    ) {
        val call = usuarioApi.loginUsuarioGoogle(usuario)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaLogin> {
                override fun onResponse(
                    call: Call<RespostaLogin>,
                    response: Response<RespostaLogin>
                ) {
                    if (response.isSuccessful) {

                        val token = response.body()?.token
                        val idUsuario = response.body()?.userId
                        val nome = response.body()?.nome

                        IdUserManager.saveId(context, idUsuario)
                        IdUserManager.saveUserName(context, nome)
                        TokenManager.saveToken(context, token)

                        callback(true, "Login realizado com sucesso!")
                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorResponse)
                        val mensagemFinal = if (errorMessage == "Bad credentials") {
                            "Email ou senha inválidos"
                        } else {
                            errorMessage
                        }

                        callback(false, mensagemFinal)
                    }
                }

                override fun onFailure(call: Call<RespostaLogin>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                    callback(false, "Erro na requisição: ${t.message}")
                }
            })
        }
    }

    // POST: Função para cadastrar usuário
    fun cadastrarUsuario(
        usuario: NovoUsuario?,
    ) {
        if (usuario == null) {
            return
        }

        val usuarioCreate = Usuario(
            email = usuario.email.orEmpty(),
            senha = usuario.senha.orEmpty(),
            img = usuario.foto.orEmpty(),
            nome = usuario.nome.orEmpty()
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val usuarioResponse = usuarioApi.cadastrarUsuario(usuarioCreate).execute()
                if (usuarioResponse.isSuccessful) {
                    val userId = usuarioResponse.body()?.id
                    if (userId != null) {
                        atualizarPerfilUsuario(userId, usuario)

                    } else {
                        Log.e(
                            "api_error",
                            "Erro ao cadastrar usuario: ${usuarioResponse.message()}"
                        )
                    }
                } else {
                    Log.e("api_error", "Erro ao cadastrar usuario: ${usuarioResponse.message()}")
                }
            } catch (e: Exception) {
                logError("api_error", e.message)
            }
        }
    }


    private fun atualizarPerfilUsuario(
        userId: Int,
        usuario: NovoUsuario,
    ) {
        val dateFormatInput = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateFormatOutput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dataNascimentoFormatada = try {
            val date = dateFormatInput.parse(usuario.dataNascimento.orEmpty())
            dateFormatOutput.format(date ?: "")
        } catch (e: Exception) {
            ""
        }

        val usuarioAtualizarPerfil = AtualizarUsuarioPerfil(
            altura = usuario.altura?.toDouble()?.toInt() ?: 0,
            nivelCondicao = usuario.nivelCondicao.orEmpty(),
            dataNascimento = dataNascimentoFormatada,
            meta = usuario.meta,
            nome = usuario.nome.orEmpty()
        )

        try {
            val updateResponse =
                usuarioApi.atualizarUsuarioPerfil(userId, null, usuarioAtualizarPerfil).execute()
            if (updateResponse.isSuccessful) {
                Log.i("api_info", "Resposta: ${updateResponse.body()}")
            } else {
                Log.e(
                    "api_error",
                    "Erro ao atualizar perfil: ${updateResponse.errorBody()?.string()}"
                )
            }
        } catch (e: Exception) {
            logError("api_error", e.message)
        }
    }

    private fun logError(tag: String, message: String?) {
        Log.e(tag, "Erro: ${message.orEmpty()}")
    }

    private fun parseErrorMessage(errorBody: String?): String {
        // Implementação da lógica para interpretar a mensagem de erro
        return errorBody ?: "Erro desconhecido"
    }


    // PATCH
    // PATCH: Função para trocar a foto do usuário
    suspend fun atualizarImagem(
        id: Int?, token: String?, imagem: ByteArray, callback: (Boolean, String) -> Unit
    ) {
        val requestImage = MultipartBody.Part.createFormData(
            "imageFile", "image.jpg", imagem.toRequestBody("image/jpeg".toMediaTypeOrNull())
        )

        val call =
            usuarioApi.atualizarImagem(id = id, token = "Bearer $token", imagem = requestImage)

        withContext(Dispatchers.IO) {
            runCatching {
                suspendCoroutine { continuation ->
                    call.enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                callback(true, "Imagem atualizada com sucesso, ela será atualizada na próxima vez que entrar no aplicativo!")
                            } else {
                                val errorMessage = parseErrorMessage(response.errorBody()?.string())
                                Log.e("api_error", "Erro ao atualizar imagem. Erro: $errorMessage")
                                callback(false, errorMessage)
                            }
                            continuation.resume(Unit)
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                            callback(false, "Erro na requisição: ${t.message}")
                            continuation.resume(Unit)
                        }
                    })
                }
            }.onFailure { throwable ->
                Log.e("api_error", "Erro inesperado: ${throwable.message}")
                callback(false, "Erro inesperado: ${throwable.message}")
            }
        }
    }


    // PUT
    // PUT: Função para atualizar usuário
    fun atualizarUsuario(
        id: Int?,
        token: String?,
        usuario: AtualizarUsuario,
        callback: (Boolean, String) -> Unit
    ) {
        val call = usuarioApi.atualizarUsuario(id = id, token = "Bearer $token", usuario = usuario)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(
                    call: Call<RespostaRequisicao>,
                    response: Response<RespostaRequisicao>
                ) {
                    if (response.isSuccessful) {
                        callback(true, "Usuário atualizado com sucesso!")

                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorResponse)
                        callback(false, errorMessage)
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e(
                        "api_error",
                        "Falha na requisição ao atualizar usuário. Erro: ${t.message}"
                    )
                    callback(false, "Erro na requisição: ${t.message}")

                }
            })
        }
    }

    // PUT: Função para atualizar perfil de usuário
    fun atualizarUsuarioPerfil(
        id: Int?,
        token: String?,
        usuarioAtualizarPerfil: AtualizarUsuarioPerfil,
        callback: (Boolean, String) -> Unit
    ) {
        val call = usuarioApi.atualizarUsuarioPerfil(
            id = id,
            token = "Bearer $token",
            usuario = usuarioAtualizarPerfil
        )

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(
                    call: Call<RespostaRequisicao>,
                    response: Response<RespostaRequisicao>
                ) {
                    if (response.isSuccessful) {
                        callback(true, "Perfil do usuário atualizado com sucesso!")
                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorResponse)
                        callback(false, errorMessage)
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e(
                        "api_error",
                        "Falha na requisição ao atualizar perfil. Erro: ${t.message}"
                    )
                    callback(false, "Erro na requisição: ${t.message}")
                }
            })
        }
    }


    // PATCH: Função para ativar usuário
    fun ativarUsuario(
        id: Int?,
        usuario: String,
        token: String?,
        callback: (Boolean, String) -> Unit
    ) {
        val call = usuarioApi.ativarUsuario(id, usuario, token = "Bearer $token")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(
                    call: Call<RespostaRequisicao>,
                    response: Response<RespostaRequisicao>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Usuário ativado com sucesso: ${response.body()}")
                        callback(true, "Usuário ativado com sucesso")

                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorResponse)
                        callback(false, errorMessage)
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                    callback(false, "Erro na requisição: ${t.message}")

                }
            })
        }
    }

    // DELETE
    fun inativarUsuario(
        id: Int?, token: String?, callback: (Boolean, String) -> Unit
    ) {
        if (id == null || token.isNullOrEmpty()) {
            Log.e("api_error", "ID ou Token inválidos.")
            return
        }

        val call = usuarioApi.inativarUsuario(id = id, token = "Bearer $token")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(
                    call: Call<RespostaRequisicao>,
                    response: Response<RespostaRequisicao>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")
                        callback(true, "Conta inativada com sucesso!")

                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorResponse)
                        callback(false, errorMessage)
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição: ${t.message}")
                    callback(false, "Erro na requisição: ${t.message}")

                }
            })
        }
    }

    fun excluirUsuario(id: Int?, token: String?, callback: (Boolean, String) -> Unit) {
        if (id == null || token.isNullOrEmpty()) {
            Log.e("api_error", "ID ou Token inválidos.")
            return
        }

        val call = usuarioApi.excluirUsuario(id = id, token = "Bearer $token")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(
                    call: Call<RespostaRequisicao>,
                    response: Response<RespostaRequisicao>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")
                        callback(true, "Conta excluida com sucesso!")

                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorResponse)
                        callback(false, errorMessage)
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição: ${t.message}")
                    callback(false, "Erro na requisição: ${t.message}")
                }
            })
        }
    }
}