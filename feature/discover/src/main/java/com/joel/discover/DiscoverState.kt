package com.joel.discover

import com.joel.domain.model.Recipe

data class DiscoverState(
    val randomRecipe : List<Recipe> = emptyList(),
    val isLoading : Boolean = false,
    val error : String = ""
)
