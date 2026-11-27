package com.example.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.NetworkNode
import com.example.ui.AuroraUiState
import com.example.ui.components.GoldMetallicButton
import com.example.ui.components.HqMapSection
import com.example.ui.components.LiquidGlassCard
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
import com.example.ui.theme.ObsidianElevated

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LeadershipScreen(
    state: AuroraUiState,
    onSelectNode: (String) -> Unit,
    onOpenInGoogleMaps: (NetworkNode) -> Unit,
    onEmailFounder: (String) -> Unit,
    onCopyEmail: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val founder = state.founderProfile

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
                    eyebrow = "FOUNDER & LEADERSHIP",
                    title = "Architecting From Kisii, Kenya",
                    subtitle = "Executive leadership, creative direction, and regional headquarters infrastructure."
                )
            }

            // Founder Editorial Profile Card
            item {
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    accentColor = GoldBright
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(104.dp)
                                    .height(116.dp)
                                    .clip(RoundedCornerShape(22.dp))
                                    .border(
                                        width = 1.5.dp,
                                        brush = Brush.linearGradient(
                                            colors = listOf(GoldChampagne, GoldBright, CrimsonPulse)
                                        ),
                                        shape = RoundedCornerShape(22.dp)
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_founder_mr_aura),
                                    contentDescription = "Portrait of Javan Omeri (MR. AURA), Founder & Lead Architect",
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.TopCenter,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = founder.roleTitle,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldBright
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = founder.fullName,
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = InkPrimary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.LocationOn,
                                        contentDescription = null,
                                        tint = CrimsonPulse,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${founder.headquarters} (${founder.coordinatesLabel})",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = InkMuted
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            founder.monikers.forEachIndexed { idx, alias ->
                                SpecChip(
                                    text = alias,
                                    accentColor = if (idx == 0) GoldBright else if (idx == 1) GoldAmber else CrimsonPulse,
                                    selected = true
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = founder.bio,
                            style = MaterialTheme.typography.bodyLarge,
                            color = InkSecondary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(ObsidianElevated.copy(alpha = 0.72f))
                                .padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "OPERATING MANDATES",
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldBright
                            )
                            founder.leadershipPrinciples.forEach { principle ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.Verified,
                                        contentDescription = null,
                                        tint = GoldBright,
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = principle,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = InkPrimary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            GoldMetallicButton(
                                text = "Contact Studio",
                                icon = Icons.Filled.Email,
                                onClick = { onEmailFounder(founder.officialEmail) },
                                testTag = "contact_founder_email",
                                modifier = Modifier.weight(1f)
                            )
                            ObsidianGlassButton(
                                text = "Copy Email",
                                icon = Icons.Filled.ContentCopy,
                                onClick = { onCopyEmail(founder.officialEmail) },
                                testTag = "copy_founder_email",
                                modifier = Modifier.weight(0.9f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Official Executive Desk: ${founder.officialEmail}",
                            style = MaterialTheme.typography.labelSmall,
                            color = InkMuted
                        )
                    }
                }
            }

            // Interactive Google Maps Platform Kisii HQ Map
            item {
                HqMapSection(
                    nodes = state.networkNodes,
                    selectedNode = state.selectedNetworkNode,
                    onSelectNode = onSelectNode,
                    onOpenInGoogleMaps = onOpenInGoogleMaps
                )
            }
        }
    }
}
