package com.joel.preference

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import com.joel.preference.components.AllergiesQuestion
import com.joel.preference.components.DietQuestion
import com.joel.preference.components.NutrientsQuestion
import com.joel.preference.components.PreferenceBottomBar
import com.joel.preference.components.PreferenceTopBar
import com.joel.preference.components.UserNameQuestion

private const val CONTENT_ANIMATION_DURATION = 300

@Composable
fun PreferenceScreen(
    viewModel: PreferenceViewModel = hiltViewModel(),
    onFinishPressed: () -> Unit
){

    val preferenceSurveyScreenData = viewModel.surveyScreenData
    val prefStatus by viewModel.preferenceOnboardingCompleted


    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event ->
            when(event){
                is UIevent.NavigationToHome -> {
                    onFinishPressed()
                }
            }
        }
    }

    PreferenceSurvey(
        surveyScreenData = preferenceSurveyScreenData,
        isNextEnabled = viewModel.isNextEnabled,
        onClosePressed = {

        },
        onPreviousPressed = {
              viewModel.onEvents(PreferenceUiEvents.MovePrevious)
        },
        onNextPressed = {
               viewModel.onEvents(PreferenceUiEvents.MoveNext)
        },
        onFinishPressed = {
            if (viewModel.preferenceOnboardingCompleted.value)
            viewModel.onEvents(PreferenceUiEvents.SaveUserPreference)
        })
    { paddingValues ->

        val modifier = Modifier.padding(paddingValues)
        AnimatedContent(
            targetState = preferenceSurveyScreenData,
            label = "",
            transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> =
                    tween(CONTENT_ANIMATION_DURATION)
                val direction = getTransitionDirection(
                    initialIndex = initialState.questionIndex,
                    targetIndex = targetState.questionIndex,
                )
                slideIntoContainer(
                    towards = direction,
                    animationSpec = animationSpec,
                ) togetherWith  slideOutOfContainer(
                    towards = direction,
                    animationSpec = animationSpec
                )
            }) { targetState ->
            when(targetState.surveyQuestion){
                PreferenceSurveyQuestion.USER_NAME -> {
                    UserNameQuestion(
                        userName = viewModel.userName,
                        onNameChange = {
                            viewModel.onEvents(PreferenceUiEvents.SelectUserName(it))
                        },
                        modifier = modifier,
                        )
                }

                PreferenceSurveyQuestion.DIET_PREFERENCE -> {
                    DietQuestion(
                        selectedAnswers = viewModel.dietSelectedChips,
                        modifier = modifier,
                        onOptionSelected = { diet ->
                               viewModel.onEvents(PreferenceUiEvents.SelectDiet(diet))
                        },
                        possibleAnswers = viewModel.dietList
                    )
                }

                PreferenceSurveyQuestion.ALLERGIES_PREFERENCE -> {
                    AllergiesQuestion(
                        selectedAnswers = viewModel.allergiesSelectedChips,
                        onOptionSelected = { allergies ->
                              viewModel.onEvents(PreferenceUiEvents.SelectAllergies(allergies))
                        },
                        modifier = modifier,
                        possibleAnswers = viewModel.allergiesList
                    )
                }

                PreferenceSurveyQuestion.NUTRIENTS_PREFERENCE -> {
                    NutrientsQuestion(
                        selectedAnswers = viewModel.nutrientsSelectedChips,
                        onOptionSelected = { nutrients ->
                              viewModel.onEvents(PreferenceUiEvents.SelectNutrition(nutrients))
                        },
                        modifier = modifier,
                        possibleAnswers = viewModel.nutrientsList
                    )
                }
            }
        }
    }
}

@Composable
fun PreferenceSurvey(
    surveyScreenData: PreferenceSurveyScreenData,
    isNextEnabled: Boolean,
    onClosePressed: () -> Unit,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit,
    onFinishPressed: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
){

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                PreferenceTopBar(
                    questionIndex = surveyScreenData.questionIndex,
                    totalQuestions = surveyScreenData.questionCount,
                    onClosePressed = onClosePressed,
                )
            },
            content = content,
            bottomBar = {
                PreferenceBottomBar(
                    showPreviousButton = surveyScreenData.shouldShowPreviousButton,
                    showFinishButton = surveyScreenData.shouldShowDoneButton,
                    isNextButtonEnabled = isNextEnabled,
                    onPreviousPressed = onPreviousPressed,
                    onNextPressed = onNextPressed,
                    onFinishPressed = onFinishPressed
                )
            }
        )
    }
}


private fun getTransitionDirection(
    initialIndex: Int,
    targetIndex: Int
): AnimatedContentTransitionScope.SlideDirection {
    return if (targetIndex > initialIndex) {
        // Going forwards in the survey: Set the initial offset to start
        // at the size of the content so it slides in from right to left, and
        // slides out from the left of the screen to -fullWidth
        AnimatedContentTransitionScope.SlideDirection.Start
    } else {
        // Going back to the previous question in the set, we do the same
        // transition as above, but with different offsets - the inverse of
        // above, negative fullWidth to enter, and fullWidth to exit.
        AnimatedContentTransitionScope.SlideDirection.End
    }
}