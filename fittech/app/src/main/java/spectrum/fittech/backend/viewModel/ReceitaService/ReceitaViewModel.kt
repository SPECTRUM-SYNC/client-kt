import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import spectrum.fittech.backend.dtos.Receita
import spectrum.fittech.backend.interfaces.ReceitaInterface
import spectrum.fittech.retroFit.RetroFitService

class ReceitaViewModel : ViewModel() {

    private val openAiController: ReceitaInterface = RetroFitService.receitaApi()

    suspend fun fetchReceitas(
        idUsuario: Long,
        objetivo: String,
        token: String
    ): MutableList<Receita>? {
        return try {
            val response = openAiController.gerarReceitas(
                token = "Bearer $token",
                id = idUsuario,
                objetivo = objetivo,
                qtdSelecionada = 5
            )

            if (response.isSuccessful) {
                val receitas = response.body()
                if (!receitas.isNullOrEmpty()) {
                    val topReceitas = receitas.sortedByDescending { it.id }.take(5).toMutableList()
                    topReceitas.forEach { receita ->
                        receita.img = adicionarImagem(
                            receita.ingredientes[0].split(" ").first()
                        ).orEmpty()
                    }
                    return topReceitas
                }
                null
            } else {
                Log.e("Erro", "Falha na requisição de Receita: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Erro", "Falha na requisição de Receita: ${e.message}")
            null
        }
    }


    suspend fun listarReceitas(token: String?, id: Int?): MutableList<Receita>? {
        if (id == null) {
            Log.e("Erro", "ID não pode ser nulo.")
            return null
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = openAiController.listarReceitas(token = "Bearer $token", id.toLong())

                if (response.isSuccessful) {
                    val receitas = response.body()
                    if (!receitas.isNullOrEmpty()) {
                        val topReceitas =
                            receitas.sortedByDescending { it.id }.take(5 ).toMutableList()

                        topReceitas.forEach { receita ->
                            receita.img = adicionarImagem(
                                receita.ingredientes[0].split(" ").first()
                            ).orEmpty()
                        }

                        topReceitas
                    } else {
                        Log.e("Erro", "Lista de receitas está vazia ou nula.")
                        null
                    }
                } else {
                    Log.e("Erro", "Falha na requisição: ${response.code()} - ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("Erro", "Erro inesperado na requisição: ${e.localizedMessage}")
                null
            }
        }
    }


    fun adicionarImagem(detalhe: String): String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.unsplash.com/search/photos?query=${detalhe} - comida&client_id=Mv0lNDqYsQQYmW6IuooDXZoe630aGrm2xCLSwI2PQkY&per_page=1")
            .build()

        client.newCall(request).execute().use { response ->
            val json = response.body?.string()
            if (json != null) {
                val jsonObject = JSONObject(json)
                val results = jsonObject.getJSONArray("results")
                if (results.length() > 0) {
                    val firstResult = results.getJSONObject(0)
                    val urls = firstResult.getJSONObject("urls")
                    return urls.getString("raw")
                }
            }
            return "https://assets.unileversolutions.com/recipes-v2/106678.jpg"
        }
    }


}