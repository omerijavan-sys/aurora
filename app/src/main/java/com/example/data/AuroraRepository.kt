package com.example.data

import com.example.model.ArchitecturePillar
import com.example.model.CoreDivision
import com.example.model.CorporateMetric
import com.example.model.DivisionCategory
import com.example.model.FounderProfile
import com.example.model.NetworkNode
import com.example.model.PortfolioEntity
import com.example.model.PrimegramFeature
import com.example.model.SchemaSectionFilter
import org.json.JSONArray
import org.json.JSONObject

object AuroraRepository {

    const val BRAND_NAME = "Aurora Empire"
    const val FLAGSHIP_URL = "https://primegramx.co.ke"
    const val CONTACT_EMAIL = "support@primegramx.co.ke"
    const val HQ_LATITUDE = -0.6817
    const val HQ_LONGITUDE = 34.7667

    val heroMetrics: List<CorporateMetric> = listOf(
        CorporateMetric(
            id = "divisions",
            label = "CORE DIVISIONS",
            value = "04"
        ),
        CorporateMetric(
            id = "webrtc",
            label = "WEBRTC RELAY",
            value = "<42ms",
            accentIsCrimson = true
        ),
        CorporateMetric(
            id = "rails",
            label = "CREATOR RAILS",
            value = "M-PESA • PAYSTACK"
        ),
        CorporateMetric(
            id = "origin",
            label = "ORIGIN HQ",
            value = "KISII, KE"
        )
    )

    val coreDivisions: List<CoreDivision> = listOf(
        CoreDivision(
            id = "div_social",
            category = DivisionCategory.SOCIAL_REALTIME,
            code = "DIV-01",
            title = "Consumer Social & Real-Time Platforms",
            subtitle = "Low-latency social graphs & live WebRTC spaces",
            summary = "Architecting high-velocity consumer software engineered for mobile-first bandwidth profiles without sacrificing spatial fluid polish.",
            capabilities = listOf(
                "WebRTC multi-party audio & HD video rooms",
                "Edge-cached short-video (Reels) delivery pipelines",
                "Real-time presence, social graphs & creator feeds"
            ),
            highlightMetric = "99.94% Relay Uptime"
        ),
        CoreDivision(
            id = "div_glass",
            category = DivisionCategory.LIQUID_GLASS_UX,
            code = "DIV-02",
            title = "Liquid Glass UI/UX Design Systems",
            subtitle = "Obsidian depth, refraction & metallic gold hierarchy",
            summary = "Proprietary dark-mode design language unifying translucent glassmorphism, specular rim lighting, and surgical typographic contrast.",
            capabilities = listOf(
                "Refractive #06070B obsidian surface elevation tokens",
                "Brushed gold (#FBBF24) & crimson (#EF4444) signal states",
                "Adaptive motion choreography across mobile & spatial screens"
            ),
            highlightMetric = "60-120fps Compose & Web"
        ),
        CoreDivision(
            id = "div_music",
            category = DivisionCategory.MUSIC_MEDIA,
            code = "DIV-03",
            title = "Digital Music & Creative Production",
            subtitle = "Independent audio-visual storytelling & artist identity",
            summary = "Fusing music discovery directly into social architecture—empowering independent East African sound designers and recording artists.",
            capabilities = listOf(
                "Independent track mastering & lossless preview pipelines",
                "Synchronized Reels audio attribution & stem discovery",
                "Executive artist branding under the MR. AURA imprint"
            ),
            highlightMetric = "Integrated Sonic Engine"
        ),
        CoreDivision(
            id = "div_fintech",
            category = DivisionCategory.FINTECH_MONETIZATION,
            code = "DIV-04",
            title = "Local-First FinTech & Creator Monetization",
            subtitle = "M-Pesa & Paystack native digital commerce",
            summary = "Eliminating global payment friction so East African creators receive instant payouts via mobile money and cross-border cards.",
            capabilities = listOf(
                "M-Pesa STK Push instant creator tipping & subscriptions",
                "Paystack multi-currency checkout & digital goods",
                "Automated ledger reconciliation & micro-royalty splits"
            ),
            highlightMetric = "T+0 Mobile Settlement"
        )
    )

