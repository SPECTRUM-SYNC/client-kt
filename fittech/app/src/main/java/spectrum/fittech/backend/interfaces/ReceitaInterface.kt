package spectrum.fittech.backend.interfaces

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import spectrum.fittech.backend.dtos.Receita

interface ReceitaInterface {


    @GET("openai/gpt3/{id}")
    suspend fun gerarReceitas(
        @Header("Authorization") token: String,
        @Path("id") id: Long,
        @Query("objetivo") objetivo: String,
        @Query("qtdSelecionada") qtdSelecionada: Int
    ): Response<MutableList<Receita>>


    @GET("openai/{id}")
    suspend fun listarReceitas(
        @Header("Authorization") token: String,
        @Path("id") id: Long,
    ): Response<MutableList<Receita>>
}