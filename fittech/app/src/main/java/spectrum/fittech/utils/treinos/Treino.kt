package spectrum.fittech.utils.treinos


data class Treino(
    val nome: String,
    val repeticao: String,
    val backgroundImage: String,
    val beneficios: String,
    val tecnica: String,
    val image: String
)

val ganharMassa = listOf(
    Treino(
        nome = "Agachamento (Squat)",
        repeticao = "4x de 8 repetições",
        backgroundImage = "https://media1.tenor.com/m/Re3T3B66V9UAAAAd/barbellsquats-gymexercisesmen.gif",
        beneficios = "Desenvolvimento Muscular: Principalmente quadríceps, glúteos e isquiotibiais.\nForça Funcional: Melhora a força para atividades diárias.\nEstabilidade e Equilíbrio: Fortalece o core e melhora a coordenação.",
        tecnica = "Respiração: Inalar na descida e exalar na subida.\nAlinhamento: Manter joelhos alinhados com os pés.\nCore Ativado: Manter o abdômen contraído durante todo o movimento.\nAmplitude de Movimento: Completar o movimento com a maior amplitude possível, respeitando os limites do corpo.",
        image = "https://t4.ftcdn.net/jpg/01/91/29/65/360_F_191296551_uslyuDjwlYDnWkbApOpdjklmdPG3EkIz.jpg"
    ),
    Treino(
        nome = "Rosca Alternada",
        repeticao = "3x de 10 repetições",
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2022/09/rosca-biceps-direta-com-halteres.gif",
        beneficios = "Desenvolvimento Muscular: Fortalece os bíceps e antebraços.\nForça de Preensão: Melhora a força da pegada.\nEstabilidade Articular: Contribui para a estabilidade do cotovelo.",
        tecnica = "Postura: Manter as costas retas e os ombros para trás.\nMovimento Controlado: Levantar e abaixar os halteres de forma controlada.\nRespiração: Exalar ao levantar o peso e inalar ao abaixar.\nAlinhamento: Manter os cotovelos próximos ao corpo durante todo o movimento.",
        image = "https://p2.trrsf.com/image/fget/cf/774/0/images.terra.com/2024/04/01/1853915857-istock-1404354190.jpg"
    ),
    Treino(
        nome = "Tríceps na Máquina",
        repeticao = "3x de 12 repetições",
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2021/07/triceps-sentado-no-aparelho.gif",
        beneficios = "Desenvolvimento Muscular: Foca nos músculos tríceps.\nIsolamento Muscular: Permite um trabalho mais isolado do tríceps.\nFacilidade de Uso: Ideal para iniciantes e pessoas com dificuldade em usar pesos livres.",
        tecnica = "Posicionamento: Ajustar a máquina para que os cotovelos fiquem alinhados com o ponto de rotação do aparelho.\nMovimento: Estender completamente os braços sem travar os cotovelos.\nRespiração: Exalar ao empurrar e inalar ao voltar à posição inicial.\nControle: Manter o movimento lento e controlado.",
        image = "https://img.freepik.com/fotos-premium/mulher-atletica-treinando-seu-triceps-na-maquina-de-exercicios-de-crossover-de-cabo-no-ginasio_175682-27093.jpg"
    ),
    Treino(
        nome = "Supino Reto",
        repeticao = "4x de 6 repetições",
        backgroundImage = "https://media1.tenor.com/m/Re3T3B66V9UAAAAd/barbellsquats-gymexercisesmen.gif",
        beneficios = "Desenvolvimento Muscular: Trabalha peitoral, tríceps e deltoides.\nForça Funcional: Melhora a força para atividades diárias.\nEstabilidade do Core: Fortalece o abdômen e a região lombar.",
        tecnica = "Alinhamento: Manter as mãos na linha dos ombros e os cotovelos próximos ao corpo.\nPostura: Manter o corpo reto da cabeça aos pés.\nRespiração: Inalar ao descer e exalar ao subir.\nAmplitude de Movimento: Descer até que o peito quase toque a barra, respeitando os limites do corpo.",
        image = "https://pratiquefitness.com.br/blog/wp-content/uploads/2023/07/Como-fazer-supino-reto-corretamente-2.jpg"
    ),
    Treino(
        nome = "Remada com Barra",
        repeticao = "4x de 8 repetições",
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/costas-puxada-aberta-com-barra-no-pulley.gif",
        beneficios = "Desenvolvimento Muscular: Principalmente dorsais, bíceps e ombros.\nPostura: Melhora a postura ao fortalecer os músculos das costas.\nForça Funcional: Facilita atividades que envolvem puxar.",
        tecnica = "Posicionamento: Manter os pés firmes e a coluna reta, inclinando-se ligeiramente para frente.\nPegada: Manter uma pegada aberta na barra.\nMovimento: Puxar a barra até o abdômen, mantendo os cotovelos próximos ao corpo.\nRespiração: Exalar ao puxar a barra e inalar ao retornar à posição inicial.",
        image = "https://www.origym.com.br/banners/remada-unilateral.jpg"
    ),
    Treino(
        nome = "Elevação Lateral",
        repeticao = "3x de 15 repetições",
        backgroundImage = "https://www.hipertrofia.org/blog/wp-content/uploads/2023/11/dumbbell-lateral-raise.gif",
        beneficios = "Desenvolvimento Muscular: Fortalece os deltoides laterais.\nSimetria: Ajuda a desenvolver a simetria nos ombros.\nAmplitude de Movimento: Melhora a amplitude de movimento dos ombros.",
        tecnica = "Postura: Manter as costas retas e os joelhos levemente flexionados.\nMovimento: Levantar os halteres até a altura dos ombros com os braços ligeiramente flexionados.\nRespiração: Exalar ao levantar e inalar ao abaixar.\nControle: Evitar movimentos bruscos, mantendo o movimento controlado.",
        image = "https://conteudo.imguol.com.br/c/entretenimento/2f/2019/10/14/elevacao-lateral-1571066104977_v2_1210x866.jpg"
    )
)


