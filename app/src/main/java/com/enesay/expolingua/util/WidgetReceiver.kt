package com.enesay.expolingua.util

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.enesay.expolingua.domain.repository.VocabRepository
import javax.inject.Inject

class MyWidgetReceiver @Inject constructor(val repository: VocabRepository): GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = SentenceWidget(repository)
}