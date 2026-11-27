package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.AuroraRepository
import com.example.model.ArchitecturePillar
import com.example.model.CoreDivision
import com.example.model.CorporateMetric
import com.example.model.FounderProfile
import com.example.model.NetworkNode
import com.example.model.PortfolioEntity
import com.example.model.PrimegramFeature
import com.example.model.SchemaSectionFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class GlassAccentMode(val label: String) {
    SOVEREIGN_GOLD("Sovereign Gold"),
    CRIMSON_PULSE("Crimson Pulse")
}

data class AuroraUiState(
    val heroMetrics: List<CorporateMetric> = AuroraRepository.heroMetrics,
    val divisions: List<CoreDivision> = AuroraRepository.coreDivisions,
    val expandedDivisionId: String = AuroraRepository.coreDivisions.first().id,
    val primegramFeatures: List<PrimegramFeature> = AuroraRepository.primegramFeatures,
    val selectedPrimegramFeatureId: String = AuroraRepository.primegramFeatures.first().id,
    val portfolioEntities: List<PortfolioEntity> = AuroraRepository.portfolioEntities,
    val architecturePillars: List<ArchitecturePillar> = AuroraRepository.architecturePillars,
    val founderProfile: FounderProfile = AuroraRepository.founderProfile,
    val networkNodes: List<NetworkNode> = AuroraRepository.networkNodes,
    val selectedNodeId: String = AuroraRepository.networkNodes.first().id,
    val glassOpacity: Float = 0.78f,
    val glassAccentMode: GlassAccentMode = GlassAccentMode.SOVEREIGN_GOLD,
    val selectedSchemaFilter: SchemaSectionFilter = SchemaSectionFilter.ALL,
    val jsonLdPayload: String = AuroraRepository.buildJsonLd(SchemaSectionFilter.ALL)
) {
    val selectedPrimegramFeature: PrimegramFeature
        get() = primegramFeatures.firstOrNull { it.id == selectedPrimegramFeatureId }
            ?: primegramFeatures.first()

    val selectedNetworkNode: NetworkNode
        get() = networkNodes.firstOrNull { it.id == selectedNodeId }
            ?: networkNodes.first()
}

class AuroraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuroraUiState())
    val uiState: StateFlow<AuroraUiState> = _uiState.asStateFlow()

    fun toggleDivision(divisionId: String) {
        _uiState.update { state ->
            state.copy(
                expandedDivisionId = if (state.expandedDivisionId == divisionId) "" else divisionId
            )
        }
    }

    fun selectPrimegramFeature(featureId: String) {
        _uiState.update { it.copy(selectedPrimegramFeatureId = featureId) }
    }

    fun selectNetworkNode(nodeId: String) {
        _uiState.update { it.copy(selectedNodeId = nodeId) }
    }

    fun updateGlassOpacity(opacity: Float) {
        _uiState.update { it.copy(glassOpacity = opacity.coerceIn(0.45f, 0.95f)) }
    }

    fun setGlassAccentMode(mode: GlassAccentMode) {
        _uiState.update { it.copy(glassAccentMode = mode) }
    }

    fun selectSchemaFilter(filter: SchemaSectionFilter) {
        _uiState.update { state ->
            state.copy(
                selectedSchemaFilter = filter,
                jsonLdPayload = AuroraRepository.buildJsonLd(filter)
            )
        }
    }
}
