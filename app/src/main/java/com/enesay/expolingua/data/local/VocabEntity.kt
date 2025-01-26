package com.enesay.expolingua.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary")
data class VocabEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String,
    val sentence: String,
    val timestamp: Long = System.currentTimeMillis()
) 