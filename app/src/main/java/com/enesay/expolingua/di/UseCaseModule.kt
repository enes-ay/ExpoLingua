package com.enesay.expolingua.di


import com.enesay.expolingua.domain.use_case.IsOnboardingCompleteUseCase
import com.enesay.expolingua.domain.repository.OnboardingRepository
import com.enesay.expolingua.domain.use_case.SetOnboardingCompleteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) // UseCase'leri ViewModel seviyesinde sağlıyoruz
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSetOnboardingCompleteUseCase(
        repository: OnboardingRepository
    ): SetOnboardingCompleteUseCase {
        return SetOnboardingCompleteUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideIsOnboardingCompleteUseCase(
        repository: OnboardingRepository
    ): IsOnboardingCompleteUseCase {
        return IsOnboardingCompleteUseCase(repository)
    }
}