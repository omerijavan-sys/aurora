package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.AuroraRepository
import com.example.model.SchemaSectionFilter
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read app name from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Aurora Empire", appName)
    }

    @Test
    fun `json ld schema links organization founder and flagship product`() {
        val raw = AuroraRepository.buildJsonLd(SchemaSectionFilter.ALL)
        val json = JSONObject(raw)
        assertEquals("https://schema.org", json.getString("@context"))
        val graph = json.getJSONArray("@graph")
        assertEquals(3, graph.length())
        assertTrue(raw.contains("Aurora Empire"))
        assertTrue(raw.contains("Javan Omeri"))
        assertTrue(raw.contains("Primegram X"))
        assertTrue(raw.contains("support@primegramx.co.ke"))
    }
}
