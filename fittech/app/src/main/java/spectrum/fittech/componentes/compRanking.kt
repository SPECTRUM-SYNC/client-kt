package spectrum.fittech.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spectrum.fittech.R

@Composable
fun UserLevelProgressBar(level: Int, maxLevel: Int) {
    val progress = level / maxLevel.toFloat()


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "${(progress * 100).toInt()}%",
            style = TextStyle(
                fontSize = 18.sp,
                color = colorResource(id = R.color.failed)
            ),
        )

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(18.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = colorResource(id = R.color.fire),
        )
    }
}


@Composable
fun RankingUser(
    nome: String,
    posicao: Int,
    level: Int,
    maxLevel: Int,
    foto: Int,
    color: Color
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.card_ranking),
            contentDescription = "Card de ranking",
            modifier = Modifier
                .fillMaxSize()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(55.dp)
                    .offset(x = (20).dp, y = (-5).dp)

                    .border(
                        BorderStroke(2.dp, color),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.dalva),
                    contentDescription = "user",
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = stringResource(id = R.string.texto_ranking_posicao, posicao),
                modifier = Modifier.offset(x = (30).dp, y = (6).dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    color = color,
                    fontWeight = FontWeight.SemiBold
                ),
            )

            Text(
                text = nome,
                modifier = Modifier
                    .offset(x = (70).dp, y = (6).dp)
                    .fillMaxWidth(0.5f),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                ),
            )

            Text(
                text = stringResource(id = R.string.texto_ranking_posicao_ponto, maxLevel, level),
                modifier = Modifier.offset(x = (90).dp, y = (6).dp),
                style = TextStyle(
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    color = color,
                ),
            )
        }
    }

}