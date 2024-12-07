import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import spectrum.fittech.backend.dtos.Receita
import spectrum.fittech.backend.interfaces.ReceitaInterface
import spectrum.fittech.retroFit.RetroFitService

class ReceitaViewModel : ViewModel() {

    private val _receitas = MutableLiveData<List<Receita>>()
    private val openAiController: ReceitaInterface = RetroFitService.receitaApi()
    val receitas: LiveData<List<Receita>> = _receitas

    fun fetchReceitas(idUsuario: Long, objetivo: String, qtdSelecionada: Int, token: String) {
        viewModelScope.launch {
            try {
                val response = openAiController.gerarReceitas(
                    token = "Bearer $token",
                    id = idUsuario,
                    objetivo = objetivo,
                    qtdSelecionada = qtdSelecionada
                )

                if (response.isSuccessful) {
                    _receitas.postValue(response.body())
                } else {
                    Log.e("Erro", "Falha na requisição de Receita: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Erro", "Falha na requisição de Receita: ${e.message}")
            }
        }
    }
}