package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Semantic design tokens defining Aurora Empire's refractive Liquid Glass aesthetic:
 * - Obsidian Base (#06070B)
 * - Brushed Metallic Gold (#FBBF24 / #F59E0B)
 * - Subtle Crimson Highlights (#EF4444)
 */
@Immutable
data class LiquidGlassTokens(
    val obsidianBase: Color = ObsidianBase,
    val obsidianSurface: Color = ObsidianSurface,
    val obsidianElevated: Color = ObsidianElevated,
    val obsidianGlassDeep: Color = Color(0xFF0A0D14),
    val goldPrimary: Color = GoldBright,
    val goldAmber: Color = GoldAmber,
    val goldChampagne: Color = GoldChampagne,
    val crimsonAccent: Color = CrimsonPulse,
    val specularTopEdge: Color = ObsidianGlassBorderTop,
    val specularBottomEdge: Color = ObsidianGlassBorderBottom,
    val defaultGlassAlpha: Float = 0.80f,
    val borderWidth: Dp = 1.dp
) {
    fun surfaceBrush(alpha: Float = defaultGlassAlpha): Brush = Brush.linearGradient(
        colors = listOf(
            obsidianElevated.copy(alpha = alpha),
            obsidianGlassDeep.copy(alpha = (alpha + 0.12f).coerceAtMost(0.96f))
        )
    )

    fun specularBorderBrush(accentColor: Color = goldPrimary): Brush = Brush.linearGradient(
        colors = listOf(
            specularTopEdge,
            accentColor.copy(alpha = 0.36f),
            specularBottomEdge
        )
    )

    fun goldMetallicBrush(): Brush = Brush.horizontalGradient(
        colors = listOf(goldChampagne, goldPrimary, goldAmber)
    )
}

val LocalLiquidGlassTokens = staticCompositionLocalOf { LiquidGlassTokens() }

val AuroraDarkColorScheme = darkColorScheme(
    primary = GoldBright,
    onPrimary = InkOnGold,
    primaryContainer = GoldMuted,
    onPrimaryContainer = GoldChampagne,
    secondary = GoldAmber,
    onSecondary = InkOnGold,
    secondaryContainer = ObsidianElevated,
    onSecondaryContainer = InkPrimary,
    tertiary = CrimsonPulse,
    onTertiary = InkPrimary,
    tertiaryContainer = CrimsonMuted,
    onTertiaryContainer = InkPrimary,
    background = ObsidianBase,
    onBackground = InkPrimary,
    surface = ObsidianSurface,
    onSurface = InkPrimary,
    surfaceVariant = ObsidianElevated,
    onSurfaceVariant = InkSecondary,
    surfaceContainerLowest = ObsidianBase,
    surfaceContainerLow = ObsidianSurface,
    surfaceContainer = ObsidianElevated,
    surfaceContainerHigh = Color(0xFF192030),
    surfaceContainerHighest = Color(0xFF20283B),
    error = CrimsonPulse,
    onError = InkPrimary,
    errorContainer = CrimsonMuted,
    onErrorContainer = InkPrimary,
    outline = DividerHairline,
    outlineVariant = ObsidianGlassBorderBottom
)

val AuroraShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(28.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

object LiquidGlassTheme {
    val glass: LiquidGlassTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalLiquidGlassTokens.current
}

@Composable
fun AuroraEmpireTheme(
    glassTokens: LiquidGlassTokens = LiquidGlassTokens(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalLiquidGlassTokens provides glassTokens) {
        MaterialTheme(
            colorScheme = AuroraDarkColorScheme,
            typography = AuroraTypography,
            shapes = AuroraShapes,
            content = content
        )
    }
}
