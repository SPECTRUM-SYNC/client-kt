package spectrum.fittech.backend.interfaces

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import spectrum.fittech.backend.dtos.*

interface ApiInterface {
    @POST("usuarios")
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )    fun cadastrarUsuario(@Body usuario: Usuario): Call<RespostaCadastro>

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

}

