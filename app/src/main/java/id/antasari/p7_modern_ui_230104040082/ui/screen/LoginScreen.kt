package id.antasari.p7_modern_ui_230104040082.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.antasari.p7_modern_ui_230104040082.R

// -------------------------------------------------------------
// KONSTANTA WARNA (TEMA BIRU MODERN)
// -------------------------------------------------------------
private val AppPrimary = Color(0xFF1E88E5)       // Biru utama
private val AppPrimaryDark = Color(0xFF1565C0)  // Biru gelap (gradien)
private val AppPrimaryLight = Color(0xFF90CAF9) // Biru terang
private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {}
) {
    AppLoginContent(onLoginSuccess)
}

// ---------------------------------------------------------------------
// KONTEN LOGIN UTAMA
// ---------------------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLoginContent(onLoginSuccess: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(AppPrimary, AppPrimaryDark)
                )
            )
            .systemBarsPadding()
    ) {

        // ---------------- HEADER ----------------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.TopCenter)
        ) {

            Icon(
                Icons.Default.AccountCircle,
                contentDescription = "App Logo",
                modifier = Modifier
                    .padding(24.dp)
                    .size(48.dp)
                    .align(Alignment.TopStart),
                tint = White
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            ) {
                Text(
                    text = "Selamat Datang",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Text(
                    text = "Masuk untuk melanjutkan",
                    fontSize = 18.sp,
                    color = White.copy(alpha = 0.85f)
                )
            }
        }

        // ---------------- CARD LOGIN ----------------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(White)
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 32.dp)
            )

            // Email
            ModernTextFieldBlue(
                value = email,
                onValueChange = { email = it },
                label = "Alamat Email",
                leadingIcon = Icons.Default.Email,
                isPassword = false
            )

            Spacer(Modifier.height(16.dp))

            // Password
            ModernTextFieldBlue(
                value = password,
                onValueChange = { password = it },
                label = "Kata Sandi",
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                showPassword = showPassword,
                onTogglePassword = { showPassword = !showPassword }
            )

            AlignEndTextButton(
                text = "Lupa kata sandi?",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textColor = AppPrimary
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                onClick = {
                    if (email == "najwa12345@gmail.com" && password == "12345") {
                        onLoginSuccess()
                    } else {
                        errorMessage = "Email atau kata sandi salah!"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppPrimary
                )
            ) {
                Text(
                    text = "MASUK",
                    color = White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(16.dp))

            Row {
                Text("Belum punya akun?", color = Color.Gray)
                TextButton(onClick = {}) {
                    Text("Daftar", color = AppPrimary)
                }
            }
        }
    }
}

// ---------------------------------------------------------------------
// TEXT FIELD MODERN (TEMA BIRU)
// ---------------------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernTextFieldBlue(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    isPassword: Boolean,
    showPassword: Boolean = false,
    onTogglePassword: (() -> Unit)? = null
) {
    val TextDark = Color(0xFF1F1F1F)
    val Gray = Color(0xFF888888)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Gray) },
        leadingIcon = { Icon(leadingIcon, null, tint = Gray) },
        visualTransformation =
            if (isPassword && !showPassword)
                PasswordVisualTransformation()
            else VisualTransformation.None,
        trailingIcon =
            if (isPassword && onTogglePassword != null) {
                {
                    val icon =
                        if (showPassword)
                            Icons.Default.VisibilityOff
                        else Icons.Default.Visibility
                    IconButton(onClick = onTogglePassword) {
                        Icon(icon, null, tint = Gray)
                    }
                }
            } else null,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppPrimary,
            focusedLabelColor = AppPrimary,
            focusedLeadingIconColor = AppPrimary,
            focusedTrailingIconColor = AppPrimary,
            cursorColor = AppPrimary,
            unfocusedBorderColor = Gray.copy(alpha = 0.5f),
            unfocusedLabelColor = Gray,
            unfocusedLeadingIconColor = Gray,
            unfocusedTrailingIconColor = Gray,
            focusedTextColor = TextDark,
            unfocusedTextColor = TextDark,
            focusedContainerColor = White,
            unfocusedContainerColor = White
        )
    )
}

// ---------------------------------------------------------------------
// TEXT BUTTON ALIGN END
// ---------------------------------------------------------------------
@Composable
fun AlignEndTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color
) {
    Box(modifier = modifier) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text, color = textColor)
        }
    }
}