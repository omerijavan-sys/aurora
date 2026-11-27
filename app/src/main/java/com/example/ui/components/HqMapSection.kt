package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.BuildConfig
import com.example.model.NetworkNode
import com.example.ui.theme.CrimsonPulse
import com.example.ui.theme.GoldAmber
import com.example.ui.theme.GoldBright
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkPrimary
import com.example.ui.theme.InkSecondary
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.ObsidianElevated
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

private const val OBSIDIAN_MAP_STYLE_JSON = """
[
  {"elementType":"geometry","stylers":[{"color":"#0B0E14"}]},
  {"elementType":"labels.text.fill","stylers":[{"color":"#94A3B8"}]},
  {"elementType":"labels.text.stroke","stylers":[{"color":"#06070B"}]},
  {"featureType":"administrative","elementType":"geometry.stroke","stylers":[{"color":"#334155"}]},
  {"featureType":"landscape","elementType":"geometry","stylers":[{"color":"#090B10"}]},
  {"featureType":"poi","stylers":[{"visibility":"off"}]},
  {"featureType":"road","elementType":"geometry","stylers":[{"color":"#1E293B"}]},
  {"featureType":"road.highway","elementType":"geometry","stylers":[{"color":"#475569"}]},
  {"featureType":"water","elementType":"geometry","stylers":[{"color":"#04060A"}]}
]
"""

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HqMapSection(
    nodes: List<NetworkNode>,
    selectedNode: NetworkNode,
    onSelectNode: (String) -> Unit,
    onOpenInGoogleMaps: (NetworkNode) -> Unit,
    modifier: Modifier = Modifier
) {
    val hasValidMapsKey = remember {
        BuildConfig.MAPS_API_KEY.isNotBlank() &&
            BuildConfig.MAPS_API_KEY != "MY_MAPS_API_KEY"
    }

    LiquidGlassCard(
        modifier = modifier.fillMaxWidth(),
        accentColor = CrimsonPulse
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Public,
                        contentDescription = null,
                        tint = GoldBright,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "HQ & Edge Topology",
                        style = MaterialTheme.typography.titleLarge,
                        color = InkPrimary
                    )
                }
                LiveTelemetryBadge(
                    label = if (hasValidMapsKey) "MAPS SDK LIVE" else "SPATIAL RADAR",
                    isCrimson = true
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                nodes.forEach { node ->
                    SpecChip(
                        text = node.cityCountry.substringBefore(","),
                        selected = node.id == selectedNode.id,
                        accentColor = if (node.isHeadquarters) GoldBright else CrimsonPulse,
                        onClick = { onSelectNode(node.id) },
                        modifier = Modifier.testTag("map_node_${node.id}")
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.14f),
                        shape = RoundedCornerShape(18.dp)
                    )
            ) {
                if (hasValidMapsKey) {
                    val targetLatLng = LatLng(selectedNode.latitude, selectedNode.longitude)
                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(targetLatLng, 11f)
                    }
                    LaunchedEffect(selectedNode.id) {
                        cameraPositionState.animate(
                            CameraUpdateFactory.newLatLngZoom(
                                targetLatLng,
                                if (selectedNode.isHeadquarters) 12.5f else 6.5f
                            ),
                            durationMs = 700
                        )
                    }
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(
                            mapStyleOptions = MapStyleOptions(OBSIDIAN_MAP_STYLE_JSON)
                        ),
                        uiSettings = MapUiSettings(
                            zoomControlsEnabled = false,
                            mapToolbarEnabled = false,
                            compassEnabled = true
                        )
                    ) {
                        nodes.forEach { node ->
                            Marker(
                                state = MarkerState(position = LatLng(node.latitude, node.longitude)),
                                title = node.name,
                                snippet = "${node.cityCountry} • ${node.latencyMs}",
                                icon = BitmapDescriptorFactory.defaultMarker(
                                    if (node.isHeadquarters) {
                                        BitmapDescriptorFactory.HUE_ORANGE
                                    } else {
                                        BitmapDescriptorFactory.HUE_RED
                                    }
                                )
                            )
                        }
                    }
                } else {
                    ObsidianSpatialRadarCanvas(
                        nodes = nodes,
                        selectedNode = selectedNode
                    )
                }

                // Floating Glass Coordinate Overlay
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(ObsidianBase.copy(alpha = 0.86f))
                        .border(1.dp, GoldBright.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 7.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = if (selectedNode.isHeadquarters) GoldBright else CrimsonPulse,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text(
                            text = "${selectedNode.name} • ${selectedNode.latencyMs}",
                            style = MaterialTheme.typography.labelSmall,
                            color = InkPrimary
                        )
                        Text(
                            text = "${selectedNode.latitude}°, ${selectedNode.longitude}°",
                            style = MaterialTheme.typography.labelSmall,
                            color = InkMuted
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = selectedNode.role,
                        style = MaterialTheme.typography.titleMedium,
                        color = InkPrimary
                    )
                    Text(
                        text = if (hasValidMapsKey) {
                            "Interactive Google Maps Platform active"
                        } else {
                            "Add MAPS_API_KEY in AI Studio Secrets for live satellite tiles"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = InkMuted
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                ObsidianGlassButton(
                    text = "Maps",
                    icon = Icons.Filled.OpenInNew,
                    onClick = { onOpenInGoogleMaps(selectedNode) },
                    testTag = "open_google_maps_button"
                )
            }
        }
    }
}

