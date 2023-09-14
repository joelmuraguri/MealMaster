package com.joel.network

import com.joel.network.response.RecipeInformation
import com.joel.network.response.RecipeSearch

class RecipesApi {


    fun getRecipes(
        diet : List<String>,
        allergies : List<String>,
        apiKey : String,
        number : Int
    ){}

    fun recipeSearch(
        query : String
    ) : RecipeSearch{}

    fun getRecipeAnalyzedInstructions(
        stepBreakDown : Boolean = true,
        id : Int
    ){}

    fun getRecipeInformation(
        id : Int,
        includedNutriotion : Boolean
    ) : RecipeInformation{}

    fun getSimilarRecipes(id : Int){}

    fun findRandomRecipes() : RecipeInformation{}
}