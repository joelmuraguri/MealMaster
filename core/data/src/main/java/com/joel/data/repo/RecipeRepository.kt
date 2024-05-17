package com.joel.data.repo

import com.joel.network.dtos.RandomRecipeDTO
import com.joel.network.dtos.RecipeInfoDTO
import com.joel.network.dtos.RecipeInstructionsResponse
import com.joel.network.dtos.SearchRecipeResponse

interface RecipeRepository {

    suspend fun getRandomRecipe() : RandomRecipeDTO

    suspend fun getRandomByCuisinesRecipes(
        cuisine : String
    ) : RandomRecipeDTO

    suspend fun getRandomByMealTypesRecipes(
        mealType : String,
    ) : RandomRecipeDTO

    suspend fun getRandomByDietRecipes(
        diet : String,
    ) : RandomRecipeDTO

    suspend fun getRecipeInfo(
        id : Int,
    ) : RecipeInfoDTO

    suspend fun getRecipeInstructions(
        id : Int,
    ) : RecipeInstructionsResponse

    suspend fun complexSearch(
        apiKey: String,
    ) : SearchRecipeResponse

}