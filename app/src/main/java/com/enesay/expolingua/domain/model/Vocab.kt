package com.enesay.expolingua.domain.model

data class Vocab(
    val id: Int = 0,
    val word: String,
    val sentence: String,
    val timestamp: Long = System.currentTimeMillis()
) 