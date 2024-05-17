package com.joel.domain.model

data class Recipe(
    val recipeId : Int,
    val recipeName : String,
    val time : Int,
    val serves : Int,
    val rating : Int,
    val image : String,
    val likes : Int
)
