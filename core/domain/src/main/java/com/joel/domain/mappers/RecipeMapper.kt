package com.joel.domain.mappers

import com.joel.domain.model.Recipe
import com.joel.domain.utils.roundToInt
import com.joel.network.dtos.RandomRecipeDTO

fun RandomRecipeDTO.Recipe.toRecipeDomainModel() : Recipe{
    return Recipe(
        recipeId = id ?: 0,
        recipeName = title.orEmpty(),
        time = readyInMinutes ?: 0,
        likes = aggregateLikes ?: 0,
        image = image.orEmpty(),
        rating = spoonacularScore?.roundToInt() ?: 0,
        serves = servings ?: 0
    )
}