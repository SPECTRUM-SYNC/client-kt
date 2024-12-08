package spectrum.fittech.backend.dtos

data class Receita(
    val id: Long,
    val nome: String,
    val ingredientes: List<String>,
    val modoPreparo: String,
    val calorias: String,
    val tempoPreparo: String,
    val tipo: String,
    val proteina: String,
    val carboidratos: String,
    val gorduras: String,
    val acucar: String,
    var img: String
)