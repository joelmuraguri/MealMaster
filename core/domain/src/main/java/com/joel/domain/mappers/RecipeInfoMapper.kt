package com.joel.domain.mappers

import com.joel.domain.model.RecipeInfo
import com.joel.network.dtos.RecipeInfoDTO

fun RecipeInfoDTO.toRecipeInfoDomainModel(): RecipeInfo {
    return RecipeInfo(
        id = this.id ?: 0,
        name = this.title.orEmpty(),
        image = this.image.orEmpty(),
        timeToPrepare = this.readyInMinutes ?: 0,
        likes = this.aggregateLikes ?: 0,
        instructions = this.analyzedInstructions.map { it.toInstructionsDomainModel() },
        nutrients = this.nutrition?.nutrients?.map { it.toNutrientsDomainModel() } ?: emptyList(),
        ingredients = this.extendedIngredients.map { it.toIngredientDomainModel() }
    )
}

fun RecipeInfoDTO.AnalyzedInstruction.toInstructionsDomainModel(): RecipeInfo.Instructions {
    return RecipeInfo.Instructions(
        steps = this.steps.map { it.toStepDomainModel() }
    )
}

fun RecipeInfoDTO.AnalyzedInstruction.Step.toStepDomainModel(): RecipeInfo.Instructions.Step {
    return RecipeInfo.Instructions.Step(
        equipment = this.equipment.map { it.toEquipmentDomainModel() },
        ingredients = this.ingredients.map { it.toInstructionIngredients() },
        length = RecipeInfo.Instructions.Step.Length(
            number = this.length?.number ?: 0,
            units = this.length?.unit.orEmpty()
        ),
        number = this.number ?: 0,
        step = this.step.orEmpty()
    )
}

fun RecipeInfoDTO.AnalyzedInstruction.Step.Equipment.toEquipmentDomainModel(): RecipeInfo.Instructions.Step.Equipment {
    return RecipeInfo.Instructions.Step.Equipment(
        id = this.id ?: 0,
        image = this.image.orEmpty(),
        name = this.name.orEmpty()
    )
}

fun RecipeInfoDTO.AnalyzedInstruction.Step.Ingredient.toInstructionIngredients() : RecipeInfo.Instructions.Step.Ingredient{
    return RecipeInfo.Instructions.Step.Ingredient(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        image = this.image.orEmpty()
    )
}

fun RecipeInfoDTO.ExtendedIngredient.toIngredientDomainModel(): RecipeInfo.Ingredient {
    return RecipeInfo.Ingredient(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        nameDeck = this.originalName.orEmpty(),
        amount = this.amount?.toInt() ?: 0,
        unit = this.unit.orEmpty(),
        image = this.image.orEmpty()
    )
}

fun RecipeInfoDTO.Nutrition.Ingredient.NutrientX.toNutrientsDomainModel(): RecipeInfo.Nutrients {
    return RecipeInfo.Nutrients(
        name = this.name.orEmpty(),
        amount = this.amount?.toInt() ?: 0,
        unit = this.unit.orEmpty()
    )
}
