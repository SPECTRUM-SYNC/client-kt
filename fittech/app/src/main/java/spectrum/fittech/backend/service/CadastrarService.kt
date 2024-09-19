package spectrum.fittech.backend.service

import spectrum.fittech.backend.log.logging;
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spectrum.fittech.backend.builder.apiInterface
import spectrum.fittech.backend.dtos.RespostaCadastro
import spectrum.fittech.backend.dtos.Usuario
import spectrum.fittech.backend.interfaces.ApiInterface

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Response


fun realizarCadastro(usuario: Usuario, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
    apiInterface.cadastrarUsuario(usuario).enqueue(object : retrofit2.Callback<RespostaCadastro> {
        override fun onResponse(call: Call<RespostaCadastro>, response: retrofit2.Response<RespostaCadastro>) {
            if (response.isSuccessful) {
                val resposta = response.body()
                if (resposta?.sucesso == true) {
                    onSuccess(resposta.mensagem)
                } else {
                    onFailure(resposta?.mensagem ?: "Erro desconhecido")
                }
            } else {
                onFailure("Erro na requisição: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<RespostaCadastro>, t: Throwable) {
            onFailure("Falha na comunicação: ${t.message}")
        }
    })
}

fun main() {
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiInterface::class.java)

    val usuario = Usuario(
        nome = "Rafael Reis",
        email = "Ricardo.vicensste.asssasd@sptech.school",
        senha = "Madalena13#",
        img = "string"
    )

    val call = apiService.cadastrarUsuario(usuario)

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