package com.enesay.expolingua.domain.use_case

import com.enesay.expolingua.domain.repository.OnboardingRepository
import javax.inject.Inject

class SetOnboardingCompleteUseCase @Inject constructor (private val repository: OnboardingRepository) {
    suspend operator fun invoke(isComplete: Boolean) {
        repository.setOnboardingComplete(isComplete)
    }
}