package com.joel.mealmaster.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joel.discover.DiscoverScreen
import com.joel.mealplan.MealPlanScreen
import com.joel.mealmaster.utils.vm.OnBoardingScreen
import com.joel.mealmaster.utils.vm.OnBoardingViewModel
import com.joel.preference.PreferenceScreen
import com.joel.preference.PreferenceViewModel
import com.joel.profile.ProfileScreen
import com.joel.profile.favourites.FavouritesScreen
import com.joel.recipes.RecipeScreen

@Composable
fun MealMasterNavHost(
    navController: NavHostController,
    updateBottomBarState: (Boolean) -> Unit,
    updateFABState: (Boolean) -> Unit,
    startDestination : String,
    preferenceViewModel : PreferenceViewModel = hiltViewModel(),
    onboardingViewModel: OnBoardingViewModel = hiltViewModel()
){

    val prefStatus by preferenceViewModel.preferenceOnboardingCompleted
    val onboardingStatus by onboardingViewModel.onboardingCompleted

    LaunchedEffect(Unit) {
        if (prefStatus && onboardingStatus) {
            navController.navigate(Screens.Explore.route) {
                popUpTo(Screens.Preference.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(
                if (!onboardingStatus) {
                    Screens.Onboarding.route
                } else {
                    Screens.Preference.route
                }
            )
        }
    }

    NavHost(
        navController = navController, startDestination = startDestination
    ){
        composable(route = Screens.Onboarding.route){
            updateFABState(false)
            updateBottomBarState(false)
            OnBoardingScreen(
                onNavPressed = {
                    navController.navigate(Screens.Preference.route)
                }
            )
        }
        composable(route = Screens.Preference.route){
            updateFABState(false)
            updateBottomBarState(false)
            PreferenceScreen(
                onFinishPressed = {
                    navController.navigate(Screens.Explore.route)
                }
            )
        }
        composable(route = Screens.Explore.route){
            updateFABState(true)
            updateBottomBarState(true)
            DiscoverScreen()
        }
        composable(route = Screens.MealPlan.route){
            updateFABState(false)
            updateBottomBarState(false)
            MealPlanScreen()
        }
        composable(route = Screens.Search.route){
            updateFABState(true)
            updateBottomBarState(true)
            RecipeScreen()
        }
        composable(route = Screens.Profile.route){
            updateFABState(true)
            updateBottomBarState(true)
            ProfileScreen {
                navController.navigate(route = it.route)
            }
        }
        composable(route = Screens.Favourites.route){
            updateFABState(true)
            updateBottomBarState(true)
            FavouritesScreen()
        }
    }
}

