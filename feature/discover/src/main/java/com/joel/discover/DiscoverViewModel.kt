package com.joel.discover

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.domain.use_case.RecipeUseCase
import com.joel.domain.utils.Resource
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class DiscoverViewModel(
    private val useCase: RecipeUseCase
) : ViewModel() {


    private val _state = mutableStateOf(DiscoverState())
    val state : State<DiscoverState> = _state

    init {
        fetchRandomRecipe()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun fetchRandomRecipe(){
        viewModelScope.launch {
            useCase.getRandomRecipeUseCase.invoke().collect{ resource ->
                when(resource){
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = resource.message ?:  "An Unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            randomRecipe = resource.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}