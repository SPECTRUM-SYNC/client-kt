package spectrum.fittech.backend.interfaces

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import spectrum.fittech.backend.dtos.AtualizaPesoDto
import spectrum.fittech.backend.dtos.HistoricoPeso
import spectrum.fittech.backend.dtos.RespostaRequisicao

interface HistoricoPesoInterface {
    @POST("pesos/cadastar-peso")
    fun cadastrarPeso(
        @Header("Authorization") token: String,
        @Body peso: HistoricoPeso,
        @Body idUsuario: Int
    ): Call<RespostaRequisicao>

    @GET("pesos/")
    fun procurarPorId(
        @Header("Authorization") token: String,
        @Body idUsuario: Int
    ): Call<List<HistoricoPeso>>

    @GET("pesos/historico-grafico/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    suspend fun historicoGrafico(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?
    ): Response<List<HistoricoPeso>>

    @POST("pesos/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    suspend fun atualizarPeso(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?,
        @Body peso: AtualizaPesoDto
    ): Response<Void>
}