val repeticao2 = "4x de 10 repetições"
val ganharMassa2 = listOf(
    Treino(
        nome = "Cadeira Extensora",
        repeticao = repeticao2,
        backgroundImage = "https://media.tenor.com/bqKtsSuqilQAAAAM/gym.gif",
        beneficios = "Desenvolvimento Muscular: Principalmente quadríceps.\nForça Funcional: Melhora a força das pernas para atividades diárias.\nPrevenção de Lesões: Fortalece os músculos ao redor do joelho, ajudando a prevenir lesões.",
        tecnica = "Posicionamento: Ajustar o assento e o encosto da cadeira para que os joelhos fiquem alinhados com o ponto de rotação do aparelho.\nMovimento: Estender as pernas completamente e retornar de forma controlada.\nRespiração: Exalar ao estender as pernas e inalar ao retornar.\nControle: Manter o movimento lento e controlado, evitando trancos.",
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1VRi7NU1K3i06x-ydm503soCReWVJKsXZpw&s"

    ),
    Treino(
        nome = "Rosca Scott",
        repeticao = repeticao2,
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/biceps-rosca-biceps-no-banco-scott-com-barra-W.gif",
        beneficios = "Desenvolvimento Muscular: Fortalece os bíceps de maneira mais isolada.\nForça de Preensão: Melhora a força da pegada.\nEstabilidade Articular: Contribui para a estabilidade do cotovelo.",
        tecnica = "Posicionamento: Sentar no banco Scott e apoiar a parte superior dos braços na almofada inclinada.\nMovimento: Levantar o peso até a altura dos ombros e abaixar de forma controlada.\nRespiração: Exalar ao levantar o peso e inalar ao abaixar.\nControle: Manter os cotovelos fixos na almofada durante todo o movimento.",
        image = "https://conteudo.imguol.com.br/c/entretenimento/85/2019/11/25/rosca-scott-musculacao-exercicio-1574704252441_v2_4x3.jpg"
    ),
    Treino(
        nome = "Tríceps no Pulley",
        repeticao = repeticao2,
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2021/07/triceps-puxada-no-pulley.gif",
        beneficios = "Desenvolvimento Muscular: Foca nos músculos tríceps.\nIsolamento Muscular: Permite um trabalho mais isolado do tríceps.\nFacilidade de Uso: Ideal para iniciantes e pessoas com dificuldade em usar pesos livres.",
        tecnica = "Posicionamento: Ajustar a altura do pulley para que os cotovelos fiquem próximos ao corpo.\nMovimento: Estender os braços para baixo, mantendo os cotovelos fixos ao lado do corpo.\nRespiração: Exalar ao estender os braços e inalar ao retornar à posição inicial.\nControle: Manter o movimento lento e controlado, evitando balançar o corpo.",
        image = "https://static1.minhavida.com.br/articles/9a/97/b7/eb/triceps-pulley-na-polia-orig-1.jpg"
    ),
    Treino(
        nome = "Crucifixo",
        repeticao = repeticao2,
        backgroundImage = "https://www.hipertrofia.org/blog/wp-content/uploads/2020/06/dumbbell-incline-fly.gif",
        beneficios = "Desenvolvimento Muscular: Trabalha peitoral de forma isolada.\nSimetria Muscular: Ajuda a desenvolver a simetria entre os lados do peitoral.\nAmplitude de Movimento: Melhora a flexibilidade do peitoral.",
        tecnica = "Posicionamento: Deitar-se em um banco plano com os pés firmes no chão.\nMovimento: Abrir os braços até sentir o alongamento no peitoral e retornar à posição inicial.\nRespiração: Inalar ao abrir os braços e exalar ao fechá-los.\nControle: Manter o movimento lento e controlado, evitando esticar excessivamente os ombros.",
        image = "https://conteudo.imguol.com.br/c/entretenimento/88/2019/09/09/crucifixo-musculacao-exercicio-academia-1568071568051_v2_1254x836.jpg"
    ),
    Treino(
        nome = "Puxada Alta com Triângulo",
        repeticao = repeticao2,
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/costas-puxada-para-frente-no-pulley-com-triangulo.gif",
        beneficios = "Desenvolvimento Muscular: Trabalha os músculos das costas, incluindo trapézio, romboides e latíssimos.\nForça Funcional: Melhora a força para atividades de puxar.\nEstabilidade do Core: Fortalece o abdômen e a região lombar.",
        tecnica = "Posicionamento: Sentar-se com os pés firmes e ajustar a almofada das coxas.\nMovimento: Puxar o triângulo em direção ao peito, mantendo os cotovelos próximos ao corpo.\nRespiração: Exalar ao puxar e inalar ao retornar à posição inicial.\nControle: Manter o movimento lento e controlado, evitando balançar o corpo.",
        image = "https://www.dicasdetreino.com.br/wp-content/uploads/2017/07/Puxada-com-tri%C3%A2ngulo-barra-fixa-com-tri%C3%A2ngulo-no-treino-de-costas.jpg"
    ),
    Treino(
        nome = "Elevação Frontal",
        repeticao = repeticao2,
        backgroundImage = "https://www.hipertrofia.org/blog/wp-content/uploads/2023/11/dumbbell-front-raise.gif",
        beneficios = "Desenvolvimento Muscular: Fortalece os deltoides anteriores.\nSimetria: Ajuda a desenvolver a simetria nos ombros.\nAmplitude de Movimento: Melhora a amplitude de movimento dos ombros.",
        tecnica = "Postura: Manter as costas retas e os joelhos levemente flexionados.\nMovimento: Levantar os halteres até a altura dos ombros com os braços ligeiramente flexionados.\nRespiração: Exalar ao levantar e inalar ao abaixar.\nControle: Evitar movimentos bruscos, mantendo o movimento controlado.",
        image = "https://i.ytimg.com/vi/jhxLYSm_P-k/maxresdefault.jpg"
    )
)

