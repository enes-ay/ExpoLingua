package com.enesay.expolingua.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.enesay.expolingua.data.local.VocabEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabDao {
    @Query("SELECT * FROM vocabulary ORDER BY timestamp DESC")
    fun getAllVocab(): Flow<List<VocabEntity>>
    
    @Insert
    suspend fun insertVocab(vocab: VocabEntity)
    
    @Query("SELECT * FROM vocabulary ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomVocab(): VocabEntity?
    
    @Delete
    suspend fun deleteVocab(vocab: VocabEntity)
    
    @Query("SELECT * FROM vocabulary ORDER BY timestamp DESC")
    suspend fun getAllVocabList(): List<VocabEntity>
} 