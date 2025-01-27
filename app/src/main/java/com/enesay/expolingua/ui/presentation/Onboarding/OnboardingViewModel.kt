package com.enesay.expolingua.ui.presentation.Onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesay.expolingua.domain.use_case.IsOnboardingCompleteUseCase
import com.enesay.expolingua.domain.use_case.SetOnboardingCompleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setOnboardingCompleteUseCase: SetOnboardingCompleteUseCase,
    private val isOnboardingCompleteUseCase: IsOnboardingCompleteUseCase
) : ViewModel() {

    private val _isOnboardingComplete = MutableStateFlow(false)
    val isOnboardingComplete: StateFlow<Boolean> get() = _isOnboardingComplete

    init {
        viewModelScope.launch {
            _isOnboardingComplete.value = isOnboardingCompleteUseCase()
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            setOnboardingCompleteUseCase(true)
            _isOnboardingComplete.value = true
        }
    }
}
