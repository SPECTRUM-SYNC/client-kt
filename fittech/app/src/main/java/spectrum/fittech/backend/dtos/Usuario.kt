package spectrum.fittech.backend.dtos

data class Usuario(
    val email: String,
    val senha: String,
    val nome: String,
    val img: String
)

data class RespostaCadastro(
    val sucesso: Boolean,
    val mensagem: String
)