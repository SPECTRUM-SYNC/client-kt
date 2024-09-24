package spectrum.fittech.backend.service.Usuario

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spectrum.fittech.backend.dtos.*
import spectrum.fittech.backend.builder.gson
import spectrum.fittech.backend.dtos.Usuario
import spectrum.fittech.backend.interfaces.ApiInterface
import retrofit2.Callback
import retrofit2.Response
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.Object.TokenManager
import spectrum.fittech.backend.log.client
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date


val usuario = Usuario(
    nome = "Rafael Reis",
    email = "Ricardo.vicesssnsste.asssasd@sptech.school",
    senha = "Madalena13#",
    img = "string"
)

val formatoData = SimpleDateFormat("yyyy-MM-dd")
val dataNascimento = formatoData.format(Date(90, 5, 15))
// Criando uma instância da classe AtualizarUsuario com valores fictícios
val usuarioAtualizar = AtualizarUsuario(
    nome = "João Silva",
    senha = "9242#Rick",
    img = "https://exemplo.com/imagem.png",
    genero = "Masculino",
    peso = 95.5,
    altura = 175,
    dataNascimento = "2001-01-10",
    meta = "Perder peso",
    nivelCondicao = "Intermediário"
)

val objetivo = Objetivo(
    objetivo = "Ganho de Massa"
)

val usuarioAtivar =  AtivarUsuario(
    nome = "Lula Molusco",
    email = "FazOL@gmail.com",
    img = "SiriSacudo",
    meta = "Ganho de Massa",
    dataNascimento = LocalDateTime.now(),
    genero = "GAY",
    peso = 20.0,
    altura =  20.0,
    nivelCondicao = "GORDOLASCADOMAIORQUESUAMAE",
    contaAtiva = true,
    pontuacao = 200000.0,
    objetivo = objetivo
)

val atualizarImagemUsuario =  gson.toJson(AtualizarImagem(
    imageFile = "profile.png"
))

val usuarioLogin = UsuarioLogin(
    email = "winycios@gmail.com",
    senha = "Madalena13#"
)
val usuarioLoginGoogle = UsuarioLoginGoogle(
    email = "winycios@gmail.com",
    nome = "Madalena13#"
)

val usuarioEnvioEmail = EnvioEmailUsuario(
    para = "Ricardo.vicesssnsste.asssasd@sptech.school",
    nome = "Vinicius13"
)


val userTopRank =  gson.toJson(AdicionarTopRank(
    nome = "Lula Molusco",
    email = "FazOL@gmail.com",
    senha = "boqueteParafuso",
    img = "SiriSacudo",
    meta = "Ganho de Massa",
    dataNascimento = LocalDateTime.now(),
    genero = "GAY",
    peso = 20.0,
    altura =  20.0,
    nivelCondicao = "GORDOLASCADOMAIORQUESUAMAE",
    contaAtiva = true,
    pontuacao = 200000.0,
    horaSenhaAtualizacao = LocalDateTime.now(),
    objetivo = objetivo
))


fun main() {
    //cadastrarUsuario(usuario);
    //loginUsuarioGoogle(usuarioLoginGoogle);
    //envioEmail(usuarioEnvioEmail);
    loginUsuario(usuarioLogin) { success ->
        if (success) {
            println("TOKEN :  ${TokenManager.token.toString()}")
            println("Id User : ${IdUserManager.userId}")

            //adicionarRanking(usuario = userTopRank, token = "Bearer ${TokenManager.token.toString()}")
            //ativarUsuario(id = IdUserManager.userId, usuario = usuarioAtivar, token = "Bearer ${TokenManager.token.toString()}" )
            //atualizarImagem(id = IdUserManager.userId, token = "Bearer ${TokenManager.token.toString()}", atualizarImagemUsuario)
            atualizarUsuario(id = IdUserManager.userId, token = "Bearer ${TokenManager.token.toString()}", usuarioAtualizar)
        } else {
            println("Login falhou")
        }
    }
}

fun atualizarUsuario(id: Int?, token: String?, usuario: AtualizarUsuario) {
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.atualizarUsuario(id =  id, token = token, usuario = usuario)

    call.enqueue(object : Callback<RespostaRequisicao> {
        override fun onResponse(
            call: Call<RespostaRequisicao>,
            response: Response<RespostaRequisicao>
        ) {
            if(response.isSuccessful){
                println("Corpo : ${response.body()}")
            } else {
                println("Erro ao relaizar atualização : ${response.body()}")
            }
        }

        override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
            println("Falha na requisição. Código de resposta: ${t.message}")
            return
        }

    })

}


fun atualizarUsuarioPerfil(id: Int?, token: String?, usuario: AtualizarUsuario) {
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.atualizarUsuarioPerfil(id =  id, token = token, usuario = usuario)

    call.enqueue(object : Callback<RespostaRequisicao> {
        override fun onResponse(
            call: Call<RespostaRequisicao>,
            response: Response<RespostaRequisicao>
        ) {
            if(response.isSuccessful){
                println("Corpo : ${response.body()}")
            } else {
                println("Erro ao relaizar atualização : ${response.body()}")
            }
        }

        override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
            println("Falha na requisição. Código de resposta: ${t.message}")
            return
        }

    })

}



