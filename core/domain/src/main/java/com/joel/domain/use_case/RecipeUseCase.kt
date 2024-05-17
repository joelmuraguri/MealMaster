package com.joel.domain.use_case

import com.joel.domain.use_case.recipe.GetRandomRecipeUseCase
import com.joel.domain.use_case.recipe.GetRecipeInfoUseCase

data class RecipeUseCase(
    val getRandomRecipeUseCase: GetRandomRecipeUseCase,
    val getRecipeInfoUseCase: GetRecipeInfoUseCase
)
