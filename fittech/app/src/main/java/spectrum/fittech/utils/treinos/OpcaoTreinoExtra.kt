package spectrum.fittech.utils.treinos

data class OpcaoTreinoExtra(
    val nome: String,
    val treinos: List<Treino>,
    val image: String
)

val opcoesTreinos = listOf(
    OpcaoTreinoExtra(
        nome = "cardio Alta Intensidade",
        treinos = cardioAlta,
        image = "https://www.hipertrofia.org/blog/wp-content/uploads/2023/05/exercicio-escalador.jpg"
    ),
    OpcaoTreinoExtra(
        nome = "Cardio baixa Intensidade",
        treinos = cardioBaixa,
        image = "https://www.oxerbrasil.com.br/wp-content/uploads/2024/03/exercicio-de-prancha.jpg"
    ),
    OpcaoTreinoExtra(
        nome = "Funcional",
        treinos = funcional,
        image = "https://alexandrekusabara.com.br/wp-content/uploads/2023/09/agachmanento-livre-1.jpg"
    ),
    OpcaoTreinoExtra(
        nome = "Alongamento",
        treinos = alongamento,
        image = "https://yogaouioga.com.br/wp-content/uploads/2022/11/bhujangasana-asana-postura-da-cobra-blog-yoga-ou-ioga.jpeg"
    )
)
