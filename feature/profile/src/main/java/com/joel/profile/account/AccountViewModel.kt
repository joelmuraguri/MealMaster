package com.joel.profile.account

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.joel.profile.R
import com.joel.utilities.FakeDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AccountViewModel : ViewModel() {


    private val _dietSelectedChips = mutableStateListOf<String>()
    private val dietSelectedChips: List<String> get() = _dietSelectedChips

    private val _allergiesSelectedChips = mutableStateListOf<String>()
    private val allergiesSelectedChips: List<String> get() = _allergiesSelectedChips

    private val _nutrientsSelectedChips = mutableStateListOf<String>()
    private val nutrientsSelectedChips: List<String> get() = _nutrientsSelectedChips


    // Reference to the dietList from FakeDataStore
    val dietList: List<String> = FakeDataStore.dietList
    val allergiesList: List<String> = FakeDataStore.allergiesList
    val nutrientsList: List<String> = FakeDataStore.nutrientsList

    val preferenceListItems  : Flow<List<UserPreferenceItems>> = flow {
        val preferenceItems = listOf(
            UserPreferenceItems(
                "Manage Diet",
                list = FakeDataStore.dietList,
                selectedAnswers = dietSelectedChips
            ),
            UserPreferenceItems(
                title = "Manage Allergies Preference",
                list = FakeDataStore.allergiesList,
                selectedAnswers = allergiesSelectedChips
            ),
            UserPreferenceItems(
                title = "Manage Nutrients",
                list = FakeDataStore.nutrientsList,
                selectedAnswers = nutrientsSelectedChips
            )
        )
        emit(preferenceItems)
    }

    private val _userName = mutableStateOf("")
    val userName : String
        get() = _userName.value

    val placeHolderImage = mutableStateOf<Any>(R.drawable.round_person_24)
    private val _profileUrl = mutableStateOf<Uri?>(null)
    val profileUrl : Uri
        get() = _profileUrl.value ?: Uri.parse("android.resource://com.joel.mealmaster/drawable/round_person_24")



    fun updateUserDetails(accountUiEvents: AccountUiEvents){
        when(accountUiEvents){
            AccountUiEvents.SaveUserDetails -> TODO()
            is AccountUiEvents.SelectProfileImage -> {
                _profileUrl.value = accountUiEvents.image
            }
            is AccountUiEvents.SelectUserName -> {
                _userName.value = accountUiEvents.name
            }
            is AccountUiEvents.SelectAllergies -> {
                if (_allergiesSelectedChips.contains(accountUiEvents.allergies)){
                    _allergiesSelectedChips.remove(accountUiEvents.allergies)
                } else {
                    _allergiesSelectedChips.add(accountUiEvents.allergies)
                }
            }
            is AccountUiEvents.SelectDiet -> {
                if (_dietSelectedChips.contains(accountUiEvents.diet)){
                    _dietSelectedChips.remove(accountUiEvents.diet)
                } else {
                    _dietSelectedChips.add(accountUiEvents.diet)
                }
            }
            is AccountUiEvents.SelectNutrition -> {
                if (_nutrientsSelectedChips.contains(accountUiEvents.nutrition)){
                    _nutrientsSelectedChips.remove(accountUiEvents.nutrition)
                } else {
                    _nutrientsSelectedChips.add(accountUiEvents.nutrition)
                }
            }
        }
    }
}

sealed class AccountUiEvents {
    object SaveUserDetails : AccountUiEvents()
    data class SelectProfileImage(val image : Uri) : AccountUiEvents()
    data class SelectUserName(val name : String) : AccountUiEvents()

    data class SelectDiet(val diet : String) : AccountUiEvents()
    data class SelectNutrition(val nutrition: String ) : AccountUiEvents()
    data class SelectAllergies(val allergies: String) : AccountUiEvents()
}
