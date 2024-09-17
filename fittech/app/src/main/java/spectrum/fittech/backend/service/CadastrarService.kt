package spectrum.fittech.backend.service

import retrofit2.Call
import spectrum.fittech.backend.builder.apiInterface
import spectrum.fittech.backend.dtos.RespostaCadastro
import spectrum.fittech.backend.dtos.Usuario

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