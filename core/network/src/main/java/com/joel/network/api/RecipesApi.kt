package com.joel.network.api

import com.joel.network.response.AnalyzedInstructionsApi
import com.joel.network.response.IngredientsSearchApi
import com.joel.network.response.RandomRecipeApi
import com.joel.network.response.RecipeInfoApi
import com.joel.network.response.RecipeSearchApi
import com.joel.network.response.SimilarRecipeApi

interface RecipesApi {

    suspend fun fetchRecipeInfo(recipeId : Int) : RecipeInfoApi

    suspend fun fetchRandomRecipes(
        number : Int = 20,
        tags : List<String>,
    ) : RandomRecipeApi

    suspend fun searchRecipes(
        diet : List<String>,
        allergies : List<String>, // intolerance
        query: String,
    ) : RecipeSearchApi

    suspend fun fetchAnalyzedRecipeInstructions(recipeId: Int) : AnalyzedInstructionsApi

    suspend fun fetchSimilarRecipes(recipeId: Int) : SimilarRecipeApi

    suspend fun fetchIngredients(query : String) : IngredientsSearchApi

}