    val primegramFeatures: List<PrimegramFeature> = listOf(
        PrimegramFeature(
            id = "pg_social",
            title = "Gen Z Social Graph",
            badge = "CORE FEED",
            description = "Algorithmically transparent discovery built around cultural velocity, close-circle resonance, and zero-clutter dark glass cards.",
            architectureSpecs = listOf(
                "Sub-120ms feed hydration on 4G/3G networks",
                "Verified creator identity & badge hierarchy",
                "End-to-end encrypted direct messaging threads"
            ),
            latencyLabel = "118ms p95 Feed"
        ),
        PrimegramFeature(
            id = "pg_reels",
            title = "Prime Reels Engine",
            badge = "SHORT VIDEO",
            description = "Adaptive bitrate vertical cinema tuned for crisp playback, tactile gesture scrubbing, and instant audio-stem remixing.",
            architectureSpecs = listOf(
                "Pre-buffered HLS adaptive streaming chunks",
                "Frame-accurate sound sync & creator attribution",
                "Zero-drop transitions inside Liquid Glass frames"
            ),
            latencyLabel = "180ms Cold Start"
        ),
        PrimegramFeature(
            id = "pg_music",
            title = "Sonic Discovery Hub",
            badge = "AUDIO LAB",
            description = "Native music discovery surfacing emerging African and global independent artists alongside live waveform previews and artist drops.",
            architectureSpecs = listOf(
                "Real-time FFT waveform rendering",
                "One-tap track linking into Reels & Live Rooms",
                "Direct artist support via M-Pesa & Paystack"
            ),
            latencyLabel = "320kbps AAC"
        ),
        PrimegramFeature(
            id = "pg_webrtc",
            title = "WebRTC Live Rooms",
            badge = "REAL-TIME",
            description = "Ultra-low-latency spatial audio stages and peer-assisted HD video lounges connecting creators and communities in real time.",
            architectureSpecs = listOf(
                "SFU selective forwarding with regional edge TURN",
                "Opus adaptive voice clarity under packet loss",
                "Crimson live-pulse telemetry & co-host stage controls"
            ),
            latencyLabel = "<42ms East Africa"
        )
    )

    val portfolioEntities: List<PortfolioEntity> = listOf(
        PortfolioEntity(
            id = "ent_primegram",
            name = "Primegram X",
            roleTag = "FLAGSHIP CONSUMER PLATFORM",
            description = "Next-generation Gen Z social networking, Reels, music discovery, and real-time WebRTC audio/video platform.",
            status = "LIVE IN PRODUCTION",
            externalUrl = FLAGSHIP_URL
        ),
        PortfolioEntity(
            id = "ent_design_lab",
            name = "Aurora Liquid Glass Lab",
            roleTag = "DESIGN & UI ARCHITECTURE",
            description = "Internal systems laboratory crafting obsidian glassmorphism component kits, micro-interactions, and visual identity systems.",
            status = "ACTIVE R&D"
        ),
        PortfolioEntity(
            id = "ent_media",
            name = "Aurora Sonic & Media",
            roleTag = "DIGITAL MUSIC DIVISION",
            description = "Independent music production, visual direction, and audio-first creator amplification anchored by JAVA PRIME ULTRA.",
            status = "ACTIVE IMPRINT"
        )
    )

