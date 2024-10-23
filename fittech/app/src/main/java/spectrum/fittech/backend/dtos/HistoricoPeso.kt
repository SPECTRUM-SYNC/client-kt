package spectrum.fittech.backend.dtos

import java.time.LocalDate
import java.util.Objects


data class HistoricoPeso(
    val id: Long,
    val dataPostagem: String,
    val peso: Double,
    val pesoMeta: Double,
 )