package com.joel.network.api.impl

import com.joel.network.api.RecipesApi
import com.joel.network.response.AnalyzedInstructionsApi
import com.joel.network.response.IngredientsSearchApi
import com.joel.network.response.RandomRecipeApi
import com.joel.network.response.RecipeInfoApi
import com.joel.network.response.RecipeSearchApi
import com.joel.network.response.SimilarRecipeApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RecipesApiImpl(
    private val client: HttpClient,
    private val apiKey: String
) : RecipesApi {

    override suspend fun fetchRecipeInfo(recipeId: Int): RecipeInfoApi =
        client.get("https://api.spoonacular.com/recipes/$recipeId/information"){
            parameter("apiKey", apiKey)
            parameter("id", recipeId)
        }.body()

    override suspend fun fetchRandomRecipes(
        number: Int,
        tags: List<String>,
    ): RandomRecipeApi =
        client.get("https://api.spoonacular.com/recipes/random"){
            parameter("apiKey", apiKey)
            parameter("number", number)
            parameter("tags", tags)
        }.body()

    override suspend fun searchRecipes(
        diet: List<String>,
        allergies: List<String>,
        query: String,
    ): RecipeSearchApi =
        client.get("https://api.spoonacular.com/recipes/complexSearch"){
            parameter("apiKey", apiKey)
            parameter("query", query)
            parameter("diet", diet)
            parameter("intolerances", allergies)
        }.body()

    override suspend fun fetchAnalyzedRecipeInstructions(
        recipeId: Int,
    ): AnalyzedInstructionsApi =
        client.get("https://api.spoonacular.com/recipes/$recipeId/analyzedInstructions"){
            parameter("apiKey", apiKey)
            parameter("id", recipeId)
        }.body()

    override suspend fun fetchSimilarRecipes(recipeId: Int): SimilarRecipeApi =
        client.get("https://api.spoonacular.com/recipes/$recipeId/similar"){
            parameter("apiKey", apiKey)
            parameter("id", recipeId)
        }.body()

    override suspend fun fetchIngredients(query: String): IngredientsSearchApi =
        client.get("https://api.spoonacular.com/food/ingredients/search"){
            parameter("apiKey", apiKey)
            parameter("query", query)
        }.body()
}