    val architecturePillars: List<ArchitecturePillar> = listOf(
        ArchitecturePillar(
            id = "pillar_glass",
            index = "01",
            title = "Refractive Liquid Glass UI/UX",
            thesis = "Software should feel physical, luminous, and unmistakable. Every surface balances #06070B obsidian depth with calibrated translucent glass, specular edge highlights, and brushed gold authority.",
            engineeringSpecs = listOf(
                "Strict contrast ratios on OLED obsidian surfaces",
                "1dp directional specular gradients instead of heavy drop shadows",
                "Deterministic typography pairing Space Grotesk + JetBrains Mono"
            )
        ),
        ArchitecturePillar(
            id = "pillar_realtime",
            index = "02",
            title = "Real-Time Engineering at the Edge",
            thesis = "East African consumers deserve global-grade speed. Aurora Empire optimizes WebRTC signaling, media chunking, and optimistic UI state so interactions resolve instantaneously.",
            engineeringSpecs = listOf(
                "Adaptive WebRTC congestion control for mobile networks",
                "Lightweight JSON/binary framing to conserve user data",
                "Fault-tolerant state synchronization and graceful degradation"
            )
        ),
        ArchitecturePillar(
            id = "pillar_creator",
            index = "03",
            title = "Accessible Creator Economies",
            thesis = "Talent is everywhere; financial rails must meet creators where they live. Direct M-Pesa and Paystack integration turns social attention into immediate economic sovereignty.",
            engineeringSpecs = listOf(
                "Native KES & multi-currency settlement workflows",
                "Transparent creator split ledgers with instant verification",
                "Zero-gate entry for emerging musicians and video directors"
            )
        )
    )

    val founderProfile = FounderProfile(
        fullName = "Javan Omeri",
        monikers = listOf("MR. AURA", "JAVA PRIME ULTRA", "JP7 ULTRA"),
        roleTitle = "Founder & Lead Software Architect",
        headquarters = "Kisii, Kenya",
        coordinatesLabel = "0.6817° S, 34.7667° E",
        officialEmail = CONTACT_EMAIL,
        flagshipUrl = FLAGSHIP_URL,
        bio = "Technologist, full-stack systems architect, and independent music producer building Aurora Empire from Kisii, Kenya. Operating under the creative monikers MR. AURA, JAVA PRIME ULTRA, and JP7 ULTRA, Javan leads the engineering, Liquid Glass design language, and product strategy behind Primegram X.",
        leadershipPrinciples = listOf(
            "Architectural clarity over bloated complexity",
            "Youth-led East African innovation competing on global craft",
            "Bridging consumer social speed with local-first mobile money"
        )
    )

    val networkNodes: List<NetworkNode> = listOf(
        NetworkNode(
            id = "node_kisii",
            name = "Aurora Empire HQ",
            role = "Founding Studio & Core Engineering",
            cityCountry = "Kisii, Kenya",
            latitude = -0.6817,
            longitude = 34.7667,
            latencyMs = "0ms Origin",
            isHeadquarters = true
        ),
        NetworkNode(
            id = "node_nairobi",
            name = "Nairobi Creator Exchange",
            role = "East Africa FinTech & Media IXP",
            cityCountry = "Nairobi, Kenya",
            latitude = -1.2921,
            longitude = 36.8219,
            latencyMs = "14ms RTT"
        ),
        NetworkNode(
            id = "node_kampala",
            name = "Great Lakes Relay",
            role = "WebRTC Regional Edge Node",
            cityCountry = "Kampala, Uganda",
            latitude = 0.3476,
            longitude = 32.5825,
            latencyMs = "22ms RTT"
        ),
        NetworkNode(
            id = "node_london",
            name = "EMEA Global Gateway",
            role = "Cross-Continental CDN & TURN",
            cityCountry = "London, UK",
            latitude = 51.5072,
            longitude = -0.1276,
            latencyMs = "112ms RTT"
        )
    )

