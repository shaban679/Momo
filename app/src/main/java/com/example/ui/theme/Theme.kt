package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = MomoPurple,
    onPrimary = Color.White,
    primaryContainer = MomoPurpleDark,
    onPrimaryContainer = Color.White,
    secondary = MomoOrange,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF7C2D12),
    onSecondaryContainer = Color.White,
    tertiary = MomoPink,
    background = MomoDarkBg,
    onBackground = MomoDarkText,
    surface = MomoDarkSurface,
    onSurface = MomoDarkText,
    surfaceVariant = MomoDarkCard,
    onSurfaceVariant = MomoDarkTextMuted,
    outline = MomoDarkBorder,
    error = MomoError,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = MomoPurple,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFEDE9FE),
    onPrimaryContainer = MomoPurpleDark,
    secondary = MomoOrange,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFEDD5),
    onSecondaryContainer = Color(0xFF9A3412),
    tertiary = MomoPink,
    background = MomoLightBg,
    onBackground = MomoLightText,
    surface = MomoLightSurface,
    onSurface = MomoLightText,
    surfaceVariant = MomoLightCard,
    onSurfaceVariant = MomoLightTextMuted,
    outline = MomoLightBorder,
    error = MomoError,
    onError = Color.White
)

@Composable
fun MomoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MomoTheme(darkTheme = darkTheme, content = content)
}
