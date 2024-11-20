package spectrum.fittech.backend.mock

import spectrum.fittech.backend.builder.gson
import spectrum.fittech.backend.dtos.AdicionarTopRank
import spectrum.fittech.backend.dtos.AtivarUsuario
import spectrum.fittech.backend.dtos.AtualizarImagem
import spectrum.fittech.backend.dtos.AtualizarUsuario
import spectrum.fittech.backend.dtos.EnvioEmailUsuario
import spectrum.fittech.backend.dtos.Objetivo
import spectrum.fittech.backend.dtos.Usuario
import spectrum.fittech.backend.dtos.UsuarioLogin
import spectrum.fittech.backend.dtos.UsuarioLoginGoogle
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

// Dados do Usuário
val usuario = Usuario(
    nome = "Rafael Reis",
    email = "rafael.reis@sptech.school",
    senha = "Madalena13#",
    img = "https://exemplo.com/imagem.png"
)


// Formato de data
val formatoData = SimpleDateFormat("yyyy-MM-dd")
val dataNascimento = formatoData.format(Date(90, 5, 15)) // Data de nascimento: 1990-06-15

// Atualização de dados do usuário
val usuarioAtualizar = AtualizarUsuario(
    nome = "João Silva",
    senha = "9242#Rick",
    img = "https://exemplo.com/imagem.png",
    genero = "Masculino",
    peso = 95.5,
    altura = 175,
    dataNascimento = "2001-01-10",
    meta = "Perda de Peso",
    nivelCondicao = "Intermediário"
)


// Definição de objetivo
val objetivo = Objetivo(
    objetivo = "Ganho de Massa Muscular"
)

// Ativação de conta de usuário
val usuarioAtivar = AtivarUsuario(
    nome = "Luiz Souza",
    email = "luiz.souza@gmail.com",
    img = "https://exemplo.com/imagem-avatar.png",
    meta = "Ganho de Massa Muscular",
    dataNascimento = LocalDateTime.now(),
    genero = "Masculino",
    peso = 85.0,
    altura = 180.0,
    nivelCondicao = "Avançado",
    contaAtiva = true,
    pontuacao = 2000.0,
    objetivo = objetivo
)

// Atualização de imagem do usuário
val atualizarImagemUsuario = gson.toJson(
    AtualizarImagem(
        imageFile = "profile.png"
    )
)

// Dados de login do usuário
val usuarioLogin = UsuarioLogin(
    email = "winycios@gmail.com",
    senha = "Madalena13#"
)

val usuarioLoginGoogle = UsuarioLoginGoogle(
    email = "winycios@gmail.com",
    nome = "Winycios"
)

// Envio de e-mail ao usuário
val usuarioEnvioEmail = EnvioEmailUsuario(
    para = "rafael.reis@sptech.school",
    nome = "Rafael Reis"
)

// Adição ao Top Rank
val userTopRank = gson.toJson(
    AdicionarTopRank(
        nome = "Luiz Souza",
        email = "luiz.souza@gmail.com",
        senha = "senhaSegura123",
        img = "https://exemplo.com/imagem-avatar.png",
        meta = "Ganho de Massa Muscular",
        dataNascimento = LocalDateTime.now(),
        genero = "Masculino",
        peso = 85.0,
        altura = 180.0,
        nivelCondicao = "Avançado",
        contaAtiva = true,
        pontuacao = 2000.0,
        horaSenhaAtualizacao = LocalDateTime.now(),
        objetivo = objetivo
    )
)