package com.joel.network.source

import com.joel.network.dtos.RandomRecipeDTO
import com.joel.network.dtos.RecipeInfoDTO
import com.joel.network.dtos.RecipeInstructionsResponse
import com.joel.network.dtos.SearchRecipeResponse
import com.joel.network.service.RecipeService

class DefaultRecipeRemoteSource(
    private val recipeService: RecipeService,
    private val apiKey : String
) : RecipeRemoteSource {

    override suspend fun getRandomRecipes(includedTags: String): RandomRecipeDTO {
        return recipeService.getRandomRecipes(includedTags, apiKey)
    }

    override suspend fun getRecipeInfo(id: Int): RecipeInfoDTO {
        return recipeService.fetchRecipeDetails(id, apiKey)
    }

    override suspend fun getRecipeInstructions(id: Int): RecipeInstructionsResponse {
        return recipeService.getRecipeInstructions(id, apiKey)
    }

    override suspend fun complexSearch(query: String): SearchRecipeResponse {
        return recipeService.complexSearch(query, apiKey)
    }
}