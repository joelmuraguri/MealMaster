package com.joel.network.api

import com.joel.network.api.impl.RecipesApiImpl
import com.joel.network.response.AnalyzedInstructionsApi
import com.joel.network.response.IngredientsSearchApi
import com.joel.network.response.RandomRecipeApi
import com.joel.network.response.RecipeInfoApi
import com.joel.network.response.RecipeSearchApi
import com.joel.network.response.SimilarRecipeApi
import com.joel.network.utils.AnalyzedInstructionsApiModel
import com.joel.network.utils.HttpClientFactory
import com.joel.network.utils.IngredientsSearchApiModel
import com.joel.network.utils.RecipeInfoApiModel
import com.joel.network.utils.RecipeSearchApiModel
import com.joel.network.utils.SimilarApiModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

class RecipeApiTest {

    @Test
    fun `Give Success on Recipe Info Success`(){

        val recipeId = 716429

        val engine = MockEngine{
            respond(
                content = RecipeInfoApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().create(engine)
        runBlocking {

            val response = RecipesApiImpl(client, "").fetchRecipeInfo(recipeId)
            val expected = Json.decodeFromString<RecipeInfoApi>(RecipeInfoApiModel)
            assertEquals(expected, response)

        }
    }

//    @Test
//    fun `Give Success on Random Recipes Success`(){
//
//        val engine = MockEngine{
//            respond(
//                content = RandomRecipeApiModel,
//                headers = headersOf(HttpHeaders.ContentType, "application/json")
//            )
//        }
//
//        val client = HttpClientFactory().create(engine)
//        runBlocking {
//
//            val response = RecipesApiImpl(client, "").fetchRandomRecipes(number = 20, tags = listOf("vegeterian","snack"))
//            val expected = Json.decodeFromString<RandomRecipeApi>(RandomRecipeApiModel)
//            assertEquals(expected, response)
//
//        }
//    }

    @Test
    fun `Give Success on Search Recipes Success`(){

        val engine = MockEngine{
            respond(
                content = RecipeSearchApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().create(engine)
        runBlocking {

            val response = RecipesApiImpl(client, "").searchRecipes(diet = listOf("vegeterian") ,allergies = listOf("dairy"), query = "te")
            val expected = Json.decodeFromString<RecipeSearchApi>(RecipeSearchApiModel)
            assertEquals(expected, response)

        }
    }

    @Test
    fun `Give Success on Analyzed Recipes Instructions Success`(){

        val recipeId = 640279


        val engine = MockEngine{
            respond(
                content = AnalyzedInstructionsApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().create(engine)
        runBlocking {

            val response = RecipesApiImpl(client, "").fetchAnalyzedRecipeInstructions(recipeId = recipeId)
            val expected = Json.decodeFromString<AnalyzedInstructionsApi>(AnalyzedInstructionsApiModel)
            assertEquals(expected, response)

        }
    }

    @Test
    fun `Give Success on Similar Recipes Success`(){

        val similarId = 640279

        val engine = MockEngine{
            respond(
                content = SimilarApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().create(engine)
        runBlocking {

            val response = RecipesApiImpl(client, "").fetchSimilarRecipes(recipeId = similarId)
            val expected = Json.decodeFromString<SimilarRecipeApi>(SimilarApiModel)
            assertEquals(expected, response)

        }
    }

    @Test
    fun `Give Success on Ingredients Search Success`(){

        val engine = MockEngine{
            respond(
                content = IngredientsSearchApiModel,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClientFactory().create(engine)
        runBlocking {

            val response = RecipesApiImpl(client, "").fetchIngredients("apple")
            val expected = Json.decodeFromString<IngredientsSearchApi>(IngredientsSearchApiModel)
            assertEquals(expected, response)

        }
    }

}