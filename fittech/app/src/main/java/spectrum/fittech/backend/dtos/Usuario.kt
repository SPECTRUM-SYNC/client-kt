package spectrum.fittech.backend.dtos

data class Usuario(
    val email: String,
    val senha: String
)

data class RespostaCadastro(
    val sucesso: Boolean,
    val mensagem: String
)