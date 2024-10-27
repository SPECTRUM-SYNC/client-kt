package spectrum.fittech.backend.interfaces

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import spectrum.fittech.backend.dtos.AtualizarUsuario
import spectrum.fittech.backend.dtos.AtualizarUsuarioPerfil
import spectrum.fittech.backend.dtos.EnvioEmailUsuario
import spectrum.fittech.backend.dtos.NovoUsuario
import spectrum.fittech.backend.dtos.RespostaCadastro
import spectrum.fittech.backend.dtos.RespostaEnvioEmail
import spectrum.fittech.backend.dtos.RespostaLogin
import spectrum.fittech.backend.dtos.RespostaRank
import spectrum.fittech.backend.dtos.RespostaRequisicao
import spectrum.fittech.backend.dtos.Usuario
import spectrum.fittech.backend.dtos.UsuarioGet
import spectrum.fittech.backend.dtos.UsuarioLogin
import spectrum.fittech.backend.dtos.UsuarioLoginGoogle

interface UsuarioInterface {
    @POST("usuarios")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )    fun cadastrarUsuario(@Body usuario: Usuario?): Call<UsuarioGet>

    @POST("usuarios/login")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )    fun loginUsuario(@Body usuario: UsuarioLogin): Call<RespostaLogin>

    @POST("usuarios/login/google")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )    fun loginUsuarioGoogle(@Body usuario: UsuarioLoginGoogle): Call<RespostaLogin>

    @POST("usuarios/enviar-email")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )    fun envioEmailRedefinicao(@Body usuario: EnvioEmailUsuario): Call<RespostaEnvioEmail>


    @POST("usuarios/adicionar-top-ranking")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun adicionarUsuarioRank(
        @Body usuario: String,
        @Header("Authorization") token: String
    ): Call<RespostaRank>

    @PATCH("usuarios/{id}/ativar")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun ativarUsuario(
        @Path("id") id: Int?,
        @Body usuario: String?,
        @Header("Authorization") token: String?
    ): Call<RespostaRequisicao>


    @PATCH("usuarios/imagem/{id}")
    @Multipart
    fun atualizarImagem(
        @Path("id") id: Int?,
        @Header("Authorization") token: String?,
        @Part imagem: MultipartBody.Part
    ): Call<Void>


    @PUT("usuarios/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun atualizarUsuario(
        @Path("id") id: Int?,
        @Header("Authorization") token: String?,
        @Body usuario : AtualizarUsuario
    ): Call<RespostaRequisicao>

    @PUT("usuarios/perfil/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun atualizarUsuarioPerfil(
        @Path("id") id: Int?,
        @Header("Authorization") token: String?,
        @Body usuario : AtualizarUsuarioPerfil
    ): Call<RespostaRequisicao>


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

    @GET("usuarios/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    suspend fun obterUsuario(
        @Path("id") id: Int?,
        @Header("Authorization") token: String?
    ): Response<UsuarioGet>

    @GET("usuarios/statusUsuario")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun obterStatusUsuario(
        @Query("contaAtiva") contaAtiva: Boolean?,
        @Header("Authorization") token: String?
    ): Call<List<UsuarioGet>>

    @GET("usuarios/pontuacao")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun obterUsuarioPontuacaoTop3(
        @Header("Authorization") token: String?
    ): Call<List<UsuarioGet>>

    @GET("usuarios/ordemAlfabetica")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun obterUsuarioOrdemAlfabetica(
        @Header("Authorization") token: String?
    ): Call<List<UsuarioGet>>



    @DELETE("usuarios/{id}")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun excluirUsuario(
        @Path("id") id: Int?,
        @Header("Authorization") token: String?
    ): Call<RespostaRequisicao>

    @DELETE("usuarios/{id}/inativar")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )
    fun inativarUsuario(
        @Path("id") id: Int?,
        @Header("Authorization") token: String?
    ): Call<RespostaRequisicao>


}

