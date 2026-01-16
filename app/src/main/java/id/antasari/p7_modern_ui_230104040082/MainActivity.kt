package id.antasari.p7_modern_ui_230104040082

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.compose.material3.*
import id.antasari.p7_modern_ui_230104040082.ui.screen.*
import id.antasari.p7_modern_ui_230104040082.ui.theme.AppTheme
import id.antasari.p7_modern_ui_230104040082.ui.components.JooxBottomBar
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 AKTIFKAN MODE EDGE-TO-EDGE
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            // State yang mengontrol tema, default-nya Light Mode (false)
            var isDarkMode by remember { mutableStateOf(false) }

            // Menerapkan tema ke seluruh aplikasi
            AppTheme(darkTheme = isDarkMode) {

                val nav = rememberNavController()
                val currentRoute = nav.currentBackStackEntryAsState().value?.destination?.route

                Scaffold(
                    // Tambahkan modifier fillMaxSize di sini jika Anda ingin scaffold yang full screen
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        // Tampilkan BottomBar hanya jika bukan di layar 'login'
                        if (currentRoute != "login") {
                            JooxBottomBar(nav)
                        }
                    }
                ) { paddingValues: PaddingValues ->

                    NavHost(
                        navController = nav,
                        startDestination = "login",
                        modifier = Modifier.fillMaxSize()
                    ) {

                        /* LOGIN SCREEN */
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    nav.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            )
                        }

                        /* MAIN ROUTES — Menerima padding dari Scaffold */
                        composable("home") { HomeScreen(nav, padding = paddingValues) }

                        // PERBAIKAN: Sekarang kita meneruskan isDarkMode ke SearchScreen
                        composable("search") {
                            SearchScreen(
                                nav = nav,
                                padding = paddingValues,
                                isDark = isDarkMode // <-- PARAMETER isDark DITAMBAHKAN
                            )
                        }

                        composable("profile") { ProfileScreen(nav, padding = paddingValues) }

                        composable("settings") {
                            SettingsScreen(
                                nav = nav,
                                isDark = isDarkMode,
                                // onThemeChange akan mengubah state isDarkMode
                                onThemeChange = { isDarkMode = it },
                                padding = paddingValues
                            )
                        }
                    }
                }
            }
        }
    }
}