// PATCHS
fun atualizarImagem(id: Int?, token: String?, imagem : String) {
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.atualizarImagem(id =  id, token = token, imagem = imagem)

    call.enqueue(object : Callback<RespostaRequisicao> {
        override fun onResponse(
            call: Call<RespostaRequisicao>,
            response: Response<RespostaRequisicao>
        ) {
            if(response.isSuccessful) {
                println("Corpo : ${response.body()}")
            } else {
                println("Erro ao atualizar Imagem : ${response.body()}")
            }
        }

        override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
            println("Falha na requisição. Código de resposta: ${t.message}")
            return
        }
    })
}

fun ativarUsuario(id: Int?, usuario: String, token: String?) {
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.ativarUsuario(id, usuario, token)

    call.enqueue(object : Callback<RespostaRequisicao> {
        override fun onResponse(
            call: Call<RespostaRequisicao>,
            response: Response<RespostaRequisicao>
        ) {
            if(response.isSuccessful){
                println("Resposta : ${response.body()}" )
            } else {
                println("Erro ao ativar usuario, corpo : ${response.body()}")
            }
        }

        override fun onFailure(call: Call<RespostaRequisicao>, t: Throwable) {
            println("Falha na requisição. Código de resposta: ${t.message}")
            return
        }
    })

}


// POSTS
fun adicionarRanking(usuario: String, token:String) {
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.adicionarUsuarioRank(usuario, token)

    call.enqueue(object : Callback<RespostaRank>  {
        override fun onResponse(call: Call<RespostaRank>, response: Response<RespostaRank>){
            if(response.isSuccessful) {
                println("Resposta : ${response.body()}")
                return
            } else {
                println("Ocorreu um erro ao cadastrar no rank : ${response.code()}")
                return
            }
        }
        override fun onFailure(call: Call<RespostaRank>, t: Throwable) {
            println("Falha na requisição. Código de resposta: ${t.message}")
            return
        }
    })

}

fun envioEmail(usuario:EnvioEmailUsuario) {
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.envioEmailRedefinicao(usuario)
    
    call.enqueue(object : Callback<RespostaEnvioEmail>  {
        override fun onResponse(call: Call<RespostaEnvioEmail>, response: Response<RespostaEnvioEmail>){
            if(response.isSuccessful) {
                println("${response.body()}")
                return
            } else {
                println("Ocorreu um erro ao enviar email : ${response.code()}")
                return
            }
        }
        override fun onFailure(call: Call<RespostaEnvioEmail>, t: Throwable) {
            println("Falha na requisição. Código de resposta: ${t.message}")
            return
        }
    })
}

fun loginUsuario(usuario:UsuarioLogin, callback: (Boolean) -> Unit){
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.loginUsuario(usuario)

    call.enqueue(object : Callback<RespostaLogin>{
        override fun onResponse(call: Call<RespostaLogin>, response: Response<RespostaLogin>) {
            if(response.isSuccessful) {
                println("Login realizado com sucesso : ${response.code()}")

                val token = response.body()?.token
                val idUsuario = response.body()?.userId;

                IdUserManager.saveId(idUsuario) // Armazena o id globalmente
                TokenManager.saveToken(token) // Armazena o token globalmente

                println("Token armazenado: ${TokenManager.token}")
                callback(true)
            } else {
                println("Login não realizado com sucesso : ${response.code()}")
                callback(false)
            }
        }

        override fun onFailure(call: Call<RespostaLogin>, t: Throwable) {
            println("Falha na requisição. Código de resposta: ${t.message}")
            return
        }
    })
}

fun loginUsuarioGoogle(usuario: UsuarioLoginGoogle, callback: (Boolean) -> Unit) {
    val interfaceUsuario = gerarAmbiente()
    val call = interfaceUsuario.loginUsuarioGoogle(usuario)

    call.enqueue(object : Callback<RespostaLogin> {
        override fun onResponse(call: Call<RespostaLogin>, response: Response<RespostaLogin>) {
            if (response.isSuccessful) {
                println("Login realizado com sucesso : ${response.code()}")
                val token = response.body()?.token
                val idUsuario = response.body()?.userId;

                IdUserManager.saveId(idUsuario)
                TokenManager.saveToken(token) // Armazena o token globalmente

                println("Token armazenado: ${TokenManager.token}")
                callback(true)
            } else {
                println("Login não realizado com sucesso : ${response.code()}")
                callback(false)
            }
        }

        override fun onFailure(call: Call<RespostaLogin>, t: Throwable) {
            println("Falha na requisição. Erro: ${t.message}")
            callback(false)
        }
    })
}

fun cadastrarUsuario(usuario:Usuario) {
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.cadastrarUsuario(usuario)

    call.enqueue(object : Callback<RespostaCadastro> {
        override fun onResponse(call: Call<RespostaCadastro>, response: Response<RespostaCadastro>) {
            if (response.isSuccessful) {
                if (response.code() == 201) {
                    println("Cadastro realizado com sucesso: ${response.code()}")
                    return;
                } else {
                    println("Cadastro não foi realizado com sucesso. Código de resposta: ${response.code()}")
                    return;
                }
            } else {
                println("Falha na requisição. Código de resposta: ${response.code()}")
                return;
            }
        }


        override fun onFailure(call: Call<RespostaCadastro>, t: Throwable) {
            println("Erro na comunicação: ${t.message}")
        }
    })
}





/*
    - Metódo responsável para gerar o ambiente para criação dos
    endpoint.

    - Temos o '.client(client)' que é usado para saber
    o log daquela requisição, não é necessário usar todas vezes.
*/
private fun gerarAmbiente() : ApiInterface {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080/")
        .client(client) // log
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiInterface::class.java)

    return apiService;
}