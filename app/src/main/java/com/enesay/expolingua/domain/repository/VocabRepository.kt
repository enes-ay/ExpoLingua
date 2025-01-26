package com.enesay.expolingua.domain.repository

import com.enesay.expolingua.data.local.VocabEntity
import com.enesay.expolingua.domain.model.Vocab
import kotlinx.coroutines.flow.Flow

interface VocabRepository {
    fun getAllVocab(): Flow<List<VocabEntity>>
    suspend fun insertVocab(vocab: Vocab)
    suspend fun getRandomVocab(): Vocab?
    suspend fun deleteVocab(vocab: VocabEntity)
} 