    fun buildJsonLd(filter: SchemaSectionFilter): String {
        val founderObj = JSONObject().apply {
            put("@type", "Person")
            put("@id", "https://primegramx.co.ke/#javan-omeri")
            put("name", "Javan Omeri")
            put("alternateName", JSONArray(listOf("MR. AURA", "JAVA PRIME ULTRA", "JP7 ULTRA")))
            put("jobTitle", "Founder & Lead Architect")
            put("email", "mailto:$CONTACT_EMAIL")
            put("homeLocation", JSONObject().apply {
                put("@type", "Place")
                put("name", "Kisii, Kenya")
                put("address", JSONObject().apply {
                    put("@type", "PostalAddress")
                    put("addressLocality", "Kisii")
                    put("addressCountry", "KE")
                })
            })
            put("worksFor", JSONObject().apply {
                put("@id", "https://primegramx.co.ke/#aurora-empire")
            })
            put("sameAs", JSONArray(listOf(FLAGSHIP_URL)))
        }

        val softwareObj = JSONObject().apply {
            put("@type", "SoftwareApplication")
            put("@id", "https://primegramx.co.ke/#primegram-x")
            put("name", "Primegram X")
            put("url", FLAGSHIP_URL)
            put("applicationCategory", "SocialNetworkingApplication")
            put("operatingSystem", "Android, iOS, Web")
            put("description", "Next-generation Gen Z social networking, short-video Reels, music discovery, and real-time WebRTC audio/video communication platform.")
            put("creator", JSONObject().apply {
                put("@id", "https://primegramx.co.ke/#aurora-empire")
            })
            put("author", JSONObject().apply {
                put("@id", "https://primegramx.co.ke/#javan-omeri")
            })
            put("featureList", JSONArray(listOf(
                "Gen Z Social Networking",
                "Short-Video Reels",
                "Music Discovery",
                "Real-Time WebRTC Audio & Video Rooms",
                "M-Pesa & Paystack Creator Monetization"
            )))
            put("offers", JSONObject().apply {
                put("@type", "Offer")
                put("price", "0")
                put("priceCurrency", "KES")
            })
        }

        val organizationObj = JSONObject().apply {
            put("@type", "Organization")
            put("@id", "https://primegramx.co.ke/#aurora-empire")
            put("name", BRAND_NAME)
            put("url", FLAGSHIP_URL)
            put("email", CONTACT_EMAIL)
            put("description", "Parent technology, digital media, and creative innovation studio founded by Javan Omeri (MR. AURA) in Kisii, Kenya.")
            put("foundingLocation", JSONObject().apply {
                put("@type", "Place")
                put("name", "Kisii, Kenya")
                put("geo", JSONObject().apply {
                    put("@type", "GeoCoordinates")
                    put("latitude", HQ_LATITUDE)
                    put("longitude", HQ_LONGITUDE)
                })
                put("address", JSONObject().apply {
                    put("@type", "PostalAddress")
                    put("addressLocality", "Kisii")
                    put("addressCountry", "KE")
                })
            })
            put("founder", JSONObject().apply {
                put("@id", "https://primegramx.co.ke/#javan-omeri")
            })
            put("owns", JSONObject().apply {
                put("@id", "https://primegramx.co.ke/#primegram-x")
            })
            put("knowsAbout", JSONArray(listOf(
                "Consumer Social Platforms",
                "WebRTC Real-Time Communication",
                "Liquid Glass UI/UX Design Systems",
                "Digital Music Production",
                "M-Pesa and Paystack Creator Monetization"
            )))
            put("contactPoint", JSONObject().apply {
                put("@type", "ContactPoint")
                put("contactType", "Executive & Support")
                put("email", CONTACT_EMAIL)
                put("areaServed", JSONArray(listOf("KE", "EA", "Global")))
            })
        }

        val root = when (filter) {
            SchemaSectionFilter.ALL -> JSONObject().apply {
                put("@context", "https://schema.org")
                put("@graph", JSONArray().apply {
                    put(organizationObj)
                    put(founderObj)
                    put(softwareObj)
                })
            }
            SchemaSectionFilter.ORGANIZATION -> organizationObj.apply {
                put("@context", "https://schema.org")
            }
            SchemaSectionFilter.FOUNDER -> founderObj.apply {
                put("@context", "https://schema.org")
            }
            SchemaSectionFilter.SOFTWARE_APP -> softwareObj.apply {
                put("@context", "https://schema.org")
            }
        }
        return root.toString(2)
    }
}
