package spectrum.fittech.backend.dtos


data class HistoricoPeso(
    val id: Long,
    var dataPostagem: String,
    var peso: Double,
    var pesoMeta: Double,
 )