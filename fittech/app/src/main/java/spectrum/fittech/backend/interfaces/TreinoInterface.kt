package spectrum.fittech.backend.interfaces

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import spectrum.fittech.backend.dtos.RespostaRequisicao
import spectrum.fittech.backend.dtos.TreinoCountDto
import spectrum.fittech.backend.dtos.TreinoCreateDto
import spectrum.fittech.backend.dtos.TreinoResponseDto

interface TreinoInterface {
    @POST("treinos")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun cadastrarTreino(
        @Header("Authorization") token: String?,
        @Body treinoCreateDTO: TreinoCreateDto?
    ): Call<TreinoResponseDto>


    @GET("treinos/validar/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    suspend fun validarTreino(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?
    ): Response<TreinoResponseDto>

    @GET("treinos/usuario/dia-atual/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    suspend fun listarTodosTreinosDoDia(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?
    ): Response<MutableList<TreinoResponseDto>>


    @GET("treinos/por-dia-da-semana/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    suspend fun listarTreinoPorDiaDaSemana(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?
    ): Response<MutableList<TreinoCountDto>>


    @GET("treinos/usuario/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    suspend fun listagemTreinos(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?
    ): Response<List<TreinoResponseDto>>


    @GET("treinos/verificar/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    suspend fun verificarTreino(
        @Header("Authorization") token: String?,
        @Path("id") id: Int?
    ): Response<Boolean>


    @PUT("treinos/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun atualizarStatusTreino(
        @Path("id") id: Int?,
        @Header("Authorization") token: String?
    ): Call<TreinoResponseDto>

    @PUT("usuarios/pontuacao/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun atualizarUsuarioPontuacao(
        @Path("id") id: Int?,
        @Header("Authorization") token: String?
    ): Call<RespostaRequisicao>
}

