package spectrum.fittech.backend.viewModel.UsuarioService

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import spectrum.fittech.backend.auth.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.backend.dtos.AtualizarUsuario
import spectrum.fittech.backend.dtos.AtualizarUsuarioPerfil
import spectrum.fittech.backend.dtos.EnvioEmailUsuario
import spectrum.fittech.backend.dtos.RespostaCadastro
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

class UsuarioViewModel : ViewModel() {

    private var usuarioGet = UsuarioGet();
    private val usuarioListGet = mutableListOf<UsuarioGet>();

    private val usuarioApi: UsuarioInterface = RetroFitService.usuarioApi()

    // GET
    // Função para obter usuários em ordem alfabética
    fun obterUsuarioOrdemAlfabetica(token: String?) {
        val call = usuarioApi.obterUsuarioOrdemAlfabetica(token = token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<List<UsuarioGet>> {
                override fun onResponse(
                    call: Call<List<UsuarioGet>>,
                    response: Response<List<UsuarioGet>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")

                        usuarioListGet.clear()
                        usuarioListGet.addAll(response.body() ?: emptyList())

                        Log.i("api_info", "usuarios = $usuarioListGet")
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
        val call = usuarioApi.obterUsuarioPontuacaoTop3(token = token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<List<UsuarioGet>> {
                override fun onResponse(
                    call: Call<List<UsuarioGet>>,
                    response: Response<List<UsuarioGet>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")

                        usuarioListGet.clear()
                        usuarioListGet.addAll(response.body() ?: emptyList())

                        Log.i("api_info", "usuarios = $usuarioListGet")
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
        val call = usuarioApi.obterStatusUsuario(contaAtiva = contaAtiva, token = token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<List<UsuarioGet>> {
                override fun onResponse(
                    call: Call<List<UsuarioGet>>,
                    response: Response<List<UsuarioGet>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")

                        usuarioListGet.clear()
                        usuarioListGet.addAll(response.body() ?: emptyList())

                        Log.i("api_info", "usuarios = $usuarioListGet")
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

    // Função para obter um usuário específico
    fun obterUsuario(id: Int?, token: String?) {
        val call = usuarioApi.obterUsuario(id = id, token = token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<UsuarioGet> {
                override fun onResponse(
                    call: Call<UsuarioGet>,
                    response: Response<UsuarioGet>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")

                        usuarioGet = UsuarioGet();
                        usuarioGet = response.body() ?: UsuarioGet();

                        Log.i("api_info", "usuario  = $usuarioGet")
                    } else {
                        Log.e("api_error", "Erro ao realizar consulta Usuario: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<UsuarioGet>, t: Throwable) {
                    Log.e(
                        "api_error",
                        "Falha na obtenção Usuário. Código de resposta: ${t.message}"
                    )
                }
            })
        }
    }

    // POST

    // POST: Função para adicionar ranking
    fun adicionarRanking(usuario: String, token: String) {
        val call = usuarioApi.adicionarUsuarioRank(usuario, token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRank> {
                override fun onResponse(call: Call<RespostaRank>, response: Response<RespostaRank>) {
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
                override fun onResponse(call: Call<RespostaEnvioEmail>, response: Response<RespostaEnvioEmail>) {
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
    fun loginUsuario(usuario: UsuarioLogin, callback: (Boolean) -> Unit) {
        val call = usuarioApi.loginUsuario(usuario)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaLogin> {
                override fun onResponse(call: Call<RespostaLogin>, response: Response<RespostaLogin>) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Login realizado com sucesso: ${response.code()}")

                        val token = response.body()?.token
                        val idUsuario = response.body()?.userId

                        IdUserManager.saveId(idUsuario) // Armazena o id globalmente
                        TokenManager.saveToken(token) // Armazena o token globalmente

                        callback(true)
                    } else {
                        Log.e("api_error", "Login não realizado com sucesso: ${response.code()}")
                        callback(false)
                    }
                }

                override fun onFailure(call: Call<RespostaLogin>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                    callback(false)
                }
            })
        }
    }

    // POST: Função para login de usuário Google
    fun loginUsuarioGoogle(usuario: UsuarioLoginGoogle, callback: (Boolean) -> Unit) {
        val call = usuarioApi.loginUsuarioGoogle(usuario)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaLogin> {
                override fun onResponse(call: Call<RespostaLogin>, response: Response<RespostaLogin>) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Login realizado com sucesso: ${response.code()}")

                        val token = response.body()?.token
                        val idUsuario = response.body()?.userId

                        IdUserManager.saveId(idUsuario)
                        TokenManager.saveToken(token) // Armazena o token globalmente

                        Log.i("api_info", "Token armazenado: ${TokenManager.token}")
                        callback(true)
                    } else {
                        Log.e("api_error", "Login não realizado com sucesso: ${response.code()}")
                        callback(false)
                    }
                }

                override fun onFailure(call: Call<RespostaLogin>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                    callback(false)
                }
            })
        }
    }

    // POST: Função para cadastrar usuário
    fun cadastrarUsuario(usuario: Usuario) {
        val call = usuarioApi.cadastrarUsuario(usuario)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaCadastro> {
                override fun onResponse(call: Call<RespostaCadastro>, response: Response<RespostaCadastro>) {
                    if (response.isSuccessful) {
                        if (response.code() == 201) {
                            Log.i("api_info", "Cadastro realizado com sucesso: ${response.code()}")
                        } else {
                            Log.e("api_error", "Cadastro não realizado com sucesso: ${response.code()}")
                        }
                    } else {
                        Log.e("api_error", "Falha na requisição. Código de resposta: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<RespostaCadastro>, t: Throwable) {
                    Log.e("api_error", "Erro na comunicação: ${t.message}")
                }
            })
        }
    }

    // PATCH
    // PATCH: Função para trocar a foto do usuário
    fun atualizarImagem(id: Int?, token: String?, imagem: String) {
        val call = usuarioApi.atualizarImagem(id = id, token = token, imagem = imagem)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(call: Call<RespostaRequisicao>, response: Response<RespostaRequisicao>) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Imagem atualizada com sucesso: ${response.body()}")
                    } else {
                        Log.e("api_error", "Erro ao atualizar imagem. Código: ${response.code()} | Corpo: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                }
            })
        }
    }

    // PUT
    // PUT: Função para atualizar pontuação do usuário
    fun atualizarUsuarioPontuacao(id: Int?, token: String?) {
        val call = usuarioApi.atualizarUsuarioPontuacao(id = id, token = token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(call: Call<RespostaRequisicao>, response: Response<RespostaRequisicao>) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Pontuação do usuário atualizada com sucesso: ${response.code()}")
                    } else {
                        Log.e("api_error", "Erro ao atualizar pontuação do usuário. Código: ${response.code()} | Corpo: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição ao atualizar pontuação. Erro: ${t.message}")
                }
            })
        }
    }

    // PUT: Função para atualizar usuário
    fun atualizarUsuario(id: Int?, token: String?, usuario: AtualizarUsuario) {
        val call = usuarioApi.atualizarUsuario(id = id, token = token, usuario = usuario)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(call: Call<RespostaRequisicao>, response: Response<RespostaRequisicao>) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Usuário atualizado com sucesso: ${response.body()}")
                    } else {
                        Log.e("api_error", "Erro ao atualizar usuário. Código: ${response.code()} | Corpo: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição ao atualizar usuário. Erro: ${t.message}")
                }
            })
        }
    }

    // PUT: Função para atualizar perfil de usuário
    fun atualizarUsuarioPerfil(id: Int?, token: String?, usuarioAtualizarPerfil: AtualizarUsuarioPerfil) {
        val call = usuarioApi.atualizarUsuarioPerfil(id = id, token = token, usuario = usuarioAtualizarPerfil)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(call: Call<RespostaRequisicao>, response: Response<RespostaRequisicao>) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Perfil do usuário atualizado com sucesso")
                    } else {
                        Log.e("api_error", "Erro ao atualizar perfil. Código: ${response.code()} | Corpo: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição ao atualizar perfil. Erro: ${t.message}")
                }
            })
        }
    }


    // PATCH: Função para ativar usuário
    fun ativarUsuario(id: Int?, usuario: String, token: String?) {
        val call = usuarioApi.ativarUsuario(id, usuario, token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(call: Call<RespostaRequisicao>, response: Response<RespostaRequisicao>) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "Usuário ativado com sucesso: ${response.body()}")
                    } else {
                        Log.e("api_error", "Erro ao ativar usuário. Código: ${response.code()} | Corpo: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição. Erro: ${t.message}")
                }
            })
        }
    }

    // DELETE
    fun inativarUsuario(id: Int?, token: String?) {
        if (id == null || token.isNullOrEmpty()) {
            Log.e("api_error", "ID ou Token inválidos.")
            return
        }

        val call = usuarioApi.inativarUsuario(id = id, token = token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(
                    call: Call<RespostaRequisicao>,
                    response: Response<RespostaRequisicao>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")
                    } else {
                        val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
                        Log.e("api_error", "Erro ao inativar usuário: $errorBody")
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição: ${t.message}")
                }
            })
        }
    }

    fun excluirUsuario(id: Int?, token: String?) {
        if (id == null || token.isNullOrEmpty()) {
            Log.e("api_error", "ID ou Token inválidos.")
            return
        }

        val call = usuarioApi.excluirUsuario(id = id, token = token)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<RespostaRequisicao> {
                override fun onResponse(
                    call: Call<RespostaRequisicao>,
                    response: Response<RespostaRequisicao>
                ) {
                    if (response.isSuccessful) {
                        Log.i("api_info", "status : ${response.code()}")

                    } else {
                        val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
                        Log.e("api_error", "Erro ao inativar usuário: $errorBody")
                    }
                }

                override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
                    Log.e("api_error", "Falha na requisição: ${t.message}")
                    return
                }
            })
        }
    }
}