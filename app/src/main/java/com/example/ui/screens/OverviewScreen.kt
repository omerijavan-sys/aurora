package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.CoreDivision
import com.example.model.DivisionCategory
import com.example.ui.AuroraUiState
import com.example.ui.components.GoldMetallicButton
import com.example.ui.components.LiquidGlassCard
import com.example.ui.components.LiveTelemetryBadge
import com.example.ui.components.ObsidianGlassButton
import com.example.ui.components.SectionHeader
import com.example.ui.components.SpecChip
import com.example.ui.theme.CrimsonPulse
import com.example.ui.theme.GoldAmber
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldChampagne
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkPrimary
import com.example.ui.theme.InkSecondary
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.ObsidianElevated

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OverviewScreen(
    state: AuroraUiState,
    onToggleDivision: (String) -> Unit,
    onExploreEcosystem: () -> Unit,
    onOpenFounderDossier: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
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
            // 1. Hero Executive Card
            item {
                HeroBrandCard(
                    onExploreEcosystem = onExploreEcosystem,
                    onOpenFounderDossier = onOpenFounderDossier
                )
            }

            // 2. Telemetry & Scale Metrics Grid
            item {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    maxItemsInEachRow = 2
                ) {
                    state.heroMetrics.forEach { metric ->
                        LiquidGlassCard(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(18.dp),
                            accentColor = if (metric.accentIsCrimson) CrimsonPulse else GoldBright,
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 14.dp)
                        ) {
                            Column {
                                Text(
                                    text = metric.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = InkMuted
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = metric.value,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = if (metric.accentIsCrimson) CrimsonPulse else GoldBright
                                )
                            }
                        }
                    }
                }
            }

            // 3. Core Divisions Header
            item {
                Spacer(modifier = Modifier.height(4.dp))
                SectionHeader(
                    eyebrow = "HOLDING ARCHITECTURE • 04 DIVISIONS",
                    title = "Core Divisions & Capabilities",
                    subtitle = "Engineered for speed, refractive beauty, and East African creator sovereignty."
                )
            }

            // 4. Expandable Core Divisions
            items(state.divisions, key = { it.id }) { division ->
                DivisionGlassItem(
                    division = division,
                    expanded = division.id == state.expandedDivisionId,
                    onClick = { onToggleDivision(division.id) }
                )
            }
        }
    }
}

@Composable
private fun HeroBrandCard(
    onExploreEcosystem: () -> Unit,
    onOpenFounderDossier: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.28f),
                        GoldBright.copy(alpha = 0.45f),
                        CrimsonPulse.copy(alpha = 0.25f)
                    )
                ),
                shape = RoundedCornerShape(28.dp)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_hero_aurora),
            contentDescription = "Aurora Empire architectural liquid glass monolith artwork",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )
        // High-contrast obsidian vignette overlay for crisp editorial legibility
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ObsidianBase.copy(alpha = 0.45f),
                            ObsidianBase.copy(alpha = 0.78f),
                            ObsidianBase.copy(alpha = 0.96f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(ObsidianBase.copy(alpha = 0.78f))
                        .border(1.dp, GoldBright.copy(alpha = 0.45f), RoundedCornerShape(50))
                        .padding(start = 6.dp, end = 12.dp, top = 5.dp, bottom = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.aurora_orb_logo),
                        contentDescription = "Aurora Orb logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AURORA EMPIRE • KISII, KE",
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldChampagne
                    )
                }

                LiveTelemetryBadge(label = "PRIMEGRAM X", isCrimson = true)
            }

            Spacer(modifier = Modifier.height(34.dp))

            Text(
                text = "Sovereign Consumer Software from Kenya for the World.",
                style = MaterialTheme.typography.displaySmall,
                color = InkPrimary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Aurora Empire is a multi-disciplinary technology holding studio founded by Javan Omeri (MR. AURA)—uniting real-time WebRTC platforms, refractive Liquid Glass design systems, digital music production, and local-first creator monetization.",
                style = MaterialTheme.typography.bodyLarge,
                color = InkSecondary
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GoldMetallicButton(
                    text = "Primegram X",
                    icon = Icons.AutoMirrored.Filled.ArrowForward,
                    onClick = onExploreEcosystem,
                    testTag = "hero_explore_primegram",
                    modifier = Modifier.weight(1f)
                )
                ObsidianGlassButton(
                    text = "Founder",
                    icon = Icons.Outlined.PersonOutline,
                    onClick = onOpenFounderDossier,
                    testTag = "hero_founder_dossier",
                    modifier = Modifier.weight(0.85f)
                )
            }
        }
    }
}

@Composable
private fun DivisionGlassItem(
    division: CoreDivision,
    expanded: Boolean,
    onClick: () -> Unit
) {
    val icon: ImageVector = when (division.category) {
        DivisionCategory.SOCIAL_REALTIME -> Icons.Filled.Hub
        DivisionCategory.LIQUID_GLASS_UX -> Icons.Filled.AutoAwesome
        DivisionCategory.MUSIC_MEDIA -> Icons.Filled.GraphicEq
        DivisionCategory.FINTECH_MONETIZATION -> Icons.Filled.AccountBalanceWallet
    }
    val accent = when (division.category) {
        DivisionCategory.SOCIAL_REALTIME -> CrimsonPulse
        DivisionCategory.LIQUID_GLASS_UX -> GoldBright
        DivisionCategory.MUSIC_MEDIA -> GoldAmber
        DivisionCategory.FINTECH_MONETIZATION -> GoldChampagne
    }

    LiquidGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("division_card_${division.id}"),
        accentColor = accent,
        onClick = onClick
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(ObsidianElevated)
                        .border(1.dp, accent.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = division.title,
                        tint = accent,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${division.code} • ${division.highlightMetric}",
                        style = MaterialTheme.typography.labelSmall,
                        color = accent
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = division.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = InkPrimary
                    )
                    Text(
                        text = division.subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = InkMuted
                    )
                }

                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) "Collapse division details" else "Expand division details",
                    tint = InkSecondary
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 14.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White.copy(alpha = 0.10f))
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = division.summary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = InkSecondary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    division.capabilities.forEach { capability ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Verified,
                                contentDescription = null,
                                tint = accent,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = capability,
                                style = MaterialTheme.typography.bodyMedium,
                                color = InkPrimary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    SpecChip(
                        text = division.highlightMetric,
                        accentColor = accent,
                        selected = true
                    )
                }
            }
        }
    }
}
