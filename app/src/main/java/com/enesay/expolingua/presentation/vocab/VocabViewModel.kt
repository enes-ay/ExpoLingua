package com.enesay.expolingua.presentation.vocab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesay.expolingua.domain.model.Vocab
import com.enesay.expolingua.domain.use_case.AddVocabUseCase
import com.enesay.expolingua.domain.use_case.GetAllVocabUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabViewModel @Inject constructor(
    private val getAllVocabUseCase: GetAllVocabUseCase,
    private val addVocabUseCase: AddVocabUseCase
) : ViewModel() {

    val vocabList = getAllVocabUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addVocab(word: String) {
        viewModelScope.launch {
            addVocabUseCase(
                Vocab(
                    word = word,
                    sentence = "" // Will be generated in repository
                )
            )
        }
    }
} 