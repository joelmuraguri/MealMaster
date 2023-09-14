package com.joel.network.response

data class RecipeInformation(
    val aggregateLikes: Int,
    val analyzedInstructions: List<Any>,
    val cheap: Boolean,
    val cookingMinutes: Int,
    val creditsText: String,
    val cuisines: List<Any>,
    val dairyFree: Boolean,
    val diets: List<Any>,
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
    val nutrition: Nutrition,
    val occasions: List<Any>,
    val originalId: Any,
    val preparationMinutes: Int,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int,
    val winePairing: WinePairing
)
data class WinePairing(
    val pairedWines: List<Any>,
    val pairingText: String,
    val productMatches: List<Any>
)

data class WeightPerServing(
    val amount: Int,
    val unit: String
)

data class Us(
    val amount: Double,
    val unitLong: String,
    val unitShort: String
)

data class Measures(
    val metric: Metric,
    val us: Us
)

data class NutrientX(
    val amount: Double,
    val name: String,
    val percentOfDailyNeeds: Double,
    val unit: String
)

data class Nutrition(
    val caloricBreakdown: CaloricBreakdown,
    val flavonoids: List<Flavonoid>,
    val ingredients: List<Ingredient>,
    val nutrients: List<NutrientX>,
    val properties: List<Property>,
    val weightPerServing: WeightPerServing
)

data class Metric(
    val amount: Double,
    val unitLong: String,
    val unitShort: String
)

data class Property(
    val amount: Double,
    val name: String,
    val unit: String
)

data class CaloricBreakdown(
    val percentCarbs: Double,
    val percentFat: Double,
    val percentProtein: Double
)

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
)

data class Flavonoid(
    val amount: Double,
    val name: String,
    val unit: String
)

data class Ingredient(
    val amount: Double,
    val id: Int,
    val name: String,
    val nutrients: List<NutrientX>,
    val unit: String
)