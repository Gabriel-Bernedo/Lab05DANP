package com.example.lab05danp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val BrutalistColorScheme = lightColorScheme(
    primary = BrutalistPrimary,
    onPrimary = BrutalistBackground,
    secondary = BrutalistSecondary,
    onSecondary = BrutalistBackground,
    background = BrutalistBackground,
    onBackground = BrutalistText,
    surface = BrutalistBackground,
    onSurface = BrutalistText,
    surfaceVariant = BrutalistSurfaceVariant,
    onSurfaceVariant = BrutalistText,
    outline = BrutalistText
)

@Composable
fun Lab05DANPTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = BrutalistColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}