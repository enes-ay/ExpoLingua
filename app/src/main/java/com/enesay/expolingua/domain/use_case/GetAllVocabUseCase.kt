package com.enesay.expolingua.domain.use_case

import com.enesay.expolingua.domain.repository.VocabRepository
import javax.inject.Inject

class GetAllVocabUseCase @Inject constructor(
    private val repository: VocabRepository
) {
    operator fun invoke() = repository.getAllVocab()
} 