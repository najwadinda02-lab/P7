package id.antasari.p7_modern_ui_230104040082.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// --- Konstanta Warna Aksen Spotify (Tetap) ---
private val SpotifyGreen = Color(0xFF1DB954)

// Konstanta Latar Belakang dan Teks Utama
private val LightBackground = Color(0xFFFFFFFF)
private val DarkBackground = Color(0xFF121212)
private val LightText = Color(0xFF000000)
private val DarkText = Color(0xFFFFFFFF)
private val LightGrayText = Color(0xFF6A6A6A) // Abu-abu gelap untuk deskripsi (Mode Terang)
private val DarkGrayText = Color(0xFFB3B3B3) // Abu-abu terang untuk deskripsi (Mode Gelap)
private val LightDivider = Color(0xFFE0E0E0) // Garis pemisah terang
private val DarkDivider = Color(0xFF282828) // Garis pemisah gelap

@Composable
fun SettingsScreen(
    nav: NavController,
    isDark: Boolean,
    onThemeChange: (Boolean) -> Unit,
    padding: PaddingValues
) {
    // --- Logika Warna Tema Utama ---
    val backgroundColor = if (isDark) DarkBackground else LightBackground
    val primaryTextColor = if (isDark) DarkText else LightText
    val secondaryTextColor = if (isDark) DarkGrayText else LightGrayText
    val headerColor = if (isDark) DarkGrayText else LightGrayText
    val dividerColor = if (isDark) DarkDivider else LightDivider

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor) // Latar belakang adaptif
            .padding(bottom = padding.calculateBottomPadding())
            .verticalScroll(rememberScrollState())
    ) {

        // 1. HEADER (Title)
        Text(
            text = "Settings",
            color = primaryTextColor, // Teks adaptif
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
        )

        // --- 2. ACCOUNT SECTION ---
        SettingsSectionHeader("Akun", headerColor)
        ModernSpotifyItem(
            title = "Edit Profil",
            subtitle = "Kelola nama pengguna, foto, dan bio Anda.",
            icon = Icons.Default.Person,
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor
        )
        ModernSpotifyItem(
            title = "Ubah Kata Sandi",
            subtitle = null,
            icon = Icons.Default.Lock,
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor
        )

        DividerItem(dividerColor)

        // --- 3. QUALITY & STORAGE SECTION ---
        SettingsSectionHeader("Kualitas & Penyimpanan", headerColor)
        ModernSpotifyItem(
            title = "Kualitas Audio",
            subtitle = "Ekstrem",
            icon = Icons.Default.Headphones,
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor
        )
        ModernSpotifyItem(
            title = "Pengunduhan",
            subtitle = "Unduh lagu secara otomatis",
            icon = Icons.Default.CloudDownload,
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor
        )
        ModernSpotifyItem(
            title = "Hapus Cache",
            subtitle = "350 MB",
            icon = Icons.Default.CleaningServices,
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor
        )

        DividerItem(dividerColor)

        // --- 4. APPEARANCE & THEME SECTION ---
        SettingsSectionHeader("Tampilan", headerColor)

        // Item Switch Mode Gelap
        SwitchSettingsItem(
            title = "Mode Gelap",
            description = "Gunakan tema gelap Spotify",
            checked = isDark,
            onCheckedChange = onThemeChange,
            icon = Icons.Default.DarkMode,
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor
        )

        DividerItem(dividerColor)

        // --- 5. SUPPORT & ABOUT ---
        SettingsSectionHeader("Dukungan", headerColor)
        ModernSpotifyItem(
            title = "Bantuan & Dukungan",
            subtitle = null,
            icon = Icons.Default.Info,
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor
        )
        ModernSpotifyItem(
            title = "Tentang Aplikasi",
            subtitle = "Versi 1.0.0",
            icon = Icons.Default.Code,
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor
        )

        Spacer(Modifier.height(32.dp))
    }
}

// --- KOMPONEN BARU GAYA SPOTIFY ---

@Composable
fun SettingsSectionHeader(title: String, color: Color) {
    Text(
        text = title,
        color = color,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun DividerItem(color: Color) {
    Divider(
        color = color,
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    )
}

@Composable
fun ModernSpotifyItem(
    title: String,
    subtitle: String?,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    primaryTextColor: Color,
    secondaryTextColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = SpotifyGreen,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                color = primaryTextColor, // Teks adaptif
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            subtitle?.let {
                Text(
                    it,
                    color = secondaryTextColor, // Teks abu-abu adaptif
                    fontSize = 12.sp
                )
            }
        }

        Icon(
            Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = secondaryTextColor // Arrow abu-abu adaptif
        )
    }
}

@Composable
fun SwitchSettingsItem(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    primaryTextColor: Color,
    secondaryTextColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = SpotifyGreen,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = primaryTextColor, fontSize = 16.sp) // Teks adaptif
            Text(description, color = secondaryTextColor, fontSize = 12.sp) // Teks adaptif
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                // Warna switch harus tetap kontras di kedua mode
                checkedThumbColor = LightBackground,
                checkedTrackColor = SpotifyGreen,
                uncheckedThumbColor = secondaryTextColor, // Thumb abu-abu adaptif
                uncheckedTrackColor = if (checked) SpotifyGreen.copy(alpha = 0.5f) else Color(
                    0xFFFFFFFF
                ) // Track abu-abu gelap
            )
        )
    }
}