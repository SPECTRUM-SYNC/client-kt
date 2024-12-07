package spectrum.fittech.retroFit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spectrum.fittech.backend.interfaces.HistoricoPesoInterface
import spectrum.fittech.backend.interfaces.ReceitaInterface
import spectrum.fittech.backend.interfaces.TreinoInterface
import spectrum.fittech.backend.interfaces.UsuarioInterface
import spectrum.fittech.backend.log.client
import java.util.concurrent.TimeUnit

object RetroFitService {

    private const val BASE_URL = "https://fittech.azurewebsites.net/api/"

    fun usuarioApi(): UsuarioInterface {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // Tempo de conexão
            .readTimeout(30, TimeUnit.SECONDS)    // Tempo de leitura
            .writeTimeout(30, TimeUnit.SECONDS)   // Tempo de escrita
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioInterface::class.java)
    }


    fun treinoApi() : TreinoInterface {
        val treino = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TreinoInterface::class.java)

        return treino;
    }

    fun historicoPesoApi() : HistoricoPesoInterface {
        val historicoPeso = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return historicoPeso.create(HistoricoPesoInterface::class.java)
    }

    fun receitaApi(): ReceitaInterface {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReceitaInterface::class.java)
    }

}