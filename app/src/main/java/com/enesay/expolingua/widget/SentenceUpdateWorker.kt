package com.enesay.expolingua.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SentenceUpdateWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val manager = GlanceAppWidgetManager(context)
            val glanceIds = manager.getGlanceIds(SentenceWidget::class.java)
            
            glanceIds.forEach { glanceId ->
                SentenceWidget().update(context, glanceId)
            }
            
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
} 