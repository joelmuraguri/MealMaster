package com.joel.domain.use_case.recipe

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.joel.data.repo.RecipeRepository
import com.joel.domain.mappers.toRecipeDomainModel
import com.joel.domain.model.Recipe
import com.joel.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetRandomRecipeUseCase(
    private val repository: RecipeRepository
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke() : Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            val randomRecipes = repository.getRandomRecipe()
            val recipes = randomRecipes.recipes.map { it.toRecipeDomainModel() }
            emit(Resource.Success(recipes))
        }
        catch(e : IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
        catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?:"FATAL EXCEPTION: Target Server disagree with how request was formatted"))
        }
    }

}