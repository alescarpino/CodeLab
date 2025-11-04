package com.example.vera100

import android.os.Bundle
import android.view.Surface
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vera100.ui.theme.VERA100Theme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.vera100.R
val bebasNeue = FontFamily(
    Font(R.font.bebas_neue)
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            VERA100Theme {
                AppHeader()
            }

        }
    }
}
@Composable
fun AppHeader() {
    Surface(
        color = Color.Black,          // sfondo nero come Netflix
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "POPVOTE",        // nome app
            color = Color.Red,       // colore rosso stile Netflix
            fontSize = 36.sp,        // dimensione testo
            fontFamily = bebasNeue,  // font custom
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
        )
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Cyan) {
        Text(
            text = "Hi, my name is $name!",
            modifier = modifier.padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VERA100Theme {
        Greeting("Android")
    }
}