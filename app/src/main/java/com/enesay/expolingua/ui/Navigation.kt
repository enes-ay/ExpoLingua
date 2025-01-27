package com.enesay.expolingua.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.enesay.expolingua.VocabScreen
import com.enesay.expolingua.ui.presentation.Onboarding.OnboardingScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("onboarding") {
            OnboardingScreen(
                onFinish = { navController.navigate("main") { popUpTo("onboarding") { inclusive = true } } }
            )
        }
        composable("main") {
            VocabScreen()
        }
    }
}
