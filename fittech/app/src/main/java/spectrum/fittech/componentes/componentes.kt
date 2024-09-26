package spectrum.fittech.componentes

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.R
import spectrum.fittech.TelaLogin


@Composable
fun BotaoVoltarTelaLogin() {
    val context = LocalContext.current


    IconButton(onClick = {
        val telaLogin = Intent(context, TelaLogin::class.java)
        context.startActivity(telaLogin)
    }) {
        Icon(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("android.resource://spectrum.fittech/raw/setaesquerda")
                    .decoderFactory(SvgDecoder.Factory())
                    .build()
            ),
            contentDescription = "Home",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }
}