val repeticaoPeso = "4x de 20 repetições"
val perderPeso1 = listOf(
    Treino(
        nome = "Agachamento (Squat)",
        repeticao = repeticaoPeso,
        backgroundImage = "https://media1.tenor.com/m/Re3T3B66V9UAAAAd/barbellsquats-gymexercisesmen.gif",
        beneficios = "Desenvolvimento Muscular: Principalmente quadríceps, glúteos e isquiotibiais.\nForça Funcional: Melhora a força para atividades diárias.\nEstabilidade e Equilíbrio: Fortalece o core e melhora a coordenação.",
        tecnica = "Respiração: Inalar na descida e exalar na subida.\nAlinhamento: Manter joelhos alinhados com os pés.\nCore Ativado: Manter o abdômen contraído durante todo o movimento.\nAmplitude de Movimento: Completar o movimento com a maior amplitude possível, respeitando os limites do corpo.",
        image = "https://t4.ftcdn.net/jpg/01/91/29/65/360_F_191296551_uslyuDjwlYDnWkbApOpdjklmdPG3EkIz.jpg"
    ),
    Treino(
        nome = "Rosca Alternada",
        repeticao = repeticaoPeso,
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2022/09/rosca-biceps-direta-com-halteres.gif",
        beneficios = "Desenvolvimento Muscular: Fortalece os bíceps e antebraços.\nForça de Preensão: Melhora a força da pegada.\nEstabilidade Articular: Contribui para a estabilidade do cotovelo.",
        tecnica = "Postura: Manter as costas retas e os ombros para trás.\nMovimento Controlado: Levantar e abaixar os halteres de forma controlada.\nRespiração: Exalar ao levantar o peso e inalar ao abaixar.\nAlinhamento: Manter os cotovelos próximos ao corpo durante todo o movimento.",
        image = "https://t4.ftcdn.net/jpg/01/91/29/65/360_F_191296551_uslyuDjwlYDnWkbApOpdjklmdPG3EkIz.jpg"
    ),
    Treino(
        nome = "Tríceps na Máquina",
        repeticao = repeticaoPeso,
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2021/07/triceps-sentado-no-aparelho.gif",
        beneficios = "Desenvolvimento Muscular: Foca nos músculos tríceps.\nIsolamento Muscular: Permite um trabalho mais isolado do tríceps.\nFacilidade de Uso: Ideal para iniciantes e pessoas com dificuldade em usar pesos livres.",
        tecnica = "Posicionamento: Ajustar a máquina para que os cotovelos fiquem alinhados com o ponto de rotação do aparelho.\nMovimento: Estender completamente os braços sem travar os cotovelos.\nRespiração: Exalar ao empurrar e inalar ao voltar à posição inicial.\nControle: Manter o movimento lento e controlado.",
        image = "https://t4.ftcdn.net/jpg/01/91/29/65/360_F_191296551_uslyuDjwlYDnWkbApOpdjklmdPG3EkIz.jpg"
    ),
    Treino(
        nome = "Flexão de Braço",
        repeticao = repeticaoPeso,
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2021/04/flexao-de-bracos.gif",
        beneficios = "Desenvolvimento Muscular: Trabalha peitoral, tríceps e deltoides.\nForça Funcional: Melhora a força para atividades diárias.\nEstabilidade do Core: Fortalece o abdômen e a região lombar.",
        tecnica = "Alinhamento: Manter as mãos na linha dos ombros e os cotovelos próximos ao corpo.\nPostura: Manter o corpo reto da cabeça aos pés.\nRespiração: Inalar ao descer e exalar ao subir.\nAmplitude de Movimento: Descer até que o peito quase toque o chão, respeitando os limites do corpo.",
        image = "https://www.smartfit.com.br/news/wp-content/uploads/2014/10/flex%C3%A3o-de-bra%C3%A7o-como-fazer.jpg"
    ),
    Treino(
        nome = "Puxada na Frente",
        repeticao = repeticaoPeso,
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/costas-puxada-aberta-com-barra-no-pulley.gif",
        beneficios = "Desenvolvimento Muscular: Principalmente dorsais, bíceps e ombros.\nPostura: Melhora a postura ao fortalecer os músculos das costas.\nForça Funcional: Facilita atividades que envolvem puxar.",
        tecnica = "Posicionamento: Sentar com os pés firmes e ajustar a almofada das coxas.\nPegada: Manter uma pegada aberta na barra.\nMovimento: Puxar a barra até a parte superior do peito, mantendo os cotovelos para baixo e para trás.\nRespiração: Exalar ao puxar a barra e inalar ao retornar à posição inicial.",
        image = "https://image.tuasaude.com/media/article/ll/ae/puxada-frontal_63648_l.jpg"
    ),
    Treino(
        nome = "Elevação Lateral",
        repeticao = repeticaoPeso,
        backgroundImage = "https://www.hipertrofia.org/blog/wp-content/uploads/2023/11/dumbbell-lateral-raise.gif",
        beneficios = "Desenvolvimento Muscular: Fortalece os deltoides laterais.\nSimetria: Ajuda a desenvolver a simetria nos ombros.\nAmplitude de Movimento: Melhora a amplitude de movimento dos ombros.",
        tecnica = "Postura: Manter as costas retas e os joelhos levemente flexionados.\nMovimento: Levantar os halteres até a altura dos ombros com os braços ligeiramente flexionados.\nRespiração: Exalar ao levantar e inalar ao abaixar.\nControle: Evitar movimentos bruscos, mantendo o movimento controlado.",
        image = "https://conteudo.imguol.com.br/c/entretenimento/2f/2019/10/14/elevacao-lateral-1571066104977_v2_1210x866.jpg"
    )
)

