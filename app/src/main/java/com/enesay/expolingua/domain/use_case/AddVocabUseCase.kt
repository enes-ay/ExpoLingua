package com.enesay.expolingua.domain.use_case

import com.enesay.expolingua.domain.model.Vocab
import com.enesay.expolingua.domain.repository.VocabRepository
import javax.inject.Inject

class AddVocabUseCase @Inject constructor(
    private val repository: VocabRepository
) {
    suspend operator fun invoke(vocab: Vocab) {
        repository.insertVocab(vocab)
    }
} 