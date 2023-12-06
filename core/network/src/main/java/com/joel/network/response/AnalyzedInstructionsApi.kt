package com.joel.network.response

import kotlinx.serialization.Serializable

@Serializable
class AnalyzedInstructionsApi : ArrayList<AnalyzedInstructionsApiItem>()

@Serializable
data class AnalyzedInstructionsApiItem(
        val name: String ? = null,
        val steps: List<Step>
) {

    @Serializable
    data class Step(
        val equipment: List<Equipment>,
        val ingredients: List<Ingredient>,
        val length: Length ?= null,
        val number: Int ?= null,
        val step: String ?= null
    ){

        @Serializable
        data class Equipment(
            val id: Int ?= null,
            val image: String ?= null,
            val localizedName: String ?= null,
            val name: String ?= null,
            val temperature: Temperature
        ) {
            @Serializable
            data class Temperature(
                val number: Double ?= null,
                val unit: String ?= null
            )
        }

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

