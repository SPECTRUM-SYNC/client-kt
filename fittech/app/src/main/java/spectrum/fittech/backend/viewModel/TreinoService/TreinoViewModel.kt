package spectrum.fittech.backend.viewModel.TreinoService

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import spectrum.fittech.backend.dtos.NovoUsuario
import spectrum.fittech.backend.dtos.RespostaCadastro
import spectrum.fittech.backend.dtos.RespostaRequisicao
import spectrum.fittech.backend.dtos.TreinoCountDto
import spectrum.fittech.backend.dtos.TreinoCreateDto
import spectrum.fittech.backend.dtos.TreinoResponseDto
import spectrum.fittech.backend.dtos.parseErrorMessage
import spectrum.fittech.backend.interfaces.TreinoInterface
import spectrum.fittech.backend.interfaces.UsuarioInterface
import spectrum.fittech.retroFit.RetroFitService
import spectrum.fittech.utils.treinos.Treino
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

class TreinoViewModel : ViewModel() {

    private val treinoController: TreinoInterface = RetroFitService.treinoApi()

    suspend fun verificarTreino(token: String?, id: Int?): Boolean? {

        return withContext(Dispatchers.IO) {
            try {
                val response = treinoController.verificarTreino(token = "Bearer $token", id)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("Erro", "Falha na requisição: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("Erro", "Falha na requisição: ${e.message}")
                null
            }
        }
    }

    suspend fun listarTodosTreinosDoDia(token: String?, id: Int?): MutableList<TreinoResponseDto>? {

        return withContext(Dispatchers.IO) {
            try {
                val response = treinoController.listarTodosTreinosDoDia(token = "Bearer $token", id)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("Erro", "Falha na requisição: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("Erro", "Falha na requisição: ${e.message}")
                null
            }
        }
    }

    suspend fun listarTreino(token: String?, id: Int?): List<TreinoResponseDto>? {

        return withContext(Dispatchers.IO) {
            try {
                val response = treinoController.listagemTreinos(token = "Bearer $token", id)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("Erro", "Falha na requisição: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("Erro", "Falha na requisição: ${e.message}")
                null
            }
        }
    }

    suspend fun listarTreinoPorDiaDaSemana(token: String?, id: Int?): MutableList<TreinoCountDto>? {

        return withContext(Dispatchers.IO) {
            try {
                val response = treinoController.listarTreinoPorDiaDaSemana(token = "Bearer $token", id)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("Erro", "Falha na requisição: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("Erro", "Falha na requisição: ${e.message}")
                null
            }
        }
    }

    suspend fun validarTreino(token: String?, id: Int?): TreinoResponseDto? {

        return withContext(Dispatchers.IO) {
            try {
                val response = treinoController.validarTreino(token = "Bearer $token", id)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("Erro", "Falha na requisição: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("Erro", "Falha na requisição: ${e.message}")
                null
            }
        }
    }


    // POST: Função para cadastrar usuário
    fun cadastrarTreino(
        token: String?,
        dias: List<Boolean>,
        meta: String,
        usuarioId: Int?,
        callback: (Boolean, String) -> Unit
    ) {
        val diasDaSemana =
            listOf("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado")
        val selectedDays = diasDaSemana.filterIndexed { index, _ -> dias[index] }

        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0 until 7) {
                val nextDay = LocalDate.now().plusDays(i.toLong())
                val diaSemanaAbreviado = diasDaSemana[nextDay.dayOfWeek.value % 7]
                val status = if (selectedDays.contains(diaSemanaAbreviado)) "Descanso" else "Treino"
                val tipoTreino = if (i % 2 == 0) "${meta}_1" else "${meta}_2"

                val treinoCreateDto = TreinoCreateDto(
                    descricao = "Diario",
                    dataTreino = nextDay.toString(),
                    status = status,
                    tipoTreino = tipoTreino,
                    usuarioId = usuarioId
                )

                val response = try {
                    treinoController.cadastrarTreino(token = "Bearer $token", treinoCreateDto)
                        .awaitResponse()
                } catch (e: Exception) {
                    Log.e("api_error", "Erro na comunicação: ${e.message}")
                    callback(false, "Erro na requisição: ${e.message}")
                    return@launch
                }

                // Processar a resposta
                if (response.isSuccessful) {
                    callback(true, "Treino gerado com sucesso!")
                } else {
                    val errorResponse = response.errorBody()?.string()
                    val errorMessage = parseErrorMessage(errorResponse)
                    Log.e("api_error", "Erro na requisição: $errorMessage")
                    callback(false, errorMessage)
                }
            }
        }
    }


    fun atualizarStatusTreino(
        id: Int?,
        token: String?,
        listaTreino: List<Treino>? = null
    ) {
        if (id == null || token.isNullOrBlank()) {
            Log.e("param_error", "ID ou token inválido.")
            return
        }

        val treinoDto = listaTreino?.getOrNull(0)?.let { treino ->
            if (treino.tituloTreino != "Diario") {
                TreinoCreateDto(
                    descricao = treino.tituloTreino,
                    dataTreino = LocalDate.now().toString(),
                    status = "Feito",
                    tipoTreino = treino.tituloTreino,
                    usuarioId = id
                )
            } else null
        }

        CoroutineScope(Dispatchers.IO).launch {
            // Atualizar pontuação do usuário
            val callUser = treinoController.atualizarUsuarioPontuacao(id = id, token = "Bearer $token")
            try {
                val responsePontuacao = withContext(Dispatchers.IO) { callUser.execute() }
                if (!responsePontuacao.isSuccessful) {
                    Log.e(
                        "api_error",
                        "Falha ao atualizar pontuação. Código: ${responsePontuacao.code()}"
                    )
                }
            } catch (t: Throwable) {
                Log.e("api_error", "Erro na atualização de pontuação: ${t.message}")
            }

            // Atualizar status do treino ou cadastrar novo treino
            val call = if (treinoDto == null) {
                treinoController.atualizarStatusTreino(id, "Bearer $token")
            } else {
                treinoController.cadastrarTreino("Bearer $token", treinoDto)
            }

            try {
                val response = withContext(Dispatchers.IO) { call.execute() }
                if (!response.isSuccessful) {
                    Log.e(
                        "api_error",
                        "Falha na resposta ao atualizar treino. Código: ${response.code()}"
                    )
                }
            } catch (t: Throwable) {
                Log.e("api_error", "Falha na requisição ao atualizar treino. Erro: ${t.message}")
            }
        }
    }



}