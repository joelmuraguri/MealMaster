package com.joel.onboarding.utils

import com.joel.onboarding.R

data class OnboardingItems(
    val image : Int,
    val titleHeader : Int,
    val screenDescription : Int
) {
    companion object{
        fun getData(): List<OnboardingItems>{
            return listOf(
                OnboardingItems(
                    image = R.drawable.beautiful_delicious_fast_food,
                    titleHeader =  R.string.endless_meal_ideas,
                    R.string.endless_meal_ideas_desc
                ),
                OnboardingItems(
                    image = R.drawable.meal_plan_image,
                    titleHeader =  R.string.organise_your_meal_plans,
                    R.string.organise_your_meal_plans_desc
                ),
                OnboardingItems(
                    image = R.drawable.favourite_meals,
                    titleHeader =  R.string.save_your_favourites,
                    R.string.save_your_favourites_desc
                ),
                OnboardingItems(
                    image = R.drawable.share_love,
                    titleHeader =  R.string.share_your_love,
                    R.string.share_your_love_desc
                )
            )
        }
    }
}