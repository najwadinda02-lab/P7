package id.antasari.p7_modern_ui_230104040082.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.antasari.p7_modern_ui_230104040082.R
import androidx.compose.foundation.verticalScroll

private val SpotifyGreen = Color(0xFF1DB954)

@Composable
fun HomeScreen(nav: NavController, padding: PaddingValues) {

    // Background MaterialTheme.colorScheme.background
    val containerColor = MaterialTheme.colorScheme.background
    val onContainerColor = MaterialTheme.colorScheme.onBackground
    val cardSurface = MaterialTheme.colorScheme.surfaceContainerHighest

    Column(
        modifier = Modifier
            .background(containerColor) // WARNA PUTIH/TERANG
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        CustomSpotifyTopBar(onContainerColor)

        SpotifyChipsRow(cardSurface, onContainerColor)

        Spacer(Modifier.height(16.dp))

        SectionTitle("Sering kamu putar baru-baru ini", onContainerColor, Modifier.padding(horizontal = 16.dp))

        ScrollRow(modifier = Modifier.padding(horizontal = 16.dp)) {
            HorizontalContentCard("Jeje", "Waluh", R.drawable.aboutyou, cardSurface, onContainerColor)
            HorizontalContentCard("Hani", "OMG", R.drawable.lany, cardSurface, onContainerColor)
            HorizontalContentCard("Yawak", "Goyang Dumang", R.drawable.tulus, cardSurface, onContainerColor)
        }

        Spacer(Modifier.height(25.dp))

        SectionTitle("Music populer", onContainerColor, Modifier.padding(horizontal = 16.dp))

        ScrollRow(modifier = Modifier.padding(horizontal = 16.dp)) {
            RadioCard("Hani", R.drawable.lany, onContainerColor)
            RadioCard("Jeje", R.drawable.aboutyou, onContainerColor)
            RadioCard("Minji", R.drawable.sheila, onContainerColor)
        }

        Spacer(Modifier.height(25.dp))

        SectionTitle("Playlist Terbaru", onContainerColor, Modifier.padding(horizontal = 16.dp))

        ScrollRow(modifier = Modifier.padding(horizontal = 16.dp)) {
            // Catatan: Pastikan R.drawable.about dan R.drawable.kucing ada
            RadioCard("Dangdut", R.drawable.kucing, onContainerColor)
            RadioCard("Lagu Jawa", R.drawable.images2, onContainerColor)
            RadioCard("Suara Dinosaurus", R.drawable.images, onContainerColor)
            RadioCard("Rock & Roll", R.drawable.lany, onContainerColor)
        }

        // ================================================================

        Spacer(Modifier.height(100.dp))
    }
}

// -------------------------------------------------------------
// KOMPONEN YANG DIMODIFIKASI: RadioCard
// -------------------------------------------------------------

@Composable
fun RadioCard(name: String, imageRes: Int, onContainerColor: Color) {
    Column(
        modifier = Modifier
            .width(120.dp) // LEBAR DIKECILKAN
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp) // TINGGI DIKECILKAN
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.height(8.dp))

        Text(
            name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = onContainerColor
        )

        Text(
            "Radio",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

// -------------------------------------------------------------
// KOMPONEN LAINNYA (Tidak Berubah)
// -------------------------------------------------------------

@Composable
fun CustomSpotifyTopBar(onContainerColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Selamat Malam",
            color = onContainerColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = { /* Handle notifications */ }) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = onContainerColor)
        }
    }
}

@Composable
fun SpotifyChipsRow(cardSurface: Color, onContainerColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SpotifyChip("Semua", isSelected = true, cardSurface, onContainerColor)
        SpotifyChip("Musik", isSelected = false, cardSurface, onContainerColor)
        SpotifyChip("Podcast", isSelected = false, cardSurface, onContainerColor)
    }
}

@Composable
fun SpotifyChip(text: String, isSelected: Boolean, cardSurface: Color, onContainerColor: Color) {
    val chipColor = if (isSelected) SpotifyGreen else cardSurface
    val textColor = if (isSelected) Color.Black else onContainerColor

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = chipColor,
        modifier = Modifier.clip(RoundedCornerShape(20.dp))
    ) {
        Text(
            text,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun SectionTitle(text: String, onContainerColor: Color, modifier: Modifier = Modifier) {
    Text(
        text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = onContainerColor,
        modifier = modifier.padding(vertical = 12.dp)
    )
}

@Composable
fun ScrollRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = content
    )
}

@Composable
fun HorizontalContentCard(artist: String, title: String, imageRes: Int, cardSurface: Color, onContainerColor: Color) {
    Row(
        modifier = Modifier
            .width(280.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(cardSurface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f)
        ) {
            Text(title, color = onContainerColor, maxLines = 1, fontWeight = FontWeight.SemiBold)
            Text(artist, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }

        IconButton(onClick = { /* Opsi */ }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Options", tint = onContainerColor.copy(alpha = 0.7f))
        }
    }
}


@Composable
fun PlaylistCard(name: String) { /* Komponen lama */ }
@Composable
fun ForYouCard(name: String) { /* Komponen lama */ }