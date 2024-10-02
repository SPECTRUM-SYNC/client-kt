package spectrum.fittech.backend.dtos

import com.google.gson.Gson

data class ErroResponse(
    val status: Int,
    val error: String,
    val message: String,
    val path: String
)

fun parseErrorMessage(errorResponse: String?): String {
    return try {
        val gson = Gson()
        val erroResponse = gson.fromJson(errorResponse, ErroResponse::class.java)
        erroResponse.message // Retorna apenas a mensagem de erro
    } catch (e: Exception) {
        "Erro desconhecido"
    }
}