@Composable
private fun ObsidianSpatialRadarCanvas(
    nodes: List<NetworkNode>,
    selectedNode: NetworkNode
) {
    val infiniteTransition = rememberInfiniteTransition(label = "radar_sweep")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "radar_ring"
    )

    // Pre-computed normalized coordinates on the obsidian radar canvas
    val nodePositions = remember(nodes) {
        mapOf(
            "node_kisii" to Pair(0.46f, 0.54f),
            "node_nairobi" to Pair(0.64f, 0.60f),
            "node_kampala" to Pair(0.32f, 0.44f),
            "node_london" to Pair(0.24f, 0.18f)
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(ObsidianElevated, ObsidianBase)
                )
            )
    ) {
        val gridSteps = 8
        val stepX = size.width / gridSteps
        val stepY = size.height / gridSteps
        for (i in 1 until gridSteps) {
            drawLine(
                color = Color.White.copy(alpha = 0.05f),
                start = Offset(stepX * i, 0f),
                end = Offset(stepX * i, size.height),
                strokeWidth = 1f
            )
            drawLine(
                color = Color.White.copy(alpha = 0.05f),
                start = Offset(0f, stepY * i),
                end = Offset(size.width, stepY * i),
                strokeWidth = 1f
            )
        }

        val hqRatio = nodePositions["node_kisii"] ?: Pair(0.46f, 0.54f)
        val hqCenter = Offset(size.width * hqRatio.first, size.height * hqRatio.second)

        // Concentric telemetry rings centered on Kisii HQ
        listOf(0.18f, 0.34f, 0.52f).forEach { factor ->
            drawCircle(
                color = GoldBright.copy(alpha = 0.14f),
                radius = size.minDimension * factor,
                center = hqCenter,
                style = Stroke(width = 1.2f)
            )
        }

        // Draw corridor arcs from Kisii HQ to all nodes
        nodes.filter { !it.isHeadquarters }.forEach { node ->
            val ratio = nodePositions[node.id] ?: Pair(0.5f, 0.5f)
            val target = Offset(size.width * ratio.first, size.height * ratio.second)
            val isSelected = node.id == selectedNode.id
            drawLine(
                color = if (isSelected) CrimsonPulse.copy(alpha = 0.85f) else GoldAmber.copy(alpha = 0.32f),
                start = hqCenter,
                end = target,
                strokeWidth = if (isSelected) 3f else 1.5f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
            )
        }

        // Expanding pulse on selected node
        val selectedRatio = nodePositions[selectedNode.id] ?: Pair(0.46f, 0.54f)
        val selectedCenter = Offset(size.width * selectedRatio.first, size.height * selectedRatio.second)
        val pulseColor = if (selectedNode.isHeadquarters) GoldBright else CrimsonPulse
        drawCircle(
            color = pulseColor.copy(alpha = (1f - pulse) * 0.55f),
            radius = 14f + (pulse * 42f),
            center = selectedCenter,
            style = Stroke(width = 2.5f)
        )

        // Draw node pins
        nodes.forEach { node ->
            val ratio = nodePositions[node.id] ?: Pair(0.5f, 0.5f)
            val center = Offset(size.width * ratio.first, size.height * ratio.second)
            val isSelected = node.id == selectedNode.id
            val pinColor = when {
                node.isHeadquarters -> GoldBright
                isSelected -> CrimsonPulse
                else -> InkSecondary
            }
            drawCircle(
                color = pinColor.copy(alpha = 0.25f),
                radius = if (isSelected) 18f else 12f,
                center = center
            )
            drawCircle(
                color = pinColor,
                radius = if (isSelected) 8f else 5.5f,
                center = center
            )
        }
    }
}
