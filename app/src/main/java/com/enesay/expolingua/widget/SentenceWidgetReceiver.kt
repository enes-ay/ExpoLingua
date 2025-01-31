package com.enesay.expolingua.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.*
import java.util.concurrent.TimeUnit

class SentenceWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = SentenceWidget()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        scheduleUpdates(context)
    }

    private fun scheduleUpdates(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val updateRequest = PeriodicWorkRequestBuilder<SentenceUpdateWorker>(
            1, TimeUnit.HOURS
        ).setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "sentence_update_work",
            ExistingPeriodicWorkPolicy.UPDATE,
            updateRequest
        )
    }
} 