package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkPrimary
import com.example.ui.theme.InkSecondary
import com.example.ui.theme.LiquidGlassTheme
import kotlin.math.roundToInt

private data class IntroScene(
    val id: String,
    val chapterTag: String,
    val headline: String,
    val subtitle: String,
    val chips: List<String>,
    @DrawableRes val backdropRes: Int,
    val showOrbCenterpiece: Boolean = false,
    val topAlignedBackdrop: Boolean = false,
    val useCrimsonPrimary: Boolean = false
)

private val introScenes = listOf(
    IntroScene(
        id = "scene_aurora_orb",
        chapterTag = "CHAPTER 01 • KISII, KENYA",
        headline = "AURORA EMPIRE",
        subtitle = "A multi-disciplinary technology & digital media studio engineering sovereign consumer software from East Africa for the world.",
        chips = listOf("Obsidian #06070B", "Est. Kisii, KE", "0.6817° S, 34.7667° E"),
        backdropRes = R.drawable.img_hero_aurora,
        showOrbCenterpiece = true
    ),
    IntroScene(
        id = "scene_primegram",
        chapterTag = "CHAPTER 02 • FLAGSHIP PLATFORM",
        headline = "PRIMEGRAM X",
        subtitle = "Next-generation Gen Z social networking, high-velocity Reels, independent music discovery, and sub-42ms WebRTC audio & video rooms.",
        chips = listOf("primegramx.co.ke", "WebRTC <42ms", "Reels + Sonic"),
        backdropRes = R.drawable.img_primegram_showcase,
        useCrimsonPrimary = true
    ),
    IntroScene(
        id = "scene_liquid_glass",
        chapterTag = "CHAPTER 03 • DESIGN SYSTEM",
        headline = "REFRACTIVE LIQUID GLASS",
        subtitle = "Signature dark-mode architecture balancing obsidian depth, translucent glass elevation, brushed metallic gold, and crimson telemetry.",
        chips = listOf("Gold #FBBF24", "Crimson #EF4444", "60-120fps Motion"),
        backdropRes = R.drawable.img_hero_aurora
    ),
    IntroScene(
        id = "scene_founder",
        chapterTag = "CHAPTER 04 • LEADERSHIP",
        headline = "JAVAN OMERI • MR. AURA",
        subtitle = "Founder & Lead Architect operating under JAVA PRIME ULTRA and JP7 ULTRA—uniting systems engineering with independent music production.",
        chips = listOf("Founder & Architect", "MR. AURA", "JAVA PRIME ULTRA"),
        backdropRes = R.drawable.img_founder_mr_aura,
        topAlignedBackdrop = true
    ),
    IntroScene(
        id = "scene_fintech",
        chapterTag = "CHAPTER 05 • CREATOR SOVEREIGNTY",
        headline = "LOCAL-FIRST FINTECH",
        subtitle = "Seamless M-Pesa STK Push and Paystack rails transforming digital attention and sonic drops into instant creator revenue.",
        chips = listOf("M-Pesa Native", "Paystack Global", "support@primegramx.co.ke"),
        backdropRes = R.drawable.img_primegram_showcase,
        useCrimsonPrimary = true
    )
)

