package com.example.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.AuroraRepository
import com.example.ui.AuroraUiState
import com.example.ui.components.GoldMetallicButton
import com.example.ui.components.LiquidGlassCard
import com.example.ui.components.LiveTelemetryBadge
import com.example.ui.components.SectionHeader
import com.example.ui.components.SpecChip
import com.example.ui.theme.CrimsonPulse
import com.example.ui.theme.EmeraldLive
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
fun EcosystemScreen(
    state: AuroraUiState,
    onSelectPrimegramFeature: (String) -> Unit,
    onLaunchUrl: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val selectedFeature = state.selectedPrimegramFeature

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
                    eyebrow = "ECOSYSTEM & PORTFOLIO",
                    title = "Flagship Product & Studios",
                    subtitle = "Consumer software, design systems, and independent media forged under Aurora Empire."
                )
            }

            // Flagship Primegram X Spotlight Card
            item {
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    accentColor = GoldBright,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(195.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_primegram_showcase),
                                contentDescription = "Primegram X social media, Reels, music discovery, and WebRTC live room interface",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                ObsidianBase.copy(alpha = 0.88f)
                                            )
                                        )
                                    )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                LiveTelemetryBadge(label = "FLAGSHIP • LIVE", isCrimson = true)
                                SpecChip(
                                    text = "primegramx.co.ke",
                                    accentColor = GoldChampagne,
                                    selected = true
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Primegram X",
                                    style = MaterialTheme.typography.displaySmall,
                                    color = InkPrimary
                                )
                                Text(
                                    text = "Gen Z Social • Reels • Music Discovery • WebRTC Rooms",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldBright
                                )
                            }
                        }

                        Column(modifier = Modifier.padding(18.dp)) {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                state.primegramFeatures.forEach { feature ->
                                    SpecChip(
                                        text = feature.title,
                                        selected = feature.id == selectedFeature.id,
                                        accentColor = if (feature.id == "pg_webrtc") CrimsonPulse else GoldBright,
                                        onClick = { onSelectPrimegramFeature(feature.id) },
                                        modifier = Modifier.testTag("primegram_tab_${feature.id}")
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(ObsidianBase.copy(alpha = 0.72f))
                                    .border(
                                        1.dp,
                                        Color.White.copy(alpha = 0.12f),
                                        RoundedCornerShape(18.dp)
                                    )
                                    .padding(16.dp)
                            ) {
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = selectedFeature.badge,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = GoldBright
                                        )
                                        Text(
                                            text = selectedFeature.latencyLabel,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = CrimsonPulse
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = selectedFeature.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = InkPrimary
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = selectedFeature.description,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = InkSecondary
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    selectedFeature.architectureSpecs.forEach { spec ->
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(vertical = 3.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.CheckCircle,
                                                contentDescription = null,
                                                tint = GoldBright,
                                                modifier = Modifier.size(15.dp)
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

                            Spacer(modifier = Modifier.height(16.dp))

                            GoldMetallicButton(
                                text = "Launch primegramx.co.ke",
                                icon = Icons.Filled.OpenInNew,
                                onClick = { onLaunchUrl(AuroraRepository.FLAGSHIP_URL) },
                                testTag = "launch_primegram_button",
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            // Aurora Sonic & FinTech Rails Row
            item {
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    accentColor = GoldAmber
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.MusicNote,
                                    contentDescription = null,
                                    tint = GoldBright,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Aurora Sonic & Media",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = InkPrimary
                                )
                            }
                            SpecChip(text = "MR. AURA IMPRINT", accentColor = GoldAmber, selected = true)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Independent music production and audio-visual identity woven straight into Primegram X discovery streams.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = InkSecondary
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        SonicWaveformStrip()
                    }
                }
            }

            // Local-First FinTech Monetization Pipeline
            item {
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    accentColor = EmeraldLive
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Payments,
                                contentDescription = null,
                                tint = GoldBright,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Local-First Creator Monetization",
                                style = MaterialTheme.typography.titleLarge,
                                color = InkPrimary
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Built from day one around East African mobile money velocity and global card settlement.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = InkSecondary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SpecChip(text = "M-Pesa STK Push", accentColor = EmeraldLive, selected = true)
                            SpecChip(text = "Paystack Cross-Border", accentColor = GoldBright, selected = true)
                            SpecChip(text = "Instant Creator Tips", accentColor = CrimsonPulse, selected = true)
                        }
                    }
                }
            }

            // Portfolio Entities Index
            items(state.portfolioEntities, key = { it.id }) { entity ->
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    accentColor = if (entity.externalUrl != null) GoldBright else CrimsonPulse
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = entity.roleTag,
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldBright
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = entity.name,
                                style = MaterialTheme.typography.titleLarge,
                                color = InkPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = entity.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = InkSecondary
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            imageVector = Icons.Filled.Bolt,
                            contentDescription = null,
                            tint = if (entity.externalUrl != null) GoldBright else CrimsonPulse
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SonicWaveformStrip() {
    val heights = listOf(0.28f, 0.62f, 0.85f, 0.45f, 0.92f, 0.70f, 0.35f, 0.80f, 0.96f, 0.52f, 0.74f, 0.40f, 0.88f, 0.60f, 0.32f, 0.68f)
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(ObsidianElevated)
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        val barCount = heights.size
        val slotWidth = size.width / barCount
        val barWidth = slotWidth * 0.56f
        heights.forEachIndexed { index, ratio ->
            val barHeight = size.height * ratio
            val top = (size.height - barHeight) / 2f
            val x = index * slotWidth + (slotWidth - barWidth) / 2f
            val color = if (index % 5 == 0) CrimsonPulse else GoldBright
            drawRoundRect(
                color = color,
                topLeft = Offset(x, top),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f)
            )
        }
    }
}
