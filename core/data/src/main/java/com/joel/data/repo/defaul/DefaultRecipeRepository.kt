package com.joel.data.repo.defaul

import com.joel.data.repo.RecipeRepository
import com.joel.network.dtos.RandomRecipeDTO
import com.joel.network.dtos.RecipeInfoDTO
import com.joel.network.dtos.RecipeInstructionsResponse
import com.joel.network.dtos.SearchRecipeResponse
import com.joel.network.source.RecipeRemoteSource

class DefaultRecipeRepository(
    private val remoteSource: RecipeRemoteSource
) : RecipeRepository {

    override suspend fun getRandomRecipe(): RandomRecipeDTO {
        return remoteSource.getRandomRecipes("")
    }

    override suspend fun getRandomByCuisinesRecipes(cuisine: String): RandomRecipeDTO {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomByMealTypesRecipes(mealType: String): RandomRecipeDTO {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomByDietRecipes(diet: String): RandomRecipeDTO {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeInfo(id: Int): RecipeInfoDTO {
        return remoteSource.getRecipeInfo(id)
    }

    override suspend fun getRecipeInstructions(id: Int): RecipeInstructionsResponse {
        return remoteSource.getRecipeInstructions(id)
    }

    override suspend fun complexSearch(apiKey: String): SearchRecipeResponse {
        TODO("Not yet implemented")
    }
}