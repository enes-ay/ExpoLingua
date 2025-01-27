package com.enesay.expolingua.ui.presentation.vocab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesay.expolingua.domain.model.Vocab
import com.enesay.expolingua.domain.repository.VocabRepository
import com.enesay.expolingua.domain.use_case.AddVocabUseCase
import com.enesay.expolingua.domain.use_case.GetAllVocabUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabViewModel @Inject constructor(
    private val getAllVocabUseCase: GetAllVocabUseCase,
    private val addVocabUseCase: AddVocabUseCase,
    private val repository: VocabRepository
) : ViewModel() {

    private val _state = MutableStateFlow(VocabState())
    val state = _state.asStateFlow()

    init {
        getVocabList()
    }

    private fun getVocabList() {
        viewModelScope.launch {
            getAllVocabUseCase().collect { vocabList ->
                _state.update { it.copy(vocabList = vocabList) }
            }
        }
    }

    fun onEvent(event: VocabEvent) {
        when (event) {
            is VocabEvent.AddVocab -> {
                viewModelScope.launch {
                    addVocabUseCase(
                        Vocab(
                            word = state.value.newWordText,
                            sentence = ""
                        )
                    )
                    _state.update { it.copy(
                        isAddDialogVisible = false,
                        newWordText = ""
                    ) }
                }
            }
            is VocabEvent.ShowAddDialog -> {
                _state.update { it.copy(isAddDialogVisible = true) }
            }
            is VocabEvent.HideAddDialog -> {
                _state.update { it.copy(
                    isAddDialogVisible = false,
                    newWordText = ""
                ) }
            }
            is VocabEvent.UpdateNewWordText -> {
                _state.update { it.copy(newWordText = event.text) }
            }
            is VocabEvent.DeleteVocab -> {
                viewModelScope.launch {
                    repository.deleteVocab(event.vocab)
                }
            }
        }
    }
} 