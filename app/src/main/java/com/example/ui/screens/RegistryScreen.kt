package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.model.SchemaSectionFilter
import com.example.ui.AuroraUiState
import com.example.ui.components.GoldMetallicButton
import com.example.ui.components.LiquidGlassCard
import com.example.ui.components.ObsidianGlassButton
import com.example.ui.components.SectionHeader
import com.example.ui.components.SpecChip
import com.example.ui.theme.CrimsonPulse
import com.example.ui.theme.EmeraldLive
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldChampagne
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkPrimary
import com.example.ui.theme.InkSecondary
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.ObsidianBase

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RegistryScreen(
    state: AuroraUiState,
    onSelectSchemaFilter: (SchemaSectionFilter) -> Unit,
    onCopyJsonLd: (String) -> Unit,
    onShareDossier: (String) -> Unit,
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
            item {
                SectionHeader(
                    eyebrow = "SEO & SCHEMA.ORG REGISTRY",
                    title = "Structured Corporate Graph",
                    subtitle = "Valid JSON-LD linking Aurora Empire to founder Javan Omeri and flagship SoftwareApplication Primegram X."
                )
            }

            // Linked Entity Graph Summary
            item {
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    accentColor = EmeraldLive
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.VerifiedUser,
                                    contentDescription = null,
                                    tint = EmeraldLive,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "SCHEMA.ORG/ORGANIZATION GRAPH",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = EmeraldLive
                                )
                            }
                            SpecChip(text = "VALID JSON-LD", accentColor = EmeraldLive, selected = true)
                        }

                        EntityLinkRow(
                            key = "Organization",
                            value = "Aurora Empire (Kisii, KE)"
                        )
                        EntityLinkRow(
                            key = "founder -> Person",
                            value = "Javan Omeri (MR. AURA • JAVA PRIME ULTRA)"
                        )
                        EntityLinkRow(
                            key = "owns -> SoftwareApplication",
                            value = "Primegram X (https://primegramx.co.ke)"
                        )
                        EntityLinkRow(
                            key = "contactPoint",
                            value = "support@primegramx.co.ke"
                        )
                    }
                }
            }

            // Filter Chips + JSON-LD Inspector
            item {
                LiquidGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    accentColor = GoldBright
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Code,
                                contentDescription = null,
                                tint = GoldBright,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "JSON-LD Payload Inspector",
                                style = MaterialTheme.typography.titleLarge,
                                color = InkPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SchemaSectionFilter.entries.forEach { filter ->
                                SpecChip(
                                    text = filter.label,
                                    selected = state.selectedSchemaFilter == filter,
                                    accentColor = if (filter == SchemaSectionFilter.SOFTWARE_APP) CrimsonPulse else GoldBright,
                                    onClick = { onSelectSchemaFilter(filter) },
                                    modifier = Modifier.testTag("schema_filter_${filter.name}")
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(ObsidianBase)
                                .border(
                                    1.dp,
                                    Color.White.copy(alpha = 0.14f),
                                    RoundedCornerShape(16.dp)
                                )
                                .horizontalScroll(rememberScrollState())
                                .padding(14.dp)
                        ) {
                            Text(
                                text = state.jsonLdPayload,
                                fontFamily = JetBrainsMonoFontFamily,
                                style = MaterialTheme.typography.bodyMedium,
                                color = GoldChampagne
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            GoldMetallicButton(
                                text = "Copy JSON-LD",
                                icon = Icons.Filled.ContentCopy,
                                onClick = { onCopyJsonLd(state.jsonLdPayload) },
                                testTag = "copy_json_ld_button",
                                modifier = Modifier.weight(1f)
                            )
                            ObsidianGlassButton(
                                text = "Share",
                                icon = Icons.Filled.Share,
                                onClick = { onShareDossier(state.jsonLdPayload) },
                                testTag = "share_json_ld_button",
                                modifier = Modifier.weight(0.75f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EntityLinkRow(
    key: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ObsidianBase.copy(alpha = 0.65f))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.labelSmall,
            color = InkMuted,
            modifier = Modifier.weight(0.45f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = InkSecondary,
            modifier = Modifier.weight(0.55f)
        )
    }
}
