package com.example

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color as AndroidColor
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Architecture
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Diamond
import androidx.compose.material.icons.outlined.Hub
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.model.NetworkNode
import com.example.ui.AuroraViewModel
import com.example.ui.components.ObsidianAtmosphereBackground
import com.example.ui.screens.ArchitectureScreen
import com.example.ui.screens.EcosystemScreen
import com.example.ui.screens.LeadershipScreen
import com.example.ui.screens.OverviewScreen
import com.example.ui.screens.RegistryScreen
import com.example.ui.theme.AuroraEmpireTheme
import com.example.ui.theme.CrimsonPulse
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldChampagne
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkPrimary
import com.example.ui.theme.ObsidianBase
import com.example.ui.theme.ObsidianElevated
import com.example.ui.theme.ObsidianSurface
import kotlinx.coroutines.launch

const val ROUTE_OVERVIEW = "overview"
const val ROUTE_ECOSYSTEM = "ecosystem"
const val ROUTE_ARCHITECTURE = "architecture"
const val ROUTE_LEADERSHIP = "leadership"
const val ROUTE_REGISTRY = "registry"

private data class NavDestinationItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val auroraDestinations = listOf(
    NavDestinationItem(
        route = ROUTE_OVERVIEW,
        label = "Empire",
        selectedIcon = Icons.Filled.Diamond,
        unselectedIcon = Icons.Outlined.Diamond
    ),
    NavDestinationItem(
        route = ROUTE_ECOSYSTEM,
        label = "Primegram",
        selectedIcon = Icons.Filled.Hub,
        unselectedIcon = Icons.Outlined.Hub
    ),
    NavDestinationItem(
        route = ROUTE_ARCHITECTURE,
        label = "Vision",
        selectedIcon = Icons.Filled.Architecture,
        unselectedIcon = Icons.Outlined.Architecture
    ),
    NavDestinationItem(
        route = ROUTE_LEADERSHIP,
        label = "Founder",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    ),
    NavDestinationItem(
        route = ROUTE_REGISTRY,
        label = "Schema",
        selectedIcon = Icons.Filled.Code,
        unselectedIcon = Icons.Outlined.Code
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(AndroidColor.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(AndroidColor.TRANSPARENT)
        )
        setContent {
            AuroraEmpireTheme {
                AuroraEmpireApp()
            }
        }
    }
}

