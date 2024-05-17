package com.joel.network.source

import com.joel.network.dtos.RandomRecipeDTO
import com.joel.network.dtos.RecipeInfoDTO
import com.joel.network.dtos.RecipeInstructionsResponse
import com.joel.network.dtos.SearchRecipeResponse

interface RecipeRemoteSource {

    suspend fun getRandomRecipes(
        includedTags : String,
    ) : RandomRecipeDTO

    suspend fun getRecipeInfo(
        id : Int,
    ) : RecipeInfoDTO


    suspend fun getRecipeInstructions(
        id : Int,
    ) : RecipeInstructionsResponse

    suspend fun complexSearch(
        query : String
    ) : SearchRecipeResponse

}