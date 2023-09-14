package com.joel.network.response

class RecipeSearch : ArrayList<RecipeSearchItem>()

data class RecipeSearchItem(
    val id: Int,
    val imageType: String,
    val title: String
)