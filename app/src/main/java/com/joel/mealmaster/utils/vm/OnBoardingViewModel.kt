package com.joel.mealmaster.utils.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.data.repo.DataStoreRepository
import com.joel.mealmaster.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screens.Preference.route)
    val startDestination: State<String> = _startDestination

    private val _uiEvent =  Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var _onboardingCompleted : MutableState<Boolean> = mutableStateOf(false)
    val onboardingCompleted: State<Boolean> = _onboardingCompleted

    init {
        viewModelScope.launch {
            repository.readPreferenceState().collect { completed ->
                _onboardingCompleted.value = completed
                if (completed) {
                    _startDestination.value = Screens.Preference.route
                } else {
                    _startDestination.value = Screens.Onboarding.route
                }
            }
            _isLoading.value = false
        }
    }


    fun saveUserOnBoardingStatus(){
        viewModelScope.launch {
            repository.saveOnboardingState(completed = true)
            _uiEvent.send(UIEvent.NavToPref)
        }
    }
}

sealed class UIEvent(){
    data object NavToPref : UIEvent()
}
