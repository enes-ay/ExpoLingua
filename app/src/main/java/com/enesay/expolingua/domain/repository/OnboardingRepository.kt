package com.enesay.expolingua.domain.repository

interface OnboardingRepository {
    suspend fun setOnboardingComplete(isComplete: Boolean)
    suspend fun isOnboardingComplete(): Boolean
}