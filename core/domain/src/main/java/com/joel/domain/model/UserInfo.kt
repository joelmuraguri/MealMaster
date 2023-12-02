package com.joel.domain.model

import android.net.Uri

data class UserInfo(
    val name : String,
    val image : Uri,
    val diets : List<String>,
    val allergies : List<String>,
    val nutrients : List<String>
)
