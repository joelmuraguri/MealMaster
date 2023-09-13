package com.joel.profile.account

import com.joel.utilities.FakeDataStore

data class UserPreferenceItems(
    val title : String,
    val list : List<String>,
    val selectedAnswers : List<String> = emptyList()
)

val preferenceList = listOf(
    UserPreferenceItems(
        title = "Manage Diet",
        list = FakeDataStore.dietList,

    ),
    UserPreferenceItems(
        title = "Manage Allergies Preference",
        list = FakeDataStore.allergiesList,


    ),
    UserPreferenceItems(
        title = "Manage Nutrients",
        list = FakeDataStore.nutrientsList
    ),
)
