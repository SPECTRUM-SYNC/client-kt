package spectrum.fittech.backend.dtos

data class TreinoResponseDto(
    val id: Long,
    val descricao: String,
    val dataTreino: String,
    val tipoTreino: String,
    val status: String,
    val usuario: UsuarioGet,
)

data class TreinoCreateDto(
    val descricao: String,
    val dataTreino: String,
    val status: String,
    val tipoTreino: String,
    val usuarioId: Int?
)

data class DiasTreino(
    val dataTreino: String,
    val diaTreino: String,
    val status: String,
    val diaAtual: Boolean
)

data class TreinoCountDto(
    val diaDaSemana: String,
    val quantidadeTreinos: Long
)