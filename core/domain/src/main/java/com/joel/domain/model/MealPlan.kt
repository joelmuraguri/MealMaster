package com.joel.domain.model

data class MealPlan(
    val mealPlanId : Int,
    val mealPlanName : String,
    val date : Long,
    val recipeName : List<String>,
    val time : Long,
    val serves : Int,
)
