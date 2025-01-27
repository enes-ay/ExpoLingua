package com.enesay.expolingua.util

import android.content.Context
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.enesay.expolingua.domain.repository.VocabRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SentenceWidget @Inject constructor(
    private val repository: VocabRepository
) : GlanceAppWidget() {
    
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val randomVocab = runBlocking { repository.getRandomVocab() }

        provideContent {
            Column(
                modifier = GlanceModifier.fillMaxSize().padding(16.dp)
            ) {
                Text(
                    text = randomVocab?.sentence ?: "Add some vocabulary to see sentences here!"
                )
            }
        }
    }
}