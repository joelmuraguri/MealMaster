package com.joel.domain.model

sealed class Intolerances(val name : String) {

    data object Dairy : Intolerances("Dairy")
    data object Egg : Intolerances("Egg")
    data object Gluten : Intolerances("Gluten")
    data object Grain : Intolerances("Grain")
    data object Peanut : Intolerances("Peanut")
    data object Seafood : Intolerances("Seafood")
    data object Sesame : Intolerances("Sesame")
    data object Shellfish : Intolerances("Shellfish")
    data object Soy : Intolerances("Soy")
    data object Sulfite : Intolerances("Sulfite")
    data object TreeNut : Intolerances("Tree Nut")
    data object Wheat : Intolerances("Wheat")
}

