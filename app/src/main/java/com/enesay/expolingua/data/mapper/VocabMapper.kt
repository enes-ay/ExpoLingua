package com.enesay.expolingua.data.mapper

import com.enesay.expolingua.data.local.VocabEntity
import com.enesay.expolingua.domain.model.Vocab

fun VocabEntity.toVocab(): Vocab {
    return Vocab(
        id = id,
        word = word,
        sentence = sentence,
        timestamp = timestamp
    )
}

fun Vocab.toVocabEntity(): VocabEntity {
    return VocabEntity(
        id = id,
        word = word,
        sentence = sentence,
        timestamp = timestamp
    )
} 