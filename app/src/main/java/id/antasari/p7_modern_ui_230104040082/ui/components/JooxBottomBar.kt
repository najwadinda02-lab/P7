package id.antasari.p7_modern_ui_230104040082.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun JooxBottomBar(nav: NavController) {

    val currentRoute = nav.currentBackStackEntryAsState().value?.destination?.route

    // NOTE: Warna latar belakang tetap Hijau Joox (0xFF1DB954)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        color = Color(0xFF1DB954),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            JooxNavItem("Home", "home", Icons.Default.Home, currentRoute, nav)
            JooxNavItem("Search", "search", Icons.Default.Search, currentRoute, nav)
            JooxNavItem("Profile", "profile", Icons.Default.Person, currentRoute, nav)
            JooxNavItem("Settings", "settings", Icons.Default.Settings, currentRoute, nav)
        }
    }
}

@Composable
fun JooxNavItem(
    label: String,
    route: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    currentRoute: String?,
    nav: NavController
) {
    val isSelected = currentRoute == route

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.25f else 1.0f, label = "ScaleAnimation"
    )

    // PERUBAHAN UTAMA DI SINI:
    // Jika terpilih (isSelected): Putih (White)
    // Jika tidak terpilih: Hitam (Black)
    val color by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color.Black, label = "ColorAnimation"
    )

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(32.dp)
                    .height(4.dp)
                    .background(Color.White, RoundedCornerShape(50))
            )
            Spacer(Modifier.height(4.dp))
        } else {
            Spacer(Modifier.height(10.dp))
        }

        IconButton(onClick = { nav.navigate(route) }) {
            Icon(
                icon,
                contentDescription = label,
                tint = color,
                modifier = Modifier.graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
            )
        }

        Text(label, color = color, style = MaterialTheme.typography.labelSmall)
    }
}