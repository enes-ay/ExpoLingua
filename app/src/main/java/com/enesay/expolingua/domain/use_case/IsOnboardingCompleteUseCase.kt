package com.enesay.expolingua.domain.use_case

import com.enesay.expolingua.domain.repository.OnboardingRepository
import javax.inject.Inject

class IsOnboardingCompleteUseCase @Inject constructor (private val repository: OnboardingRepository) {
    suspend operator fun invoke(): Boolean {
        return repository.isOnboardingComplete()
    }
}