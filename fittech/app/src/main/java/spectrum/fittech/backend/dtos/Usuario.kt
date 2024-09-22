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

data class Objetivo (
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
    val email: String,
    val nome: String
)

data class RespostaLogin(
    val token: String
)