package spectrum.fittech.backend.viewModel.HistoricoPesoService

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import spectrum.fittech.backend.dtos.HistoricoPeso
import spectrum.fittech.backend.interfaces.HistoricoPesoInterface
import spectrum.fittech.retroFit.RetroFitService

class HistoricoPesoViewModel : ViewModel() {

    private val historicoPeso: HistoricoPesoInterface = RetroFitService.historicoPesoApi()

    suspend fun historicoGrafico(token: String?, id: Int?):List<HistoricoPeso>? {

        return withContext(Dispatchers.IO) {
            try {
                val response = historicoPeso.historicoGrafico(token = "Bearer $token", id)
                if (response.isSuccessful) {
                    Log.e("Sucesso", "Requisição bem-sucedida" + response.body())
                    response.body()
                } else {
                    Log.e("Erro", "Falha na requisição: ${response.code()}")
                    null
                }
            }catch (e: Exception){
                Log.e("Erro", "Falha na requisição: ${e.message}")
                null
            }
        }
    }

}