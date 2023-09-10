package com.joel.mealmaster.navigation

import androidx.annotation.DrawableRes
import com.joel.mealmaster.R

sealed class Screens(var route: String, @DrawableRes var icon: Int, var title: Int) {

    object Preference : Screens(
        route = "/preference",
        icon = R.drawable.round_directions_24,
        title = R.string.preference_title)
    object Home : Screens(route = "/home", icon = R.drawable.round_home_24, title = R.string.home_bottom_bar_title)
    object MealPlan : Screens(route = "/mealplan", icon = R.drawable.round_meal_plan_24, title = R.string.meal_plan_bottom_bar_title)
    object Recipes : Screens(route = "/recipes", icon = R.drawable.round_recipes_24, title = R.string.recipe_bottom_bar_title)
    object RecipeDetails : Screens(
        route = "/recipeDetails/{recipeId}",
        icon = R.drawable.round_directions_24,
        title = R.string.recipe_details_title
    ) { const val recipeIdNavigationArgument = "recipeId" }
    object Profile : Screens(route = "/profile", icon = R.drawable.round_person_24, title = R.string.profile_bottom_bar_title)
    object NutritionTracking : Screens(
        route = "/nutritionTRacking",
        title = R.string.nutrition_tracking_title,
        icon = R.drawable.round_directions_24
        )
    object DietPreference : Screens(
        route = "/dietPreference",
        title = R.string.diet_preference_title,
        icon = R.drawable.round_directions_24
        )
    object FavouriteRecipes : Screens(
        route = "/favourites",
        title = R.string.favourites_title,
        icon = R.drawable.round_directions_24
        )
    object Settings : Screens(
        route = "/settings",
        title = R.string.settings_title,
        icon = R.drawable.round_directions_24
        )
}


val bottomBarNavigationList = listOf(
    Screens.Home,
    Screens.MealPlan,
    Screens.Recipes,
    Screens.Profile
)