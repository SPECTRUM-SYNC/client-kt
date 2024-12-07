package spectrum.fittech.backend.dtos

data class Receita(
    val nome: String,
    val ingredientes: List<String>,
    val modoDePreparo: String,
    val calorias: Int,
    val tempoDePreparo: String,
    val tipo: String,
    val proteinas: Double,
    val carboidratos: Double,
    val gorduras: Double,
    val acucar: Double
)