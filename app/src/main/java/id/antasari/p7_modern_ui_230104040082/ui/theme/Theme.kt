package id.antasari.p7_modern_ui_230104040082.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.runtime.SideEffect // 🔥 Import ini diperlukan
import androidx.compose.ui.graphics.toArgb // 🔥 Import ini diperlukan
import androidx.compose.material3.Surface
private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = Secondary,
    onSecondary = OnSecondary,
    background = Background,
    onBackground = OnBackground,
    onSurface = OnSurface,
)

private val DarkColors = darkColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    // 🔥🔥🔥 KODE BARU UNTUK MENGATUR SYSTEM BARS 🔥🔥🔥
    val view = LocalView.current
    if (!view.isInEditMode) {
        // Ini memastikan status bar dan navigation bar transparan
        // sehingga konten Compose dapat menggambar di baliknya.
        SideEffect {
            val window = (view.context as Activity).window

            // Atur status bar dan navigation bar menjadi transparan
            // Catatan: Warna di sini tidak akan terlihat karena kita telah
            // mengatur WindowCompat.setDecorFitsSystemWindows(window, false) di MainActivity,
            // tetapi ini penting untuk memastikan tidak ada warna default yang menindih.
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()

            // Mengontrol apakah ikon status bar berwarna gelap (untuk tema terang)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme.not()
        }
    }
    // 🔥🔥🔥 AKHIR KODE BARU 🔥🔥🔥

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}