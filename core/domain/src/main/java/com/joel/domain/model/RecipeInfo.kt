package com.joel.domain.model

data class RecipeInfo(
    val id : Int,
    val name : String,
    val image : String,
    val timeToPrepare  : Int,
    val likes : Int,
    val instructions : List<Instructions>,
    val nutrients :  List<Nutrients>,
    val ingredients : List<Ingredient>
){

    data class Ingredient(
        val id : Int,
        val name : String,
        val nameDeck : String,
        val amount: Int,
        val unit : String,
        val image : String
    )
    data class Nutrients(
        val name : String,
        val amount: Int,
        val unit : String
    )
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
                val number: Int,
                val units : String
            )
        }
    }
}
