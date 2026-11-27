package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkPrimary
import com.example.ui.theme.InkSecondary
import com.example.ui.theme.LiquidGlassTheme

private data class VisionPillarSpec(
    val id: String,
    val code: String,
    val title: String,
    val tagline: String,
    val description: String,
    val highlights: List<String>,
    val icon: ImageVector,
    val useCrimsonAccent: Boolean = false
)

private val auroraVisionPillars = listOf(
    VisionPillarSpec(
        id = "vision_refractive",
        code = "01",
        title = "Refractive Design",
        tagline = "Liquid Glass UI/UX Architecture",
        description = "Every Aurora Empire surface pairs deep #06070B obsidian contrast with translucent glass elevation, hairline specular gradients, and brushed metallic gold clarity.",
        highlights = listOf(
            "Obsidian OLED depth calibrated for high-contrast legibility",
            "Directional 1dp specular rim light over heavy drop shadows",
            "Brushed gold (#FBBF24) & crimson (#EF4444) signal hierarchy"
        ),
        icon = Icons.Filled.AutoAwesome
    ),
    VisionPillarSpec(
        id = "vision_realtime",
        code = "02",
        title = "Real-Time Connectivity",
        tagline = "Sub-50ms WebRTC & Edge Streaming",
        description = "Human connection cannot lag. Our real-time engine routes multi-party WebRTC audio/video rooms and short-video Reels with minimal packet overhead.",
        highlights = listOf(
            "Adaptive Opus voice & HD video rooms over mobile 4G/3G",
            "Edge-buffered Reels playback with instant creator attribution",
            "Resilient state synchronization with live crimson telemetry"
        ),
        icon = Icons.Filled.Sensors,
        useCrimsonAccent = true
    ),
    VisionPillarSpec(
        id = "vision_africa",
        code = "03",
        title = "African-Led Innovation",
        tagline = "Engineered in Kisii, Kenya for the World",
        description = "Founded by Javan Omeri (MR. AURA), Aurora Empire proves global-grade consumer software and creator economies can originate directly from East Africa.",
        highlights = listOf(
            "Youth-led software architecture & independent music culture",
            "Native M-Pesa & Paystack rails for instant creator payouts",
            "Built in Kisii, scaled across Nairobi, Great Lakes & Global Edge"
        ),
        icon = Icons.Filled.Public
    )
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VisionSection(
    modifier: Modifier = Modifier,
    glassAlpha: Float = LiquidGlassTheme.glass.defaultGlassAlpha,
    accentColor: Color = LiquidGlassTheme.glass.goldPrimary
) {
    val glass = LiquidGlassTheme.glass
    var expandedPillarId by remember { mutableStateOf(auroraVisionPillars.first().id) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("vision_section"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Executive Mission Card
        LiquidGlassCard(
            modifier = Modifier.fillMaxWidth(),
            glassAlpha = glassAlpha,
            accentColor = accentColor
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .width(18.dp)
                                .height(2.dp)
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(glass.goldPrimary, glass.crimsonAccent)
                                    ),
                                    shape = CircleShape
                                )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "AURORA EMPIRE • MISSION",
                            style = MaterialTheme.typography.labelSmall,
                            color = accentColor
                        )
                    }
                    LiveTelemetryBadge(label = "KISII -> GLOBAL", isCrimson = false)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Engineering Sovereign Digital Culture From East Africa to the World.",
                    style = MaterialTheme.typography.headlineMedium,
                    color = InkPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Aurora Empire exists to build uncompromising consumer platforms, tactile Liquid Glass interfaces, and local-first creator economies—proving that category-defining software is forged by clarity, velocity, and fearless African-led craft.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = InkSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SpecChip(text = "Refractive Glass", accentColor = glass.goldPrimary, selected = true)
                    SpecChip(text = "Real-Time WebRTC", accentColor = glass.crimsonAccent, selected = true)
                    SpecChip(text = "African-Led R&D", accentColor = glass.goldChampagne, selected = true)
                }
            }
        }

        // Core Vision Pillars
        auroraVisionPillars.forEach { pillar ->
            val pillarAccent = if (pillar.useCrimsonAccent) glass.crimsonAccent else accentColor
            val isExpanded = expandedPillarId == pillar.id

            LiquidGlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("vision_pillar_${pillar.id}"),
                glassAlpha = glassAlpha,
                accentColor = pillarAccent,
                contentPadding = PaddingValues(18.dp),
                onClick = {
                    expandedPillarId = if (isExpanded) "" else pillar.id
                }
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(13.dp))
                                .background(glass.obsidianElevated)
                                .border(
                                    1.dp,
                                    pillarAccent.copy(alpha = 0.45f),
                                    RoundedCornerShape(13.dp)
                                )
                        ) {
                            Icon(
                                imageVector = pillar.icon,
                                contentDescription = pillar.title,
                                tint = pillarAccent,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "PILLAR ${pillar.code} • ${pillar.tagline.uppercase()}",
                                style = MaterialTheme.typography.labelSmall,
                                color = pillarAccent
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = pillar.title,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                                color = InkPrimary
                            )
                        }

                        Icon(
                            imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = if (isExpanded) "Collapse pillar" else "Expand pillar",
                            tint = InkMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = pillar.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = InkSecondary
                    )

                    AnimatedVisibility(
                        visible = isExpanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Column(
                            modifier = Modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.White.copy(alpha = 0.10f))
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            pillar.highlights.forEach { item ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.Verified,
                                        contentDescription = null,
                                        tint = pillarAccent,
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = InkPrimary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
