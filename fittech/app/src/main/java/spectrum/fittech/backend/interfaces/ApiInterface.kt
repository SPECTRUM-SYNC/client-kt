package spectrum.fittech.backend.interfaces

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import spectrum.fittech.backend.dtos.RespostaCadastro
import spectrum.fittech.backend.dtos.Usuario

interface ApiInterface {
    @POST("cadastro") // substitua pela rota de cadastro correta
    fun cadastrarUsuario(@Body usuario: Usuario): Call<RespostaCadastro>
}

