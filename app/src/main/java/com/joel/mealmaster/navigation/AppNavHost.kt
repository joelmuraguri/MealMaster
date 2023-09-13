package com.joel.mealmaster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joel.discover.DiscoverScreen
import com.joel.mealplan.MealPlanScreen
import com.joel.preference.PreferenceScreen
import com.joel.profile.ProfileScreen
import com.joel.profile.account.AccountsScreen
import com.joel.profile.favourites.FavouritesScreen
import com.joel.profile.nutrition_tracking.NutritionTrackingScreen
import com.joel.profile.settings.SettingsScreen
import com.joel.recipes.RecipeScreen

@Composable
fun MealMasterNavHost(
    navController: NavHostController,
    updateBottomBarState: (Boolean) -> Unit
){


    NavHost(
        navController = navController, startDestination = Screens.Preference.route
    ){
        composable(route = Screens.Preference.route){
            updateBottomBarState(false)
            PreferenceScreen(
                onFinishPressed = {
                    navController.navigate(Screens.Home.route)
                }
            )
        }
        composable(route = Screens.Home.route){
            updateBottomBarState(true)
            DiscoverScreen()
        }
        composable(route = Screens.MealPlan.route){
            updateBottomBarState(true)
            MealPlanScreen()
        }
        composable(route = Screens.Recipes.route){
            updateBottomBarState(true)
            RecipeScreen()
        }
        composable(route = Screens.Profile.route){
            updateBottomBarState(true)
            ProfileScreen {
                navController.navigate(route = it.route)
            }
        }
        composable(route = Screens.UserPreference.route){
            updateBottomBarState(false)
            AccountsScreen()
        }
        composable(route = Screens.NutritionTracking.route){
            updateBottomBarState(false)
            NutritionTrackingScreen()
        }
        composable(route = Screens.FavouriteRecipes.route){
            updateBottomBarState(false)
            FavouritesScreen()
        }
        composable(route = Screens.Settings.route){
            updateBottomBarState(false)
            SettingsScreen()
        }
    }
}

