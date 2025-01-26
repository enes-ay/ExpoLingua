package com.enesay.expolingua.data.repository

import com.enesay.expolingua.ai.GeminiService
import com.enesay.expolingua.data.VocabDao
import com.enesay.expolingua.data.local.VocabEntity
import com.enesay.expolingua.data.mapper.toVocab
import com.enesay.expolingua.data.mapper.toVocabEntity
import com.enesay.expolingua.domain.model.Vocab
import com.enesay.expolingua.domain.repository.VocabRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VocabRepositoryImpl @Inject constructor(
    private val dao: VocabDao,
    private val geminiService: GeminiService
) : VocabRepository {
    
    override fun getAllVocab(): Flow<List<VocabEntity>> {
        return dao.getAllVocab().map { entities -> 
            entities.map { it }
        }
    }
    
    override suspend fun insertVocab(vocab: Vocab) {
        val sentence = geminiService.generateSentence(vocab.word)
        dao.insertVocab(vocab.copy(sentence = sentence).toVocabEntity())
    }
    
    override suspend fun getRandomVocab(): Vocab? {
        return dao.getRandomVocab()?.toVocab()
    }

    override suspend fun deleteVocab(vocab: VocabEntity) {
        dao.deleteVocab(vocab)
    }
} 