package com.joel.domain.model

data class RecipeInfo(
    val id : Int,
    val name : String,
    val image : String,
    val calories : Int,
    val timeToPrepare  : Int,
    val instructions : List<Instructions>
){

    data class Instructions(
        val steps : List<Step>
    ){

        data class Step(
            val equipment: List<Equipment> = emptyList(),
            val ingredients: List<Ingredient> = emptyList(),
            val length: Length,
            val number: Int,
            val step: String
        ){

            data class Equipment(
                val id: Int,
                val image: String,
                val name: String
            )

            data class Ingredient(
                val id: Int,
                val image: String,
                val name: String
            )

            data class Length(
                val number: Int, //Minutes
            )
        }
    }
}
