package com.joel.network.response

import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchApi(
    val number: Int ? = null,
    val offset: Int ?= null,
    val results: List<RecipeSearch> = emptyList(),
    val totalResults: Int ?= null
) {

    @Serializable
    data class RecipeSearch(
        val id: Int ?= null,
        val image: String ?= null,
        val imageType: String ?= null,
        val title: String ?= null
    )
}