private const val SCENE_DURATION_MS = 4200

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CinematicIntroOverlay(
    onSkip: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onSkip() }

    val glass = LiquidGlassTheme.glass
    var currentIndex by remember { mutableIntStateOf(0) }
    var isPaused by remember { mutableStateOf(false) }
    val sceneProgress = remember { Animatable(0f) }

    val currentScene = introScenes[currentIndex]
    val activeAccent = if (currentScene.useCrimsonPrimary) glass.crimsonAccent else glass.goldPrimary

    // Continuous auto-play loop ("plays like a video & taps itself until Skip")
    LaunchedEffect(currentIndex) {
        sceneProgress.snapTo(0f)
    }

    LaunchedEffect(currentIndex, isPaused) {
        if (!isPaused) {
            val remaining = (1f - sceneProgress.value).coerceIn(0f, 1f)
            val durationMillis = (SCENE_DURATION_MS * remaining).roundToInt().coerceAtLeast(100)
            sceneProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
            )
            currentIndex = (currentIndex + 1) % introScenes.size
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "intro_ambient")
    val orbRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "orb_rotation"
    )
    val orbBreath by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "orb_breath"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(glass.obsidianBase)
            // Left/Right edge tap to scrub, center tap to pause/play
            .pointerInput(currentIndex, isPaused) {
                detectTapGestures(
                    onTap = { offset ->
                        val width = size.width.toFloat().coerceAtLeast(1f)
                        when {
                            offset.x < width * 0.28f -> {
                                currentIndex = if (currentIndex == 0) {
                                    introScenes.lastIndex
                                } else {
                                    currentIndex - 1
                                }
                            }
                            offset.x > width * 0.72f -> {
                                currentIndex = (currentIndex + 1) % introScenes.size
                            }
                            else -> {
                                isPaused = !isPaused
                            }
                        }
                    }
                )
            }
    ) {
        // 1. Full-bleed Ken Burns motion backdrop
        AnimatedContent(
            targetState = currentScene,
            transitionSpec = {
                fadeIn(animationSpec = tween(650)) togetherWith fadeOut(animationSpec = tween(550))
            },
            label = "scene_backdrop"
        ) { scene ->
            val scale = 1.04f + (sceneProgress.value * 0.12f)
            val panX = if (scene.id.hashCode() % 2 == 0) {
                (sceneProgress.value - 0.5f) * 28f
            } else {
                (0.5f - sceneProgress.value) * 28f
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = scene.backdropRes),
                    contentDescription = scene.headline,
                    contentScale = ContentScale.Crop,
                    alignment = if (scene.topAlignedBackdrop) Alignment.TopCenter else Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            translationX = panX
                        }
                )

                // Deep cinematic obsidian vignette
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    glass.obsidianBase.copy(alpha = 0.74f),
                                    glass.obsidianBase.copy(alpha = 0.36f),
                                    glass.obsidianBase.copy(alpha = 0.86f),
                                    glass.obsidianBase.copy(alpha = 0.98f)
                                )
                            )
                        )
                )

                // Optional floating Aurora Orb centerpiece on Chapter 1
                if (scene.showOrbCenterpiece) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 110.dp)
                            .size(210.dp)
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawArc(
                                brush = Brush.sweepGradient(
                                    listOf(
                                        glass.goldPrimary,
                                        Color.Transparent,
                                        glass.crimsonAccent,
                                        Color.Transparent,
                                        glass.goldChampagne
                                    )
                                ),
                                startAngle = orbRotation,
                                sweepAngle = 300f,
                                useCenter = false,
                                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                            )
                            drawCircle(
                                color = glass.goldPrimary.copy(alpha = 0.16f),
                                radius = size.minDimension * 0.44f * orbBreath,
                                style = Stroke(width = 1.dp.toPx())
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(138.dp)
                                .graphicsLayer {
                                    scaleX = orbBreath
                                    scaleY = orbBreath
                                }
                                .clip(CircleShape)
                                .border(
                                    width = 1.5.dp,
                                    brush = glass.specularBorderBrush(glass.goldPrimary),
                                    shape = CircleShape
                                )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.aurora_orb_logo),
                                contentDescription = "Aurora Empire Aurora Orb",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }

        // 2. Top Segmented Reel Progress Bars + Skip Control
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                introScenes.forEachIndexed { index, _ ->
                    val fill = when {
                        index < currentIndex -> 1f
                        index == currentIndex -> sceneProgress.value.coerceIn(0f, 1f)
                        else -> 0f
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(3.5.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.22f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fill)
                                .fillMaxHeight()
                                .clip(CircleShape)
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(glass.goldChampagne, activeAccent)
                                    )
                                )
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.aurora_orb_logo),
                        contentDescription = "Aurora Orb",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .border(1.dp, activeAccent.copy(alpha = 0.65f), CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "AURORA EMPIRE",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = InkPrimary
                        )
                        Text(
                            text = if (isPaused) {
                                "PAUSED • ${currentIndex + 1}/${introScenes.size}"
                            } else {
                                "AUTO-PLAYING • ${currentIndex + 1}/${introScenes.size}"
                            },
                            style = MaterialTheme.typography.labelSmall,
                            color = activeAccent
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(
                        onClick = { isPaused = !isPaused },
                        modifier = Modifier.testTag("intro_play_pause_button")
                    ) {
                        Icon(
                            imageVector = if (isPaused) Icons.Filled.PlayArrow else Icons.Filled.Pause,
                            contentDescription = if (isPaused) "Resume intro" else "Pause intro",
                            tint = InkPrimary
                        )
                    }
                }

                // Prominent Skip Pill
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .clip(RoundedCornerShape(50))
                        .background(glass.obsidianElevated.copy(alpha = 0.88f))
                        .border(1.dp, activeAccent.copy(alpha = 0.65f), RoundedCornerShape(50))
                        .clickable(onClick = onSkip)
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .testTag("intro_skip_button")
                ) {
                    Text(
                        text = "Skip",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = InkPrimary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Filled.SkipNext,
                        contentDescription = "Skip intro",
                        tint = activeAccent,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        // 3. Bottom Kinetic Liquid Glass Caption Panel
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            LiquidGlassCard(
                modifier = Modifier.widthIn(max = 680.dp),
                glassAlpha = 0.84f,
                accentColor = activeAccent,
                contentPadding = PaddingValues(20.dp)
            ) {
                AnimatedContent(
                    targetState = currentScene,
                    transitionSpec = {
                        (fadeIn(tween(420)) + slideInVertically(tween(420)) { it / 5 }) togetherWith
                            fadeOut(tween(260))
                    },
                    label = "scene_copy"
                ) { scene ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = scene.chapterTag,
                                style = MaterialTheme.typography.labelSmall,
                                color = activeAccent
                            )
                            LiveTelemetryBadge(
                                label = if (isPaused) "PAUSED" else "LIVE REEL",
                                isCrimson = scene.useCrimsonPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = scene.headline,
                            style = MaterialTheme.typography.displaySmall,
                            color = InkPrimary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = scene.subtitle,
                            style = MaterialTheme.typography.bodyLarge,
                            color = InkSecondary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            scene.chips.forEach { chip ->
                                SpecChip(
                                    text = chip,
                                    accentColor = activeAccent,
                                    selected = true
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Tap edges to scrub • Center to pause",
                                style = MaterialTheme.typography.labelSmall,
                                color = InkMuted,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            GoldMetallicButton(
                                text = "Enter App",
                                icon = Icons.AutoMirrored.Filled.ArrowForward,
                                onClick = onSkip,
                                testTag = "intro_enter_app_button"
                            )
                        }
                    }
                }
            }
        }
    }
}
