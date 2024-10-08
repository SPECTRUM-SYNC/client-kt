package spectrum.fittech.backend.dtos

import java.time.LocalDateTime

data class Usuario(
    val email: String,
    val senha: String,
    val nome: String,
    val img: String
)

data class AdicionarTopRank(
    val nome: String,
    val email: String,
    val senha: String,
    val img: String,
    val meta : String,
    val dataNascimento: LocalDateTime,
    val genero: String,
    val peso : Double,
    val altura : Double,
    val nivelCondicao : String,
    val contaAtiva : Boolean,
    val pontuacao : Double,
    val horaSenhaAtualizacao : LocalDateTime,
    val objetivo: Objetivo
)

data class AtualizarUsuario(
    val nome: String,
    val senha: String,
    val img: String,
    val genero: String,
    val peso : Double,
    val altura : Int,
    val dataNascimento: String,
    val meta : String,
    val nivelCondicao : String
)

data class AtualizarUsuarioPerfil(
    val nome: String,
    val altura : Int,
    val dataNascimento: String,
    val nivelCondicao : String
)


data class AtivarUsuario(
    val nome: String,
    val email: String,
    val img: String,
    val dataNascimento: LocalDateTime,
    val genero: String,
    val peso : Double,
    val altura : Double,
    val nivelCondicao : String,
    val meta : String,
    val contaAtiva : Boolean,
    val pontuacao : Double,
    val objetivo: Objetivo
)

data class AtualizarImagem(
    val imageFile : String
)

data class RespostaRequisicao (
    val mensagem : String
)

data class Objetivo (
    val objetivo : String
)

data class ObjetivoUsuario (
    val id : Int,
    val objetivo : String
)

data class RespostaRank(
    val mensagem: String
)

data class RespostaCadastro(
    val sucesso: Boolean,
    val mensagem: String
)

data class EnvioEmailUsuario(
    val para: String,
    val nome: String
)
data class RespostaEnvioEmail(
    val mensagem: String
)


// DTOS LOGIN
data class UsuarioLogin(
    val email: String,
    val senha: String,
)

data class UsuarioLoginGoogle(
    val email: String? = null,
    val nome: String? = null,
)

data class RespostaLogin(
    val token: String,
    val userId: Int,
    val nome: String
)


data class UsuarioGet(
    val id: Int,
    val nome: String,
    val email: String,
    val img: String,
    val dataNascimento: String, // Usar String para lidar diretamente com o formato da API
    val genero: String,
    val peso: Double,
    val altura: Int, // Mudança de Double para Int
    val nivelCondicao: String,
    val meta: String,
    val contaAtiva: Boolean,
    val pontuacao: Double,
    val objetivo: ObjetivoUsuario
) {
    constructor() : this(0, "", "", "", "", "", 0.0, 0, "", "", false, 0.0, ObjetivoUsuario(0, ""))
}