val repeticaoPeso2 = "4x de 15 repetições"
val perderPeso2 = listOf(
    Treino(
        nome = "Cadeira Extensora",
        repeticao = repeticaoPeso2,
        backgroundImage = "https://media1.tenor.com/m/bqKtsSuqilQAAAAC/gym.gif",
        beneficios = "Desenvolvimento Muscular: Principalmente quadríceps.\nForça Funcional: Melhora a força das pernas para atividades diárias.\nPrevenção de Lesões: Fortalece os músculos ao redor do joelho, ajudando a prevenir lesões.",
        tecnica = "Respiração: Inalar na descida e exalar na subida.\nAlinhamento: Manter joelhos alinhados com os pés.\nCore Ativado: Manter o abdômen contraído durante todo o movimento.\nAmplitude de Movimento: Completar o movimento com a maior amplitude possível, respeitando os limites do corpo.",
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1VRi7NU1K3i06x-ydm503soCReWVJKsXZpw&s"
    ),
    Treino(
        nome = "Rosca Martelo",
        repeticao = repeticaoPeso2,
        backgroundImage = "https://www.hipertrofia.org/blog/wp-content/uploads/2023/04/dumbbell-hammer-curl-v-2.gif",
        beneficios = "Desenvolvimento Muscular: Fortalece os bíceps e antebraços.\nForça de Preensão: Melhora a força da pegada.\nEstabilidade Articular: Contribui para a estabilidade do cotovelo.",
        tecnica = "Postura: Manter as costas retas e os ombros para trás.\nMovimento Controlado: Levantar e abaixar os halteres de forma controlada.\nRespiração: Exalar ao levantar o peso e inalar ao abaixar.\nAlinhamento: Manter os cotovelos próximos ao corpo durante todo o movimento.",
        image = "https://image.tuasaude.com/media/article/eq/sm/rosca-martelo_63246_l.jpg"
    ),
    Treino(
        nome = "Tríceps com Máquina 2",
        repeticao = repeticaoPeso2,
        backgroundImage = "https://fitnessprogramer.com/wp-content/uploads/2021/09/Triceps-Extension-Machine.gif",
        beneficios = "Desenvolvimento Muscular: Foca nos músculos tríceps.\nIsolamento Muscular: Permite um trabalho mais isolado do tríceps.\nFacilidade de Uso: Ideal para iniciantes e pessoas com dificuldade em usar pesos livres.",
        tecnica = "Posicionamento: Ajustar a máquina para que os cotovelos fiquem alinhados com o ponto de rotação do aparelho.\nMovimento: Estender completamente os braços sem travar os cotovelos.\nRespiração: Exalar ao empurrar e inalar ao voltar à posição inicial.\nControle: Manter o movimento lento e controlado.",
        image = "https://totalhealth.com.br/uploads/pagina/elemento/campo/2023/01/ei1ZHqPxS8R1tXuK/505rrf-treino-3.jpg"
    ),
    Treino(
        nome = "Flexão de Braço",
        repeticao = repeticaoPeso2,
        backgroundImage = "https://media1.tenor.com/m/bqKtsSuqilQAAAAC/gym.gif",
        beneficios = "Desenvolvimento Muscular: Trabalha peitoral, tríceps e deltoides.\nForça Funcional: Melhora a força para atividades diárias.\nEstabilidade do Core: Fortalece o abdômen e a região lombar.",
        tecnica = "Alinhamento: Manter as mãos na linha dos ombros e os cotovelos próximos ao corpo.\nPostura: Manter o corpo reto da cabeça aos pés.\nRespiração: Inalar ao descer e exalar ao subir.\nAmplitude de Movimento: Descer até que o peito quase toque o chão, respeitando os limites do corpo.",
        image = "https://www.smartfit.com.br/news/wp-content/uploads/2014/10/flex%C3%A3o-de-bra%C3%A7o-como-fazer.jpg"
    ),
    Treino(
        nome = "Remada com Barra",
        repeticao = repeticaoPeso2,
        backgroundImage = "https://media.tenor.com/AYJ_bNXDvoUAAAAM/workout-muscles.gif",
        beneficios = "Desenvolvimento Muscular: Trabalha os músculos das costas, incluindo trapézio, romboides e latíssimos.\nForça Funcional: Melhora a força para atividades de puxar.\nEstabilidade do Core: Fortalece o abdômen e a região lombar.",
        tecnica = "Postura: Manter as costas retas e os joelhos levemente flexionados.\nAlinhamento: Puxar a barra em direção ao abdômen, mantendo os cotovelos próximos ao corpo.\nRespiração: Exalar ao puxar e inalar ao soltar a barra.\nControle: Realizar o movimento de forma lenta e controlada.",
        image = "https://www.origym.com.br/banners/remada-unilateral.jpg"
    ),
    Treino(
        nome = "Elevação Frontal",
        repeticao = repeticaoPeso2,
        backgroundImage = "https://www.hipertrofia.org/blog/wp-content/uploads/2023/11/dumbbell-front-raise.gif",
        beneficios = "Desenvolvimento Muscular: Fortalece os deltoides anteriores.\nSimetria: Ajuda a desenvolver a simetria nos ombros.\nAmplitude de Movimento: Melhora a amplitude de movimento dos ombros.",
        tecnica = "Postura: Manter as costas retas e os joelhos levemente flexionados.\nMovimento: Levantar os halteres até a altura dos ombros com os braços ligeiramente flexionados.\nRespiração: Exalar ao levantar e inalar ao abaixar.\nControle: Evitar movimentos bruscos, mantendo o movimento controlado.",
        image = "https://i.ytimg.com/vi/jhxLYSm_P-k/maxresdefault.jpg"
    )
)

