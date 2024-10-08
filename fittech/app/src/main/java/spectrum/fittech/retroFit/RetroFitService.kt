package spectrum.fittech.retroFit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spectrum.fittech.backend.interfaces.UsuarioInterface
import spectrum.fittech.backend.log.client

object RetroFitService {

    private const val BASE_URL = "https://fittech.azurewebsites.net/api/"

    fun usuarioApi() : UsuarioInterface {
        val usuario = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioInterface::class.java)

        return usuario;
    }
}