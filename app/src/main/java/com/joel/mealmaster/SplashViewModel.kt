package com.joel.mealmaster

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.data.repo.DataStoreRepository
import com.joel.mealmaster.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository : DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screens.Preference.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readPreferenceState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screens.Home.route
                } else {
                    _startDestination.value = Screens.Preference.route
                }
            }
            _isLoading.value = false
        }
    }
}