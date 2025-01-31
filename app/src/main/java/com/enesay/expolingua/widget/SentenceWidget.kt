package com.enesay.expolingua.widget

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.*
import androidx.glance.text.*
import androidx.glance.unit.ColorProvider
import com.enesay.expolingua.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class SentenceWidget : GlanceAppWidget() {

    @SuppressLint("RestrictedApi")
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val repository = WidgetRepository(context)
        val vocab = withContext(Dispatchers.IO) {
            repository.getNextVocab()
        }

        provideContent {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ImageProvider(R.drawable.widget_background))
                    .padding(16.dp)
                    .clickable { 
                        // Update widget on click
                        runBlocking {
                            update(context, id)
                        }
                    }
            ) {
                Column(
                    modifier = GlanceModifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (vocab != null) {
                        Text(
                            text = vocab.word,
                            style = TextStyle(
                                color = ColorProvider(R.color.widget_text_color),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                        
                        Spacer(GlanceModifier.height(8.dp))
                        
                        Text(
                            text = vocab.sentence,
                            style = TextStyle(
                                color = ColorProvider(R.color.widget_text_color),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            ),
                            maxLines = 3
                        )
                    } else {
                        Text(
                            text = "Add some vocabulary to see sentences!",
                            style = TextStyle(
                                color = ColorProvider(R.color.widget_text_color),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
} 