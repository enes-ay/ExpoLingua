package com.enesay.expolingua.presentation.vocab

import com.enesay.expolingua.data.local.VocabEntity

sealed class VocabEvent {
    object AddVocab : VocabEvent()
    object ShowAddDialog : VocabEvent()
    object HideAddDialog : VocabEvent()
    data class DeleteVocab(val vocab: VocabEntity) : VocabEvent()
    data class UpdateNewWordText(val text: String) : VocabEvent()
} 