val cardioAlta = listOf(
    Treino(
        nome = "Squat Jump",
        repeticao = "3 séries de 15 repetições",
        backgroundImage = "https://j.gifs.com/lO82oM.gif",
        beneficios = "Queima Calórica: Alta queima de calorias e gordura.\nSaúde Cardiovascular: Melhora a saúde do coração e a capacidade pulmonar.\nResistência: Aumenta a resistência física geral.",
        tecnica = "Postura: Manter o corpo ereto com os ombros relaxados.\nPulos: Saltar o mais alto possível e aterrisar suavemente em posição de agachamento.\nRespiração: Respirar de forma rítmica e constante.\nHidratação: Manter-se hidratado durante e após o exercício.",
        image = "https://t3.ftcdn.net/jpg/04/35/78/68/360_F_435786899_v92ZMhvgR3OEu9EHCDihkGGSI6dUPWYB.jpg"
    ),
    Treino(
        nome = "Plank Jacks",
        repeticao = "40 minutos",
        backgroundImage = "https://hips.hearstapps.com/hmg-prod/images/766/fitgif-friday-sweep-plank-slider-thumbnail-override-1509654737.gif",
        beneficios = "Coordenação: Melhora a coordenação e agilidade.\nQueima Calórica: Alta queima de calorias em curto tempo.\nResistência: Aumenta a resistência cardiovascular e muscular.",
        tecnica = "Postura: Manter o corpo ereto e olhar à frente.\nPulos: Saltar com os pés juntos e aterrissar suavemente.\nRespiração: Respirar de forma rítmica e constante.\nMovimento de Braços: Utilizar os pulsos para girar a corda, não os braços.",
        image = "https://i.ytimg.com/vi/PR9nHQXCZMo/maxresdefault.jpg"
    ),
    Treino(
        nome = "Prancha Escalada",
        repeticao = "3 séries de 20 repetições por perna",
        backgroundImage = "https://i.pinimg.com/originals/62/c5/75/62c5759fb23253bf1153dd4f2aa0ce83.gif",
        beneficios = "Queima Calórica: Alta queima de calorias e gordura.\nSaúde Cardiovascular: Melhora a saúde do coração e a capacidade pulmonar.\nResistência: Aumenta a resistência física geral.",
        tecnica = "Postura: Manter o corpo reto, com as mãos diretamente sob os ombros.\nMovimento: Alternar a movimentação das pernas como se estivesse escalando uma montanha.\nRespiração: Manter uma respiração ritmada e profunda.\nHidratação: Manter-se hidratado durante e após o exercício.",
        image = "https://www.hipertrofia.org/blog/wp-content/uploads/2023/05/exercicio-escalador.jpg"
    ),
    Treino(
        nome = "Box Jump",
        repeticao = "40 minutos",
        backgroundImage = "https://bod-blog-assets.prod.cd.beachbodyondemand.com/bod-blog/wp-content/uploads/2024/01/09113749/box-jump-600-demo.gif",
        beneficios = "Coordenação: Melhora a coordenação e agilidade.\nQueima Calórica: Alta queima de calorias em curto tempo.\nResistência: Aumenta a resistência cardiovascular e muscular.",
        tecnica = "Postura: Manter o corpo ereto e olhar à frente.\nPulos: Saltar com os pés juntos e aterrissar suavemente.\nRespiração: Respirar de forma rítmica e constante.\nMovimento de Braços: Utilizar os pulsos para girar a corda, não os braços.",
        image = "https://experiencelife.lifetime.life/wp-content/uploads/2021/03/Box-Jump.jpg"
    )
)

