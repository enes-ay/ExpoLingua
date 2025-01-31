package com.enesay.expolingua.widget

import android.content.Context
import com.enesay.expolingua.data.local.VocabDatabase
import com.enesay.expolingua.data.local.VocabEntity
import com.enesay.expolingua.data.preferences.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WidgetRepository(private val context: Context) {
    
    private val database by lazy { VocabDatabase.getDatabase(context) }
    private val vocabDao by lazy { database.vocabDao() }
    private val preferencesManager = PreferencesManager(context)
    
    suspend fun getNextVocab(): VocabEntity? = withContext(Dispatchers.IO) {
        val allVocab = vocabDao.getAllVocabList()
        if (allVocab.isEmpty()) return@withContext null
        
        val currentIndex = preferencesManager.getCurrentWordIndex()
        val nextIndex = currentIndex % allVocab.size
        
        preferencesManager.incrementWordIndex()
        allVocab.getOrNull(nextIndex)
    }
} 