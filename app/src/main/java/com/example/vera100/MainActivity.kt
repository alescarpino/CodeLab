package com.example.vera100

// import principali per Android e Compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vera100.ui.theme.VERA100Theme
import com.example.vera100.R

// Font personalizzato (lo stesso che hai già nel progetto)
val bebasNeue = FontFamily(Font(R.font.bebas_neue))

// Classe principale dell'app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Usiamo il tema definito nel progetto
            VERA100Theme {
                // Mostra il contenuto principale dell'app
                FilmApp()
            }
        }
    }
}

// data class per rappresentare un Film
data class Film(val titolo: String, val descrizione: String, val voto: Int)

// data class per rappresentare un Genere (con lista di film)
data class Genere(val nome: String, val film: MutableList<Film> = mutableListOf())

// Funzione principale dell'app
@Composable
fun FilmApp() {
    // Lista dei generi (ricordata dallo stato dell'app)
    val generi = remember { mutableStateListOf<Genere>() }

    // Tiene traccia se un genere è aperto
    var genereAperto by remember { mutableStateOf<Genere?>(null) }

    // Se nessun genere è aperto, mostra la lista dei generi
    if (genereAperto == null) {
        ListaGeneri(
            generi = generi,
            onApriGenere = { genereAperto = it },
            onAggiungiGenere = { nome ->
                if (nome.isNotBlank()) generi.add(Genere(nome))
            }
        )
    } else {
        // Se un genere è aperto, mostra la lista dei film di quel genere
        ListaFilm(
            genere = genereAperto!!,
            onBack = { genereAperto = null },
            onAggiungiFilm = { film -> genereAperto!!.film.add(film) }
        )
    }
}

// Intestazione dell'app (quella che avevi già)
@Composable
fun AppHeader() {
    Surface(color = Color.Black, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "POPVOTE",
                color = Color.Red,
                fontSize = 36.sp,
                fontFamily = bebasNeue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Schermata che mostra i generi
@Composable
fun ListaGeneri(
    generi: List<Genere>,
    onApriGenere: (Genere) -> Unit,
    onAggiungiGenere: (String) -> Unit
) {
    // Stato per gestire il dialog di creazione nuovo genere
    var mostraDialog by remember { mutableStateOf(false) }
    var nuovoGenere by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppHeader() },
        floatingActionButton = {
            FloatingActionButton(onClick = { mostraDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Aggiungi Genere")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Generi di film:", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))

            // Mostra i generi in una lista scorrevole
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(generi) { genere ->
                    Surface(
                        tonalElevation = 3.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onApriGenere(genere) }
                            .padding(4.dp)
                    ) {
                        Text(
                            text = genere.nome,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        // Dialog per creare un nuovo genere
        if (mostraDialog) {
            AlertDialog(
                onDismissRequest = { mostraDialog = false },
                title = { Text("Nuovo genere") },
                text = {
                    OutlinedTextField(
                        value = nuovoGenere,
                        onValueChange = { nuovoGenere = it },
                        label = { Text("Nome genere") }
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        onAggiungiGenere(nuovoGenere)
                        nuovoGenere = ""
                        mostraDialog = false
                    }) { Text("Crea") }
                },
                dismissButton = {
                    TextButton(onClick = { mostraDialog = false }) {
                        Text("Annulla")
                    }
                }
            )
        }
    }
}

// Schermata che mostra i film di un genere
@Composable
fun ListaFilm(genere: Genere, onBack: () -> Unit, onAggiungiFilm: (Film) -> Unit) {
    var mostraDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            // Barra superiore con tasto “indietro”
            Surface(color = Color.Black, modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Indietro", tint = Color.White)
                    }
                    Text(
                        text = genere.nome,
                        color = Color.Red,
                        fontSize = 28.sp,
                        fontFamily = bebasNeue
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { mostraDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Aggiungi Film")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            // Se non ci sono film, mostra un messaggio
            if (genere.film.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nessun film aggiunto", color = Color.Gray)
                }
            } else {
                // Mostra la lista dei film
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(genere.film) { film ->
                        Surface(
                            tonalElevation = 2.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text(film.titolo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text(film.descrizione)
                                Row {
                                    repeat(5) { i ->
                                        val filled = i < film.voto
                                        Icon(
                                            imageVector = if (filled) Icons.Filled.Star else Icons.Outlined.Star,
                                            contentDescription = null,
                                            tint = if (filled) Color.Yellow else Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Dialog per aggiungere un nuovo film
        if (mostraDialog) {
            var titolo by remember { mutableStateOf("") }
            var descrizione by remember { mutableStateOf("") }
            var voto by remember { mutableStateOf(3) }

            AlertDialog(
                onDismissRequest = { mostraDialog = false },
                title = { Text("Aggiungi un film") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = titolo,
                            onValueChange = { titolo = it },
                            label = { Text("Titolo") }
                        )
                        OutlinedTextField(
                            value = descrizione,
                            onValueChange = { descrizione = it },
                            label = { Text("Descrizione") }
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Valutazione: ")
                            repeat(5) { i ->
                                Icon(
                                    imageVector = if (i < voto) Icons.Filled.Star else Icons.Outlined.Star,
                                    contentDescription = null,
                                    tint = if (i < voto) Color.Yellow else Color.Gray,
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clickable { voto = i + 1 }
                                )
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (titolo.isNotBlank()) {
                            onAggiungiFilm(Film(titolo, descrizione, voto))
                            mostraDialog = false
                        }
                    }) { Text("Aggiungi") }
                },
                dismissButton = {
                    TextButton(onClick = { mostraDialog = false }) { Text("Annulla") }
                }
            )
        }
    }
}

// Anteprima per Android Studio
@Preview(showBackground = true)
@Composable
fun AppPreview() {
    VERA100Theme { FilmApp() }
}
