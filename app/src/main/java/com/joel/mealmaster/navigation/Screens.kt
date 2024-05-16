package com.joel.mealmaster.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.res.stringResource
import com.joel.mealmaster.R

const val CHARACTER_ARGUMENT_KEY = "characterId"
const val ISSUE_ARGUMENT_KEY = "issueId"

sealed class Screens(val route: String, val icon: Int ?= null, val title: String) {

    data object Onboarding : Screens(
        route = "onboarding_route",
        icon = null,
        title =  ""
    )
    data object Preference : Screens(
        route = "preference_route",
        icon = null,
        title =  ""
    )
    data object Search : Screens(
        route = "search_route",
        icon = R.drawable.round_search_24,
        title =  ""
    )
    data object MealPlan : Screens(
        route = "meal_plan_route",
        icon = R.drawable.round_add_24,
        title =  ""
    )
    data object Settings : Screens(
        route = "settings_route",
        icon = R.drawable.round_settings_24,
        title = ""
    )
    data object Explore : Screens(
        route = "explore_route",
        icon = R.drawable.round_explore_24,
        title = ""
    )
    data object Favourites : Screens(
        route = "favourites_route",
        icon = R.drawable.round_favorite_border_24,
        title = ""
    )
    data object Profile : Screens(
        route = "profile_route",
        icon = R.drawable.round_person_24,
        title = ""
    )
}


val bottomBarNavigationList = listOf(
    Screens.Explore,
    Screens.Search,
    Screens.Favourites,
    Screens.Profile,
)