val cardioBaixa = listOf(
    Treino(
        nome = "Polichinelos Moderados",
        repeticao = "30 minutos",
        backgroundImage = "https://www.hipertrofia.org/blog/wp-content/uploads/2023/11/polichinelos.gif",
        beneficios = "Saúde Cardiovascular: Melhora a saúde do coração e a circulação.\nBaixo Impacto: Ótimo para pessoas com problemas nas articulações.\nBem-Estar Mental: Reduz o estresse e melhora o humor.",
        tecnica = "Postura: Manter o corpo ereto com os ombros relaxados.\nMovimento: Abrir e fechar as pernas enquanto se eleva e abaixa os braços.\nRespiração: Respirar profundamente e de forma ritmada.\nHidratação: Manter-se hidratado.",
        image = "https://blogeducacaofisica.com.br/wp-content/uploads/2022/07/polichinelos-1.jpg"
    ),
    Treino(
        nome = "Agachamentos Leves",
        repeticao = "3 séries de 15 repetições",
        backgroundImage = "https://media.tenor.com/3Id5iyj7kuUAAAAM/agachamento.gif",
        beneficios = "Total do Corpo: Trabalha todos os principais grupos musculares.\nBaixo Impacto: Ideal para pessoas com problemas nas articulações.\nResistência: Aumenta a resistência cardiovascular e muscular.",
        tecnica = "Postura: Manter os pés na largura dos ombros, com o peso nos calcanhares.\nMovimento: Agachar até os joelhos formarem um ângulo de 90 graus, depois retornar à posição inicial.\nRespiração: Inspirar ao descer e expirar ao subir.\nHidratação: Manter-se hidratado.",
        image = "https://s3.sa-east-1.amazonaws.com/static.activodeporte.com/brasil/uploads/2022/11/02133926/confident-fit-couple-standing-in-squatting-positio-2022-10-28-05-28-20-utc-1.jpg"
    ),
    Treino(
        nome = "Prancha",
        repeticao = "3 séries de 1 minuto",
        backgroundImage = "https://i.pinimg.com/originals/bb/e7/86/bbe78693697daed4037ea5439025f68f.gif",
        beneficios = "Saúde Cardiovascular: Melhora a saúde do coração e a circulação.\nBaixo Impacto: Ótimo para pessoas com problemas nas articulações.\nBem-Estar Mental: Reduz o estresse e melhora o humor.",
        tecnica = "Postura: Manter o corpo alinhado da cabeça aos pés, com os cotovelos diretamente sob os ombros.\nMovimento: Manter a posição estática, evitando deixar os quadris caírem ou levantarem demais.\nRespiração: Respirar profundamente e de forma controlada.\nHidratação: Manter-se hidratado.",
        image = "https://www.oxerbrasil.com.br/wp-content/uploads/2024/03/exercicio-de-prancha.jpg"
    ),
    Treino(
        nome = "Abdominal Curto",
        repeticao = "3 séries de 20 repetições",
        backgroundImage = "https://s2-ug.ap4r.com/image-aigc-article/seoPic/origin/b79cc2135caceac828548a01d65511dc1f821283.gif",
        beneficios = "Total do Corpo: Trabalha todos os principais grupos musculares.\nBaixo Impacto: Ideal para pessoas com problemas nas articulações.\nResistência: Aumenta a resistência cardiovascular e muscular.",
        tecnica = "Postura: Deitar-se de costas com os joelhos dobrados e os pés apoiados no chão.\nMovimento: Levantar ligeiramente os ombros do chão, contraindo os músculos abdominais.\nRespiração: Expirar ao subir e inspirar ao descer.\nHidratação: Manter-se hidratado.",
        image = "https://www.saudevitalidade.com/wp-content/uploads/2022/10/Capturar.jpg"
    )
)

