package spectrum.fittech

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityOptionsCompat
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetSuccess
import spectrum.fittech.backend.Object.IdUserManager
import spectrum.fittech.backend.auth.TokenManager
import spectrum.fittech.ui.theme.FittechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FittechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaInicial(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaInicial(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    TokenManager.getToken(LocalContext.current)?.let {
        if (it.isNotBlank()) {
            SweetSuccess(
                message = "Seja bem-vindo, ${IdUserManager.getUserName(LocalContext.current)}",
                duration = Toast.LENGTH_SHORT,
                padding = PaddingValues(top = 16.dp),
                contentAlignment = Alignment.TopCenter
            )

            Handler(Looper.getMainLooper()).postDelayed({
                val home = Intent(context, Home::class.java)
                context.startActivity(home)
            }, 2000)
        }
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount < 0) {  // Detecta swipe para a direita
                        change.consume() // Consome o gesto
                        // Adiciona animação de fade ao iniciar a nova Activity
                        val intent = Intent(context, TelaInicialDois::class.java)
                        val options = ActivityOptionsCompat.makeCustomAnimation(
                            context,
                            android.R.anim.fade_in, // Animação de entrada
                            android.R.anim.fade_out // Animação de saída
                        )
                        context.startActivity(intent, options.toBundle())
                    }
                }
            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalConfiguration.current) { screenHeightDp.dp * 0.6f })
                .clip(
                    GenericShape { size, _ ->
                        moveTo(0f, 0f)
                        lineTo(size.width, 0f)
                        lineTo(size.width, size.height * 0.8f)
                        lineTo(0f, size.height)
                        close()
                    }
                )
        ) {
            Image(
                painter = painterResource(id = R.mipmap.backgroundinit),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.4f),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.txt_init1),
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.White
                )
            )
            Text(
                text = stringResource(id = R.string.txt_init2),
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp)
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(32.dp)
                    .height(4.dp)
                    .background(Color(0xFFFF3B47), shape = RectangleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(4.dp)
                    .background(Color(0xFF3A3A3C), shape = RectangleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(4.dp)
                    .background(Color(0xFF3A3A3C), shape = RectangleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaInicialPreview() {
    FittechTheme {
        TelaInicial("Android")
    }
}