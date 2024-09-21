package spectrum.fittech.backend.service.Usuario

import spectrum.fittech.backend.log.client;
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spectrum.fittech.backend.builder.apiInterface
import spectrum.fittech.backend.dtos.*
import spectrum.fittech.backend.dtos.Usuario
import spectrum.fittech.backend.interfaces.ApiInterface

import retrofit2.Callback
import retrofit2.Response
import spectrum.fittech.backend.Object.TokenManager
import java.time.LocalDateTime

val usuario = Usuario(
    nome = "Rafael Reis",
    email = "Ricardo.vicesssnsste.asssasd@sptech.school",
    senha = "Madalena13#",
    img = "string"
)
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

val objetivo = Objetivo(
    objetivo = "Ganho de Massa"
)
val userTopRank =  AdicionarTopRank(
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
)


fun main() {
    //cadastrarUsuario(usuario);
    loginUsuarioGoogle(usuarioLoginGoogle)
    //loginUsuarioGoogle(usuarioLoginGoogle);
    //envioEmail(usuarioEnvioEmail);
   // reajustes adicionarRanking(usuario = userTopRank, token = TokenManager.token.toString())
}


fun adicionarRanking(usuario:AdicionarTopRank, token:String) {
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


fun loginUsuario(usuario:UsuarioLogin){
    val interfaceUsuario = gerarAmbiente();

    val call = interfaceUsuario.loginUsuario(usuario)

    call.enqueue(object : Callback<RespostaLogin>{
        override fun onResponse(call: Call<RespostaLogin>, response: Response<RespostaLogin>) {
            if(response.isSuccessful) {
                println("Login realizado com sucesso : ${response.code()}")
                val token = response.body()?.token
                TokenManager.saveToken(token) // Armazena o token globalmente
                println("Token armazenado: ${TokenManager.token}")
                return ;
            } else {
                println("Login não realizado com sucesso : ${response.code()}")
                return
            }
        }

        override fun onFailure(call: Call<RespostaLogin>, t: Throwable) {
            println("Falha na requisição. Código de resposta: ${t.message}")
            return
        }

    })

}

fun loginUsuarioGoogle(usuario: UsuarioLoginGoogle) {
    val interfaceUsuario = gerarAmbiente()
    val call = interfaceUsuario.loginUsuarioGoogle(usuario)

    call.enqueue(object : Callback<RespostaLogin> {
        override fun onResponse(call: Call<RespostaLogin>, response: Response<RespostaLogin>) {
            if (response.isSuccessful) {
                println("Login realizado com sucesso : ${response.code()}")
                val token = response.body()?.token
                TokenManager.saveToken(token) // Armazena o token globalmente
                println("Token armazenado: ${TokenManager.token}")
            } else {
                println("Login não realizado com sucesso : ${response.code()}")
            }
        }

        override fun onFailure(call: Call<RespostaLogin>, t: Throwable) {
            println("Falha na requisição. Erro: ${t.message}")
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
        //.client(client) // log
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiInterface::class.java)

    return apiService;
}