val funcional = listOf(
    Treino(
        nome = "Abdominal Curto",
        repeticao = "3 séries de 20 repetições",
        backgroundImage = "https://s2-ug.ap4r.com/image-aigc-article/seoPic/origin/b79cc2135caceac828548a01d65511dc1f821283.gif",
        beneficios = "Força do Core: Fortalece o abdômen.\nEstabilidade: Melhora o equilíbrio e a postura.\nFunção Funcional: Melhora a capacidade de realizar atividades diárias.",
        tecnica = "Postura: Deitar-se de costas com os joelhos dobrados e os pés apoiados no chão.\nMovimento: Levantar ligeiramente os ombros do chão, contraindo os músculos abdominais.\nRespiração: Expirar ao subir e inspirar ao descer.\nControle: Movimentos lentos e controlados.",
        image = "https://www.saudevitalidade.com/wp-content/uploads/2022/10/Capturar.jpg"
    ),
    Treino(
        nome = "Prancha",
        repeticao = "3 séries de 1 minuto",
        backgroundImage = "https://i.pinimg.com/originals/bb/e7/86/bbe78693697daed4037ea5439025f68f.gif",
        beneficios = "Força do Core: Fortalece o abdômen e a lombar.\nEstabilidade: Melhora o equilíbrio e a postura.\nFunção Funcional: Melhora a capacidade de realizar atividades diárias.",
        tecnica = "Postura: Manter o corpo alinhado da cabeça aos pés, com os cotovelos diretamente sob os ombros.\nMovimento: Manter a posição estática, evitando deixar os quadris caírem ou levantarem demais.\nRespiração: Respirar profundamente e de forma controlada.\nFoco: Manter o olhar no chão para evitar tensão no pescoço.",
        image = "https://www.oxerbrasil.com.br/wp-content/uploads/2024/03/exercicio-de-prancha.jpg"
    ),
    Treino(
        nome = "Flexão de Braço",
        repeticao = "3 séries de 15 repetições",
        backgroundImage = "https://www.hipertrofia.org/blog/wp-content/uploads/2019/12/negative-push-up.gif",
        beneficios = "Força Superior: Fortalece peitoral, ombros e tríceps.\nEstabilidade do Core: Fortalece o abdômen e a lombar.\nFunção Funcional: Melhora a capacidade de empurrar e suportar peso.",
        tecnica = "Postura: Manter o corpo em linha reta dos pés à cabeça.\nDescida: Descer até o peito quase tocar o chão, mantendo os cotovelos próximos ao corpo.\nSubida: Empurrar o corpo de volta à posição inicial.\nControle: Movimentos lentos e controlados.",
        image = "https://www.smartfit.com.br/news/wp-content/uploads/2014/10/flex%C3%A3o-de-bra%C3%A7o-como-fazer.jpg"
    ),
    Treino(
        nome = "Agachamento",
        repeticao = "3 séries de 15 repetições",
        backgroundImage = "https://media.tenor.com/3Id5iyj7kuUAAAAM/agachamento.gif",
        beneficios = "Força Inferior: Fortalece glúteos, quadríceps e isquiotibiais.\nEstabilidade: Melhora o equilíbrio e a postura.\nFunção Funcional: Melhora a capacidade de sentar e levantar objetos.",
        tecnica = "Postura: Manter os pés na largura dos ombros, com o peso nos calcanhares.\nMovimento: Agachar até os joelhos formarem um ângulo de 90 graus, depois retornar à posição inicial.\nRespiração: Inspirar ao descer e expirar ao subir.\nFoco: Manter o olhar à frente.",
        image = "https://alexandrekusabara.com.br/wp-content/uploads/2023/09/agachmanento-livre-1.jpg"
    )
)

