package id.antasari.p7_modern_ui_230104040082.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.antasari.p7_modern_ui_230104040082.R
import androidx.compose.material.icons.filled.MoreVert

// Konstanta Warna Spotify
private val AppGreen = Color(0xFF1DB954)
private val LightGrayText = Color(0xFFAFAFAF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(nav: NavController, padding: PaddingValues) {

    // Warna Latar Belakang menggunakan tema (dark/light)
    val containerColor = MaterialTheme.colorScheme.background
    val onContainerColor = MaterialTheme.colorScheme.onBackground

    // Padding yang sudah disediakan oleh Scaffold
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Profile", fontWeight = FontWeight.Bold, color = onContainerColor)
                },
                // PERUBAHAN 1: Membuat Top Bar Transparan/Menyatu dengan Latar Belakang
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = containerColor // Menyatu saat discroll
                ),
                actions = {
                    IconButton(onClick = { nav.navigate("settings") }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = onContainerColor
                        )
                    }
                }
            )
        },
        // PERUBAHAN 2: Menerapkan warna latar belakang di Scaffold
        containerColor = containerColor
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ------------------------------------------------------------
            // 1. PROFILE PICTURE (Spotify circle glow style)
            // ------------------------------------------------------------
            Box(
                modifier = Modifier
                    .size(130.dp)
                    // Warna Gradient/Glow menggunakan Hijau Spotify
                    .background(
                        Brush.radialGradient(
                            colors = listOf(AppGreen.copy(alpha = 0.5f), Color.Transparent),
                            radius = 200f
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(Modifier.height(16.dp))

            // NAME
            Text(
                "Najwa Dinda",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold, // Lebih Bold
                color = onContainerColor
            )

            Text(
                "@Najwa",
                color = LightGrayText, // Warna abu-abu yang lebih lembut
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(16.dp))

            // EDIT BUTTON (DIPERBAIKI)
            OutlinedButton(
                onClick = {},
                shape = RoundedCornerShape(30.dp),
                // PARAMETER border DIHILANGKAN karena menyebabkan error
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = onContainerColor // Warna teks dan ikon
                    // Warna garis tepi akan mengikuti MaterialTheme.colorScheme.outline secara default
                )
            ) {
                Icon(Icons.Default.Edit, contentDescription = null)
                Spacer(Modifier.width(6.dp))
                Text("Edit Profile")
            }

            Spacer(Modifier.height(24.dp))

            // PERUBAHAN 4: Divider yang lebih halus
            Divider(color = onContainerColor.copy(alpha = 0.15f))

            Spacer(Modifier.height(24.dp))

            // ------------------------------------------------------------
            // 2. FOLLOWERS — FOLLOWING (Spotify layout)
            // ------------------------------------------------------------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat("Followers", "152", onContainerColor)
                ProfileStat("Following", "98", onContainerColor)
                ProfileStat("Playlists", "24", onContainerColor)
            }

            Spacer(Modifier.height(28.dp))

            Divider(color = onContainerColor.copy(alpha = 0.15f))

            Spacer(Modifier.height(24.dp))

            // ------------------------------------------------------------
            // 3. Top Artists (like Spotify)
            // ------------------------------------------------------------
            Text(
                "Your Top Artists",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = onContainerColor,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // PERUBAHAN 5: Tambahkan indeks untuk Top Artists
                ArtistRow("Hanni", R.drawable.lany, 1, onContainerColor)
                ArtistRow("Minji", R.drawable.tulus, 2, onContainerColor)
                ArtistRow("Jeje", R.drawable.aboutyou, 3, onContainerColor)
            }

            Spacer(Modifier.height(padding.calculateBottomPadding() + 40.dp))
        }
    }
}

// PERUBAHAN: Menerima onContainerColor
@Composable
fun ProfileStat(label: String, value: String, onContainerColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = onContainerColor // Menggunakan warna tema
        )
        Text(label, color = LightGrayText) // Warna abu-abu yang lebih lembut
    }
}

// PERUBAHAN: Menerima onContainerColor dan rank
@Composable
fun ArtistRow(name: String, imageRes: Int, rank: Int, onContainerColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Nomor Urut (Rank)
        Text(
            "$rank.",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = LightGrayText,
            modifier = Modifier.width(30.dp) // Memberi ruang agar sejajar
        )

        // Gambar Artis
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Artist: $name",
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(Modifier.width(14.dp))

        // Nama Artis
        Text(
            name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = onContainerColor
        )

        Spacer(Modifier.weight(1f))

        // Ikon Aksen (misalnya titik-titik untuk opsi)
        Icon(
            Icons.Default.MoreVert,
            contentDescription = "Options",
            tint = LightGrayText
        )
    }
}