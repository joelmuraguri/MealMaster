package com.joel.network.response

import kotlinx.serialization.Serializable

@Serializable
class SimilarRecipeApi : ArrayList<SimilarRecipeApiItem>()

@Serializable
data class SimilarRecipeApiItem(
    val id: Int ? = null,
    val imageType: String ? = null,
    val readyInMinutes: Int ?= null,
    val servings: Int ?= null,
    val sourceUrl: String ? = null,
    val title: String ?= null
)


