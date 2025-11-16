package com.example.vera100.Codelabs



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vera100.PopVote.AppHeader
import com.example.vera100.PopVote.theme.VERA100Theme
import com.example.vera100.R


val bebasNeueSwipe = FontFamily(Font(R.font.bebas_neue))



class SwipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VERA100Theme {
                SwipeScreenRoot()
            }
        }

    }

    @Composable
    fun SwipeScreenRoot() {
        // 0 = prima schermata, 1 = seconda schermata
        var screenIndex by remember { mutableStateOf(0) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        // dragAmount negativo = trascinamento verso sinistra
                        if (dragAmount < -120) {
                            screenIndex = 1
                        } else if (dragAmount > 120) {
                            screenIndex = 0
                        }
                    }
                }
        ) {
            when (screenIndex) {

                0 -> SwipeFirstScreen()
                1 -> SwipeSecondScreen()
            }
        }
    }

    @Composable
    fun SwipeFirstScreen() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppHeader()
                Text(
                    text = " haz swipe a la izquierda por abrir la segunda pantalla",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = bebasNeueSwipe
                )
            }
        }
    }

    @Composable
    fun SwipeSecondScreen() {
        var text by remember { mutableStateOf("") }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "pantalla 2",
                    color = Color.Red,
                    fontSize = 26.sp,
                    fontFamily = bebasNeueSwipe,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )            // Campo di testo al centro. Usa lo stesso font.
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("escribe algo...", fontFamily = bebasNeueSwipe) },
                    textStyle = TextStyle(
                        fontFamily = bebasNeueSwipe,
                        fontSize = 18.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(120.dp)
                )
            }
        }
    }
}







