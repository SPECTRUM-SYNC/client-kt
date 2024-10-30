package spectrum.fittech.backend.dtos

import java.time.LocalDate
import java.util.Objects


data class HistoricoPeso(
    val id: Long,
    var dataPostagem: String,
    var peso: Double,
    var pesoMeta: Double,
 )