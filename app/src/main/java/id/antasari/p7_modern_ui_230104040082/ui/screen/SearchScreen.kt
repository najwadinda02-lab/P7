package id.antasari.p7_modern_ui_230104040082.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.antasari.p7_modern_ui_230104040082.R

// Konstanta Warna Kustom
private val PrimaryMagenta = Color(0xFFC300B4)
private val DarkTeal = Color(0xFF00796B)
private val DarkBrown = Color(0xFF5D4037)
// Hapus LightBackground karena kita akan menggunakan MaterialTheme.colorScheme

// PERBAIKAN 1: Jadikan color dan imageRes sebagai parameter opsional
data class Genre(
    val name: String,
    val color: Color = PrimaryMagenta,
    val imageRes: Int? = null
)

// PERUBAHAN 1: Menambahkan isDark
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(nav: NavController, padding: PaddingValues, isDark: Boolean) {

    var searchQuery by remember { mutableStateOf("") }

    // MENGGUNAKAN WARNA TEMA MATERIAL
    val containerColor = MaterialTheme.colorScheme.background
    val onContainerColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = Modifier
            .background(containerColor)
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
    ) {

        // --- 1. HEADER ---
        SpotifySearchHeader(onContainerColor, containerColor)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // --- 2. SEARCH BAR ---
            // PERUBAHAN 2: Kirim status tema ke SearchBar
            SpotifySearchBar(searchQuery, { searchQuery = it }, isDark)

            Spacer(Modifier.height(8.dp))

            // Pastikan semua Text menggunakan warna tema
            Text(
                "Mulai jelajahi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = onContainerColor
            )

            // --- 3. KATEGORI UTAMA (FOTO DIHILANGKAN PADA 3 ITEM PERTAMA) ---
            GenreGrid(
                items = listOf(
                    Genre("Musik", PrimaryMagenta),
                    Genre("Podcast", DarkTeal),
                    Genre("Acara Langsung", DarkBrown),

                    // Item ini tetap memiliki foto
                    Genre("K-Pop ON! (찐) Hub", Color(0xFF00ACC1))
                ),
                cardHeight = 45.dp
            )

            Text(
                "Temukan sesuatu yang lain",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = onContainerColor,
                modifier = Modifier.padding(top = 10.dp)
            )

            // --- 4. REKOMENDASI HORIZONTAL (Layout Gambar Tumpang Tindih) ---
            ScrollRow(modifier = Modifier.padding(bottom = 12.dp)) {
                RecomendationCard("Video", Color(0xFF4CAF50), R.drawable.aboutyou)
                RecomendationCard("Music", Color(0xFF4CAF50), R.drawable.lany)
                RecomendationCard("Podcast", Color(0xFF4CAF50), R.drawable.aboutyou)
            }

            Text(
                "Jelajahi semua",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = onContainerColor,
            )

            Spacer(Modifier.height(50.dp))
        }
    }
}

// PERBAIKAN 3: Implementasi GenreGrid
@Composable
fun GenreGrid(items: List<Genre>, cardHeight: Dp) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        for (i in items.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GenreCard(items[i], cardHeight, Modifier.weight(1f))

                if (i + 1 < items.size) {
                    GenreCard(items[i + 1], cardHeight, Modifier.weight(1f))
                } else {
                    Spacer(Modifier.weight(1f)) // Mengisi ruang jika ganjil
                }
            }
        }
    }
}

// PERBAIKAN 4: Perbaiki penutup kurung kurawal di GenreCard
@Composable
fun GenreCard(genre: Genre, height: Dp, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(8.dp))
            .background(genre.color)
            .clickable { /* Handle klik genre */ }
            .padding(12.dp)
    ) {
        // Teks genre di sudut kiri atas
        Text(
            genre.name,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.TopStart)
        )

        // Hanya tampilkan Image jika imageRes tidak null
        genre.imageRes?.let { resId ->
            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.BottomEnd)
                    .rotate(20f)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
    } // <-- Kurung kurawal penutup diperbaiki di sini
}

@Composable
fun SpotifySearchHeader(onContainerColor: Color, containerColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .background(containerColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.AccountCircle,
            contentDescription = "Profile",
            tint = onContainerColor,
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(50.dp))
        )
        Spacer(Modifier.width(16.dp))

        Text(
            "Cari",
            color = onContainerColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = { /* Action */ }) {
            Icon(Icons.Default.CameraAlt, contentDescription = "Scan", tint = onContainerColor)
        }
    }
}

// PERUBAHAN 5: Menggunakan isDark untuk warna dinamis
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotifySearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    isDark: Boolean // <-- Diubah dari textColor ke isDark
) {
    // Warna yang menyesuaikan tema
    val barContainerColor = if (isDark) Color(0xFF282828) else Color.White
    val barTextColor = if (isDark) Color.White else Color.Black

    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
        },
        placeholder = {
            Text("Apa yang ingin kamu dengarkan?", color = Color.Gray)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .clickable { /* Simulate focus/click behavior */ },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            // Menggunakan warna adaptif
            unfocusedContainerColor = barContainerColor,
            focusedContainerColor = barContainerColor,
            disabledContainerColor = barContainerColor,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

            // Menggunakan warna teks adaptif
            focusedTextColor = barTextColor,
            unfocusedTextColor = barTextColor,
            cursorColor = barTextColor,

            focusedLeadingIconColor = Color.Gray,
            unfocusedLeadingIconColor = Color.Gray,
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray
        ),
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun RecomendationCard(text: String, color: Color, imageRes: Int) {
    Column(
        modifier = Modifier
            .width(130.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .clickable { }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}