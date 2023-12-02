package com.joel.domain.model

data class Recipe(
    val recipeId : Int,
    val recipeName : String,
    val time : Long,
    val serves : Int,
    val rating : Int,
    val calories : Int
)
