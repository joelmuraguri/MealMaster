package com.joel.network.service

import com.joel.network.dtos.RandomRecipeDTO
import com.joel.network.dtos.RecipeInfoDTO
import com.joel.network.dtos.RecipeInstructionsResponse
import com.joel.network.dtos.SearchRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("/recipes/random")
    suspend fun getRandomRecipes(
        @Query("include-tags") includedTags : String,
        @Query("apiKey") apiKey : String,
        @Query("number") number : Int = 15
    ) : RandomRecipeDTO


    @GET("/recipes/{id}/analyzedInstructions")
    suspend fun getRecipeInstructions(
        @Path("id") id : Int,
        @Query("apiKey") apiKey : String
    ) : RecipeInstructionsResponse

    @GET("/recipes/complexSearch")
    suspend fun complexSearch(
        @Query("query") query : String,
        @Query("apiKey") apiKey : String
    ) : SearchRecipeResponse

//    @GET("recipes/complexSearch")
//    fun searchMultipleRecipes(
//        @Query("apiKey") apiKey : String,
//        @Query("query") query : String,
//        @Query("cuisines") cuisines : List<String>,
//        @Query("types") types : List<String>,
//        @Query("intolerance") intolerance : List<String>,
//        @Query("ingredients") ingredients : List<String>,
//    )

    @GET("/recipes/{id}/information")
    fun fetchRecipeDetails(
        @Path("id") id : Int,
        @Query("apiKey") apiKey : String,
    ) : RecipeInfoDTO

}