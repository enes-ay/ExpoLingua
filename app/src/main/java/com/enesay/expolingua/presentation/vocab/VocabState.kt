package com.enesay.expolingua.presentation.vocab

import com.enesay.expolingua.data.local.VocabEntity

data class VocabState(
    val vocabList: List<VocabEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAddDialogVisible: Boolean = false,
    val newWordText: String = ""
) 