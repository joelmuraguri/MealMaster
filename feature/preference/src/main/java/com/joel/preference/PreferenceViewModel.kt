package com.joel.preference

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PreferenceViewModel : ViewModel() {


    private val surveyQuestionOrder : List<PreferenceSurveyQuestion> = listOf(
        PreferenceSurveyQuestion.USER_NAME,
        PreferenceSurveyQuestion.DIET_PREFERENCE,
        PreferenceSurveyQuestion.ALLERGIES_PREFERENCE,
        PreferenceSurveyQuestion.NUTRIENTS_PREFERENCE,
    )



    private val _dietSelectedChips = mutableStateListOf<String>()
    val dietSelectedChips: List<String> get() = _dietSelectedChips

    private val _allergiesSelectedChips = mutableStateListOf<String>()
    val allergiesSelectedChips: List<String> get() = _allergiesSelectedChips

    private val _nutrientsSelectedChips = mutableStateListOf<String>()
    val nutrientsSelectedChips: List<String> get() = _nutrientsSelectedChips

    // Reference to the dietList from FakeDataStore
    val dietList: List<String> = FakeDataStore.dietList
    val allergiesList: List<String> = FakeDataStore.allergiesList
    val nutrientsList: List<String> = FakeDataStore.nutrientsList



    private var questionIndex = 0

    private val _userName = mutableStateOf("")
    val userName : String
        get() = _userName.value


    // Expose Preference-Surveys as state
    private val _surveyScreenData = mutableStateOf(createSurveyScreenData())
    val surveyScreenData: PreferenceSurveyScreenData
        get() = _surveyScreenData.value

    private val _isNextEnabled = mutableStateOf(false)
    val isNextEnabled: Boolean
        get() = _isNextEnabled.value


    fun fetchUserPreference(preferenceUiEvents: PreferenceUiEvents){
        when(preferenceUiEvents){
            is PreferenceUiEvents.MoveNext -> {
                changeQuestion(questionIndex + 1)
            }
            is PreferenceUiEvents.MovePrevious -> {
                if (questionIndex == 0) {
                    throw IllegalStateException("onPreviousPressed when on question 0")
                }
                changeQuestion(questionIndex - 1)
            }
            PreferenceUiEvents.SaveUserPreference -> TODO()
            is PreferenceUiEvents.SelectAllergies -> {
                if (_allergiesSelectedChips.contains(preferenceUiEvents.allergies)){
                    _allergiesSelectedChips.remove(preferenceUiEvents.allergies)
                } else {
                    _allergiesSelectedChips.add(preferenceUiEvents.allergies)
                }
                _isNextEnabled.value = getIsNextEnabled()
            }
            is PreferenceUiEvents.SelectDiet -> {
                if (_dietSelectedChips.contains(preferenceUiEvents.diet)){
                    _dietSelectedChips.remove(preferenceUiEvents.diet)
                } else {
                    _dietSelectedChips.add(preferenceUiEvents.diet)
                }
                _isNextEnabled.value = getIsNextEnabled()
            }
            is PreferenceUiEvents.SelectNutrition -> {
                if (_nutrientsSelectedChips.contains(preferenceUiEvents.nutrition)){
                    _nutrientsSelectedChips.remove(preferenceUiEvents.nutrition)
                } else {
                    _nutrientsSelectedChips.add(preferenceUiEvents.nutrition)
                }
                _isNextEnabled.value = getIsNextEnabled()
            }

            is PreferenceUiEvents.SelectUserName -> {
                _userName.value = preferenceUiEvents.name
                _isNextEnabled.value = getIsNextEnabled()
            }
        }
    }


    private fun changeQuestion(newQuestionIndex : Int){
        questionIndex = newQuestionIndex
        _isNextEnabled.value = getIsNextEnabled()
        _surveyScreenData.value = createSurveyScreenData()

    }

    private fun createSurveyScreenData(): PreferenceSurveyScreenData {
        return PreferenceSurveyScreenData(
            questionIndex = questionIndex,
            questionCount = surveyQuestionOrder.size,
            shouldShowPreviousButton = questionIndex > 0,
            shouldShowDoneButton = questionIndex == surveyQuestionOrder.size - 1,
            surveyQuestion = surveyQuestionOrder[questionIndex],
        )
    }

    private fun getIsNextEnabled(): Boolean {
        return when (surveyQuestionOrder[questionIndex]) {
            PreferenceSurveyQuestion.USER_NAME -> true
            PreferenceSurveyQuestion.DIET_PREFERENCE -> _dietSelectedChips.isNotEmpty()
            PreferenceSurveyQuestion.ALLERGIES_PREFERENCE -> _allergiesSelectedChips.isNotEmpty()
            PreferenceSurveyQuestion.NUTRIENTS_PREFERENCE -> _nutrientsSelectedChips.isNotEmpty()
        }
    }

}

data class PreferenceSurveyScreenData(
    val questionIndex: Int,
    val questionCount: Int,
    val shouldShowPreviousButton: Boolean,
    val shouldShowDoneButton: Boolean,
    val surveyQuestion: PreferenceSurveyQuestion,
)

enum class PreferenceSurveyQuestion{
    USER_NAME,
    DIET_PREFERENCE,
    ALLERGIES_PREFERENCE,
    NUTRIENTS_PREFERENCE,
}

sealed class PreferenceUiEvents{
    data class SelectDiet(val diet : String) : PreferenceUiEvents()
    data class SelectNutrition(val nutrition: String ) : PreferenceUiEvents()
    data class SelectAllergies(val allergies: String) : PreferenceUiEvents()
    data class SelectUserName(val name : String) : PreferenceUiEvents()
    object SaveUserPreference : PreferenceUiEvents()
    object MoveNext : PreferenceUiEvents()
    object MovePrevious : PreferenceUiEvents()
}

//data class PreferenceScreenState(
//    val dietsList : List<String> = emptyList(),
//    val nutritionList : List<String> = emptyList(),
//    val allergiesList : List<String> = emptyList(),
//    val dietSelectionError : String? = null,
//    val nutritionSelectionError : String?= null,
//    val allergiesSelectionError : String? = null,
//    val currentPosition : Int = 0,
//    val previousPosition : Int = 0
//)