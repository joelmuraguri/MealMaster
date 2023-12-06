package com.joel.network.response

import kotlinx.serialization.Serializable

@Serializable
data class IngredientsSearchApi(
    val number: Int ? = null,
    val offset: Int ? = null,
    val results: List<IngredientsList>,
    val totalResults: Int ? = null
) {

    @Serializable
    data class IngredientsList(
        val id: Int ?= null,
        val image: String ?= null,
        val name: String ?= null
    )

}