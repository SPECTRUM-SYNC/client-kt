package spectrum.fittech.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import spectrum.fittech.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalPeso(isDialogOpen: MutableState<Boolean>) {

    var meta by remember { mutableIntStateOf(0) }
    var metaInvalida by remember { mutableStateOf(false) }

    var peso by remember { mutableIntStateOf(0) }
    var pesoInvalida by remember { mutableStateOf(false) }

    val dataAtual = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()) }


    Dialog(onDismissRequest = { isDialogOpen.value = false }) {
        Box(
            modifier = Modifier
                .height(425.dp)
                .fillMaxWidth()
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(25.dp))
                .background(color = colorResource(id = R.color.background_card))
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.txt_h1_modal_peso),
                    style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
                )

                Spacer(modifier = Modifier.height(16.dp))


                TextField(
                    label = { Text(stringResource(id = R.string.ipt_meta_peso)) },
                    value = meta.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        if (it.isEmpty()) {
                            meta = 0
                            metaInvalida = false
                        } else {
                            meta = it.toInt()
                            metaInvalida = !(meta <= 200 && (meta > 0))
                        }
                    },
                    isError = metaInvalida,
                    supportingText = {
                        if (metaInvalida) {
                            Text(text = stringResource(id = R.string.ipt_meta_invalido))
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedLabelColor = Color.White,
                        cursorColor = Color(0xFFFF3B47),
                        focusedLabelColor = Color(0xFFFF3B47),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        containerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        errorTextColor = Color.White,
                        focusedIndicatorColor = Color(0xFFFF3B47)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    label = { Text(stringResource(id = R.string.ipt_peso)) },
                    value = peso.toString(),
                    onValueChange = {
                        if (it.isEmpty()) {
                            peso = 0
                            pesoInvalida = false
                        } else {
                            peso = it.toInt()
                            pesoInvalida = !(peso <= 200 && (peso > 0))
                        }
                    },
                    isError = pesoInvalida,
                    supportingText = {
                        if (pesoInvalida) {
                            Text(text = stringResource(id = R.string.ipt_meta_invalido))
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedLabelColor = Color.White,
                        cursorColor = Color(0xFFFF3B47),
                        focusedLabelColor = Color(0xFFFF3B47),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        containerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        errorTextColor = Color.White,
                        focusedIndicatorColor = Color(0xFFFF3B47)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.fire)),
                    enabled = !(metaInvalida && pesoInvalida),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(8.dp, shape = RoundedCornerShape(4.dp))
                ) {
                    Text(stringResource(id = R.string.txt_button_peso))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .clickable { isDialogOpen.value = false }
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.txt_cancel),
                    style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
                )
            }
        }
    }
}


@Composable
fun DayItem(
    dayName: Int,
    dayNumber: String,
    iconRes: String,
    iconTint: Color,
    isBold: Boolean = false
) {
    Row {
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = dayName),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = if (isBold) colorResource(id = R.color.day) else Color.White,
                    fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
                )
            )
            Text(
                text = dayNumber,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
            Icon(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(iconRes)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                ),
                contentDescription = stringResource(id = dayName),
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.CenterHorizontally),
                tint = iconTint
            )
        }
    }
}