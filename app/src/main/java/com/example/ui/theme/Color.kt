package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Momo Brand Colors
val MomoPurple = Color(0xFF7C3AED)
val MomoPurpleDark = Color(0xFF5B21B6)
val MomoPurpleLight = Color(0xFFA78BFA)
val MomoOrange = Color(0xFFF97316)
val MomoPink = Color(0xFFEC4899)
val MomoYellow = Color(0xFFFBBF24)

// Neutral Colors - Light Mode
val MomoLightBg = Color(0xFFFFFFFF)
val MomoLightSurface = Color(0xFFF5F5F7)
val MomoLightCard = Color(0xFFFFFFFF)
val MomoLightText = Color(0xFF0F172A)
val MomoLightTextMuted = Color(0xFF64748B)
val MomoLightBorder = Color(0xFFE2E8F0)

// Neutral Colors - Dark Mode
val MomoDarkBg = Color(0xFF0B0B0F)
val MomoDarkSurface = Color(0xFF16161D)
val MomoDarkCard = Color(0xFF1E1E28)
val MomoDarkText = Color(0xFFF8FAFC)
val MomoDarkTextMuted = Color(0xFF94A3B8)
val MomoDarkBorder = Color(0xFF2A2A38)

// Semantic
val MomoSuccess = Color(0xFF10B981)
val MomoWarning = Color(0xFFF59E0B)
val MomoError = Color(0xFFEF4444)

// Signature Momo Gradient
val MomoStoryGradient = Brush.linearGradient(
    colors = listOf(MomoOrange, MomoPink, MomoPurple)
)

val MomoButtonGradient = Brush.horizontalGradient(
    colors = listOf(MomoPurple, Color(0xFF9333EA), MomoOrange)
)
