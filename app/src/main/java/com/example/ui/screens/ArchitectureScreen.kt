package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.AuroraUiState
import com.example.ui.GlassAccentMode
import com.example.ui.components.LiquidGlassCard
import com.example.ui.components.SectionHeader
import com.example.ui.components.SpecChip
import com.example.ui.components.VisionSection
import com.example.ui.theme.CrimsonPulse
import com.example.ui.theme.GoldAmber
import com.example.ui.theme.GoldBright
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkPrimary
import com.example.ui.theme.InkSecondary
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.ObsidianElevated
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ArchitectureScreen(
    state: AuroraUiState,
    onUpdateGlassOpacity: (Float) -> Unit,
    onSetAccentMode: (GlassAccentMode) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val activeAccent = when (state.glassAccentMode) {
        GlassAccentMode.SOVEREIGN_GOLD -> GoldBright
        GlassAccentMode.CRIMSON_PULSE -> CrimsonPulse
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 760.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = contentPadding.calculateTopPadding() + 12.dp,
                bottom = contentPadding.calculateBottomPadding() + 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SectionHeader(
                    eyebrow = "VISION & ARCHITECTURE PILLARS",
                    title = "Craft, Velocity & Glass Physics",
                    subtitle = "Architectural principles guiding every Aurora Empire product and design token."
                )
            }

            item {
                VisionSection(
                    glassAlpha = state.glassOpacity,
                    accentColor = activeAccent
                )
            }

            // Interactive Liquid Glass System Playground
            item {
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    glassAlpha = state.glassOpacity,
                    accentColor = activeAccent
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Tune,
                                    contentDescription = null,
                                    tint = activeAccent,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Liquid Glass Token Lab",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = InkPrimary
                                )
                            }
                            Text(
                                text = "${(state.glassOpacity * 100).roundToInt()}% ALPHA",
                                style = MaterialTheme.typography.labelSmall,
                                color = activeAccent
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Calibrate translucent obsidian density (#06070B) and metallic accent polarity in real time.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = InkSecondary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Live layered glass specimen box
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            ObsidianBase,
                                            activeAccent.copy(alpha = 0.25f),
                                            CrimsonPulse.copy(alpha = 0.16f)
                                        )
                                    )
                                )
                                .padding(16.dp)
                        ) {
                            LiquidGlassCard(
                                modifier = Modifier.fillMaxWidth(),
                                glassAlpha = state.glassOpacity,
                                accentColor = activeAccent,
                                shape = RoundedCornerShape(16.dp),
                                contentPadding = PaddingValues(14.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "SURFACE SPEC • #06070B",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = activeAccent
                                        )
                                        Text(
                                            text = "Refractive Obsidian Layer",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = InkPrimary
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(activeAccent)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Slider(
                            value = state.glassOpacity,
                            onValueChange = onUpdateGlassOpacity,
                            valueRange = 0.45f..0.95f,
                            colors = SliderDefaults.colors(
                                thumbColor = activeAccent,
                                activeTrackColor = activeAccent,
                                inactiveTrackColor = ObsidianElevated
                            ),
                            modifier = Modifier.testTag("glass_opacity_slider")
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            GlassAccentMode.entries.forEach { mode ->
                                SpecChip(
                                    text = mode.label,
                                    selected = state.glassAccentMode == mode,
                                    accentColor = if (mode == GlassAccentMode.SOVEREIGN_GOLD) GoldBright else CrimsonPulse,
                                    onClick = { onSetAccentMode(mode) },
                                    modifier = Modifier.testTag("accent_mode_${mode.name}")
                                )
                            }
                        }
                    }
                }
            }

            // Three Architecture Pillars
            items(state.architecturePillars, key = { it.id }) { pillar ->
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    glassAlpha = state.glassOpacity,
                    accentColor = if (pillar.index == "02") CrimsonPulse else GoldBright
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(ObsidianElevated)
                                    .border(1.dp, GoldAmber.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                            ) {
                                Text(
                                    text = pillar.index,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldBright
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = pillar.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = InkPrimary
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = pillar.thesis,
                            style = MaterialTheme.typography.bodyMedium,
                            color = InkSecondary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        pillar.engineeringSpecs.forEach { spec ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 3.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Layers,
                                    contentDescription = null,
                                    tint = activeAccent,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = spec,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = InkPrimary
                                )
                            }
                        }
                    }
                }
            }

            // Color Token Reference Strip
            item {
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    accentColor = GoldBright
                ) {
                    Column {
                        Text(
                            text = "BRAND PALETTE REGISTRY",
                            style = MaterialTheme.typography.labelSmall,
                            color = InkMuted
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TokenSwatch("Obsidian", "#06070B", ObsidianBase, Modifier.weight(1f))
                            TokenSwatch("Gold", "#FBBF24", GoldBright, Modifier.weight(1f))
                            TokenSwatch("Amber", "#F59E0B", GoldAmber, Modifier.weight(1f))
                            TokenSwatch("Crimson", "#EF4444", CrimsonPulse, Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TokenSwatch(
    name: String,
    hex: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(ObsidianElevated)
            .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(14.dp))
            .padding(vertical = 10.dp, horizontal = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(color)
                .border(1.dp, Color.White.copy(alpha = 0.35f), CircleShape)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            color = InkPrimary
        )
        Text(
            text = hex,
            style = MaterialTheme.typography.labelSmall,
            color = InkMuted
        )
    }
}
