package spectrum.fittech.backend.interfaces

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import spectrum.fittech.backend.dtos.RespostaCadastro
import spectrum.fittech.backend.dtos.Usuario

interface ApiInterface {
    @POST("usuarios") // substitua pela rota de cadastro correta
    @Headers(
        "Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.42.0",
        "Connection: keep-alive",
        "Cache-Control: no-cache"
    )    fun cadastrarUsuario(@Body usuario: Usuario): Call<RespostaCadastro>
}

