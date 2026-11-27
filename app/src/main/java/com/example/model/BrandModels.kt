package com.example.model

enum class DivisionCategory {
    SOCIAL_REALTIME,
    LIQUID_GLASS_UX,
    MUSIC_MEDIA,
    FINTECH_MONETIZATION
}

data class CorporateMetric(
    val id: String,
    val label: String,
    val value: String,
    val accentIsCrimson: Boolean = false
)

data class CoreDivision(
    val id: String,
    val category: DivisionCategory,
    val code: String,
    val title: String,
    val subtitle: String,
    val summary: String,
    val capabilities: List<String>,
    val highlightMetric: String
)

data class PrimegramFeature(
    val id: String,
    val title: String,
    val badge: String,
    val description: String,
    val architectureSpecs: List<String>,
    val latencyLabel: String
)

data class PortfolioEntity(
    val id: String,
    val name: String,
    val roleTag: String,
    val description: String,
    val status: String,
    val externalUrl: String? = null
)

data class ArchitecturePillar(
    val id: String,
    val index: String,
    val title: String,
    val thesis: String,
    val engineeringSpecs: List<String>
)

data class FounderProfile(
    val fullName: String,
    val monikers: List<String>,
    val roleTitle: String,
    val headquarters: String,
    val coordinatesLabel: String,
    val officialEmail: String,
    val flagshipUrl: String,
    val bio: String,
    val leadershipPrinciples: List<String>
)

data class NetworkNode(
    val id: String,
    val name: String,
    val role: String,
    val cityCountry: String,
    val latitude: Double,
    val longitude: Double,
    val latencyMs: String,
    val isHeadquarters: Boolean = false
)

enum class SchemaSectionFilter(val label: String) {
    ALL("Full Graph"),
    ORGANIZATION("Organization"),
    FOUNDER("Person (Founder)"),
    SOFTWARE_APP("Primegram X")
}
