package com.joel.network.dtos

import kotlinx.serialization.Serializable

@Serializable
data class RecipeInfoDTO(
    val aggregateLikes: Int ?= null,
    val analyzedInstructions: List<AnalyzedInstruction> = emptyList(),
    val cheap: Boolean = false,
    val cookingMinutes: Int ?= null,
    val creditsText: String ?= null,
    val cuisines: List<String> ?= emptyList(),
    val dairyFree: Boolean = false,
    val diets: List<String> = emptyList(),
    val dishTypes: List<String> = emptyList(),
    val extendedIngredients: List<ExtendedIngredient> = emptyList(),
    val gaps: String ?= null,
    val glutenFree: Boolean = false,
    val healthScore: Int ?= null,
    val id: Int ?= null,
    val image: String ?= null,
    val imageType: String ?= null,
    val instructions: String ?= null,
    val license: String ?= null,
    val lowFodmap: Boolean = false,
    val occasions: List<String> = emptyList(), //ANY
    val originalId: String ?= null, //ANY
    val preparationMinutes: Int ?= null,
    val pricePerServing: Double ?= null,
    val readyInMinutes: Int ?= null,
    val servings: Int ?= null,
    val sourceName: String ?= null,
    val sourceUrl: String ?= null,
    val spoonacularScore: Double ?= null,
    val spoonacularSourceUrl: String ?= null,
    val summary: String ?= null,
    val sustainable: Boolean = false,
    val taste: Taste ?= null,
    val title: String ?= null,
    val vegan: Boolean = false,
    val vegetarian: Boolean = false,
    val veryHealthy: Boolean = false,
    val veryPopular: Boolean = false,
    val weightWatcherSmartPoints: Int ?= null,
    val winePairing: WinePairing ?= null
) {

    @Serializable
    data class WinePairing(
        val pairedWines: List<String> = emptyList(),  //ANY
        val pairingText: String ?= null,
        val productMatches: List<String> = emptyList()  //ANY
    )

    @Serializable
    data class Taste(
        val bitterness: Int ?= null,
        val fattiness: Double ?= null,
        val saltiness: Double ?= null,
        val savoriness: Double ?= null,
        val sourness: Double ?= null,
        val spiciness: Int ?=null,
        val sweetness: Double ?= null
    )

    @Serializable
    data class AnalyzedInstruction(
        val name: String ?= null,
        val steps: List<Step> = emptyList()
    ) {


        @Serializable
        data class Step(
            val equipment: List<Equipment> = emptyList(),
            val ingredients: List<Ingredient> = emptyList(),
            val length: Length ?= null,
            val number: Int ?= null,
            val step: String ?= null
        ) {

            @Serializable
            data class Equipment(
                val id: Int ?= null,
                val image: String ?= null,
                val localizedName: String ?= null,
                val name: String ?= null
            )

            @Serializable
            data class Ingredient(
                val id: Int ?= null,
                val image: String ?= null,
                val localizedName: String ?= null,
                val name: String ?= null
            )

            @Serializable
            data class Length(
                val number: Int ?= null,
                val unit: String ?= null
            )

        }
    }

    @Serializable
    data class ExtendedIngredient(
        val aisle: String ?= null,
        val amount: Double ?= null,
        val consistency: String ?= null,
        val id: Int ?= null,
        val image: String ?= null,
        val measures: Measures ?= null,
        val meta: List<String> = emptyList(),
        val name: String ?= null,
        val nameClean: String ?= null,
        val original: String ?= null,
        val originalName: String ?= null,
        val unit: String ?= null
    ){

        @Serializable
        data class Measures(
            val metric: Metric ?= null,
            val us: Us ?= null
        ){

            @Serializable
            data class Metric(
                val amount: Double ?= null,
                val unitLong: String ?= null,
                val unitShort: String ?= null
            )


            @Serializable
            data class Us(
                val amount: Double ?= null,
                val unitLong: String ?= null,
                val unitShort: String ?= null
            )


        }
    }
}