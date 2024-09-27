package spectrum.fittech.utils.receita



data class Receita(
    val nome: String,
    val horario: String,
    val image: String
)

val receitas = listOf(
    Receita(
        nome = "Sanduiche de Frango",
        horario = "Café da Manhã - 9:00 AM",
        image = "https://assets.unileversolutions.com/recipes-v2/106678.jpg"
    ),
    Receita(
        nome = "Frango com Quiabo",
        horario = "Almoço - 12:00 AM",
        image = "https://i.panelinha.com.br/i1/228-bk-3716-30-panelinha-29-01-20-foto260.webp"
    ),
    Receita(
        nome = "Macarronada",
        horario = "Café da Tarde - 4:00 PM",
        image = "https://www.receitasagora.com.br/wp-content/uploads/2020/07/receita-de-macarronada-simples-433x305.jpg"
    ),
    Receita(
        nome = "Sopa de Legumes",
        horario = "Jantar - 7:00 PM",
        image = "https://csc.princesasupermercados.com.br/blog/wp-content/uploads/2023/06/drinks-do-carnaval-5.png"
    ),
)
