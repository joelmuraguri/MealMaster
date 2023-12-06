package com.joel.network.response

import kotlinx.serialization.Serializable

@Serializable
data class RandomRecipeApi(
    val recipes: List<Recipe> = emptyList()
) {

    @Serializable
    data class Recipe(
        val aggregateLikes: Int ,
        val analyzedInstructions: List<AnalyzedInstruction>,
        val cheap: Boolean,
        val cookingMinutes: Int,
        val creditsText: String,
        val cuisines: List<String>,
        val dairyFree: Boolean,
        val diets: List<String>,
        val dishTypes: List<String>,
        val extendedIngredients: List<ExtendedIngredient>,
        val gaps: String,
        val glutenFree: Boolean,
        val healthScore: Int,
        val id: Int,
        val image: String,
        val imageType: String,
        val instructions: String,
        val license: String,
        val lowFodmap: Boolean,
        val occasions: List<String>,
        val originalId: String ?= null,
        val preparationMinutes: Int,
        val pricePerServing: Double,
        val readyInMinutes: Int,
        val servings: Int,
        val sourceName: String,
        val sourceUrl: String,
        val spoonacularScore: Double,
        val spoonacularSourceUrl: String,
        val summary: String,
        val sustainable: Boolean,
        val title: String,
        val vegan: Boolean,
        val vegetarian: Boolean,
        val veryHealthy: Boolean,
        val veryPopular: Boolean,
        val weightWatcherSmartPoints: Int
    ) {

        @Serializable
        data class AnalyzedInstruction(
            val name: String,
            val steps: List<Step>
        ) {
            @Serializable
            data class Step(
                val equipment: List<Equipment>,
                val ingredients: List<Ingredient>,
                val length: Length,
                val number: Int,
                val step: String
            ){

                @Serializable
                data class Length(
                    val number: Int,
                    val unit: String
                )

                @Serializable
                data class Equipment(
                    val id: Int,
                    val image: String,
                    val localizedName: String,
                    val name: String,
                    val temperature: Temperature
                ) {
                    @Serializable
                    data class Temperature(
                        val number: Double,
                        val unit: String
                    )
                }

                @Serializable
                data class Ingredient(
                    val id: Int,
                    val image: String,
                    val localizedName: String,
                    val name: String
                )

            }
        }

        @Serializable
        data class ExtendedIngredient(
            val aisle: String,
            val amount: Double,
            val consistency: String,
            val id: Int,
            val image: String,
            val measures: Measures,
            val meta: List<String>,
            val name: String,
            val nameClean: String,
            val original: String,
            val originalName: String,
            val unit: String
        ) {

            @Serializable
            data class Measures(
                val metric: Metric,
                val us: Us
            ) {

                @Serializable
                data class Metric(
                    val amount: Double,
                    val unitLong: String,
                    val unitShort: String
                )

                @Serializable
                data class Us(
                    val amount: Double,
                    val unitLong: String,
                    val unitShort: String
                )
            }

        }
    }
}