@Composable
fun AuroraEmpireApp(
    viewModel: AuroraViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: ROUTE_OVERVIEW
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    fun navigateTo(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun showMessage(message: String) {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(message)
        }
    }

    fun copyText(label: String, text: String, toastMessage: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(label, text))
        showMessage(toastMessage)
    }

    fun openExternalUrl(url: String) {
        runCatching {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }.onFailure {
            showMessage("No browser available to open $url")
        }
    }

    fun emailFounder(email: String) {
        runCatching {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
                putExtra(Intent.EXTRA_SUBJECT, "Aurora Empire Executive Inquiry")
            }
            context.startActivity(intent)
        }.onFailure {
            copyText("Aurora Empire Email", email, "Email copied: $email")
        }
    }

    fun openInGoogleMaps(node: NetworkNode) {
        val uri = Uri.parse("geo:${node.latitude},${node.longitude}?q=${node.latitude},${node.longitude}(${Uri.encode(node.name)})")
        runCatching {
            val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.google.android.apps.maps")
            }
            context.startActivity(mapIntent)
        }.onFailure {
            openExternalUrl("https://maps.google.com/?q=${node.latitude},${node.longitude}")
        }
    }

    fun shareJsonLd(payload: String) {
        runCatching {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/json"
                putExtra(Intent.EXTRA_SUBJECT, "Aurora Empire — Schema.org JSON-LD Dossier")
                putExtra(Intent.EXTRA_TEXT, payload)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share Aurora Empire Dossier"))
        }.onFailure {
            copyText("JSON-LD", payload, "JSON-LD copied to clipboard")
        }
    }

    ObsidianAtmosphereBackground {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val useNavigationRail = maxWidth >= 600.dp

            Scaffold(
                containerColor = Color.Transparent,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState) { data ->
                        Snackbar(
                            snackbarData = data,
                            containerColor = ObsidianElevated,
                            contentColor = InkPrimary,
                            actionColor = GoldBright,
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                },
                topBar = {
                    AuroraExecutiveHeader(currentRoute = currentRoute)
                },
                bottomBar = {
                    if (!useNavigationRail) {
                        AuroraBottomNavigationBar(
                            currentRoute = currentRoute,
                            onSelectRoute = ::navigateTo
                        )
                    }
                }
            ) { innerPadding ->
                Row(modifier = Modifier.fillMaxSize()) {
                    if (useNavigationRail) {
                        AuroraSideNavigationRail(
                            currentRoute = currentRoute,
                            onSelectRoute = ::navigateTo,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(top = innerPadding.calculateTopPadding())
                        )
                    }

                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_OVERVIEW,
                        modifier = Modifier.weight(1f)
                    ) {
                        composable(ROUTE_OVERVIEW) {
                            OverviewScreen(
                                state = state,
                                onToggleDivision = viewModel::toggleDivision,
                                onExploreEcosystem = { navigateTo(ROUTE_ECOSYSTEM) },
                                onOpenFounderDossier = { navigateTo(ROUTE_LEADERSHIP) },
                                contentPadding = innerPadding
                            )
                        }
                        composable(ROUTE_ECOSYSTEM) {
                            EcosystemScreen(
                                state = state,
                                onSelectPrimegramFeature = viewModel::selectPrimegramFeature,
                                onLaunchUrl = ::openExternalUrl,
                                contentPadding = innerPadding
                            )
                        }
                        composable(ROUTE_ARCHITECTURE) {
                            ArchitectureScreen(
                                state = state,
                                onUpdateGlassOpacity = viewModel::updateGlassOpacity,
                                onSetAccentMode = viewModel::setGlassAccentMode,
                                contentPadding = innerPadding
                            )
                        }
                        composable(ROUTE_LEADERSHIP) {
                            LeadershipScreen(
                                state = state,
                                onSelectNode = viewModel::selectNetworkNode,
                                onOpenInGoogleMaps = ::openInGoogleMaps,
                                onEmailFounder = ::emailFounder,
                                onCopyEmail = { email ->
                                    copyText("Executive Email", email, "Copied $email")
                                },
                                contentPadding = innerPadding
                            )
                        }
                        composable(ROUTE_REGISTRY) {
                            RegistryScreen(
                                state = state,
                                onSelectSchemaFilter = viewModel::selectSchemaFilter,
                                onCopyJsonLd = { payload ->
                                    copyText("Aurora JSON-LD", payload, "JSON-LD schema copied to clipboard")
                                },
                                onShareDossier = ::shareJsonLd,
                                contentPadding = innerPadding
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AuroraExecutiveHeader(
    currentRoute: String
) {
    val activeLabel = auroraDestinations.firstOrNull { it.route == currentRoute }?.label ?: "Empire"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ObsidianBase.copy(alpha = 0.86f))
            .statusBarsPadding()
            .padding(horizontal = 18.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(listOf(GoldChampagne, GoldBright, CrimsonPulse)),
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.aurora_orb_logo),
                    contentDescription = "Aurora Empire Aurora Orb logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "AURORA EMPIRE",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = InkPrimary
                )
                Text(
                    text = "HOLDING DOSSIER • KISII, KE",
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldBright
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(ObsidianElevated)
                .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(50))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(CrimsonPulse)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = activeLabel.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = InkPrimary
            )
        }
    }
}

@Composable
private fun AuroraBottomNavigationBar(
    currentRoute: String,
    onSelectRoute: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ObsidianSurface.copy(alpha = 0.94f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            GoldBright.copy(alpha = 0.35f),
                            CrimsonPulse.copy(alpha = 0.25f),
                            Color.Transparent
                        )
                    )
                )
        )
        NavigationBar(
            containerColor = Color.Transparent,
            windowInsets = WindowInsets.navigationBars,
            tonalElevation = 0.dp
        ) {
            auroraDestinations.forEach { item ->
                val selected = item.route == currentRoute
                NavigationBarItem(
                    selected = selected,
                    onClick = { onSelectRoute(item.route) },
                    icon = {
                        Icon(
                            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.label
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = GoldBright,
                        selectedTextColor = GoldBright,
                        indicatorColor = GoldMuted,
                        unselectedIconColor = InkMuted,
                        unselectedTextColor = InkMuted
                    ),
                    modifier = Modifier.testTag("nav_${item.route}")
                )
            }
        }
    }
}

@Composable
private fun AuroraSideNavigationRail(
    currentRoute: String,
    onSelectRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        containerColor = ObsidianSurface.copy(alpha = 0.90f),
        modifier = modifier
            .border(1.dp, Color.White.copy(alpha = 0.08f))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        auroraDestinations.forEach { item ->
            val selected = item.route == currentRoute
            NavigationRailItem(
                selected = selected,
                onClick = { onSelectRoute(item.route) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = GoldBright,
                    selectedTextColor = GoldBright,
                    indicatorColor = GoldMuted,
                    unselectedIconColor = InkMuted,
                    unselectedTextColor = InkMuted
                ),
                modifier = Modifier.testTag("rail_${item.route}")
            )
        }
    }
}