val alongamento = listOf(
    Treino(
        nome = "Postura da Cobra",
        repeticao = "2 minutos",
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/alongamento-serpente.gif",
        beneficios = "Flexibilidade: Aumenta a flexibilidade da coluna e dos isquiotibiais.\nAlívio de Tensão: Reduz a tensão na parte inferior das costas.\nPostura: Melhora a postura e o alinhamento da coluna.",
        tecnica = "Postura: Deitar de barriga para baixo com as mãos sob os ombros.\nMovimento: Elevar o peito do chão, estendendo os braços.\nRespiração: Inspirar ao levantar o corpo e expirar ao descer.\nSegurança: Manter os quadris no chão e não forçar a lombar.",
        image = "https://yogaouioga.com.br/wp-content/uploads/2022/11/bhujangasana-asana-postura-da-cobra-blog-yoga-ou-ioga.jpeg"
    ),
    Treino(
        nome = "Postura da Cobra a fundo",
        repeticao = "2 minutos",
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2019/01/alongamento-postura-crianca-para-cobra.gif",
        beneficios = "Flexibilidade: Aumenta a flexibilidade da coluna e quadris.\nAlívio de Tensão: Reduz a tensão na parte inferior das costas.\nRelaxamento: Promove relaxamento muscular e bem-estar.",
        tecnica = "Posição Inicial: Começar na postura da criança com os braços estendidos à frente.\nMovimento: Deslizar para frente até a postura da cobra, elevando o peito.\nRespiração: Inspirar ao mover para a cobra e expirar ao retornar à postura da criança.\nFluidez: Manter o movimento suave e controlado.",
        image = "https://www.mundoboaforma.com.br/wp-content/uploads/2023/10/Bhujangasana.webp"
    ),
    Treino(
        nome = "Alongamento de Coluna Sentado",
        repeticao = "2 minutos",
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2021/09/alongamento-de-coluna-sentado.gif",
        beneficios = "Flexibilidade: Aumenta a flexibilidade da coluna e dos isquiotibiais.\nAlívio de Tensão: Reduz a tensão na parte inferior das costas.\nPostura: Melhora a postura e o alinhamento da coluna.",
        tecnica = "Postura: Sentar com as pernas estendidas à frente.\nMovimento: Inclinar-se à frente alcançando os pés ou tornozelos.\nRespiração: Respirar profundamente e relaxar na posição.\nAlinhamento: Manter a coluna ereta e os ombros relaxados.",
        image = "https://fisiostudiopilates.com.br/wp-content/uploads/2017/06/foto-8a.jpg"
    ),
    Treino(
        nome = "Alongamento em Pé para Trás",
        repeticao = "2 minutos",
        backgroundImage = "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/alongamento-em-pe-para-tras.gif",
        beneficios = "Flexibilidade: Aumenta a flexibilidade da coluna e quadris.\nAlívio de Tensão: Reduz a tensão na parte inferior das costas.\nRelaxamento: Promove relaxamento muscular e bem-estar.",
        tecnica = "Postura: Ficar de pé com os pés juntos e as mãos nos quadris.\nMovimento: Inclinar-se para trás suavemente, arqueando a coluna.\nRespiração: Inspirar ao inclinar-se para trás e expirar ao retornar.\nSegurança: Não forçar o movimento, mantendo-o confortável.",
        image = "https://lirp.cdn-website.com/fae6c407/dms3rep/multi/opt/7521-640w.jpg"
    )
)


