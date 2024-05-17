package com.joel.mealmaster.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.joel.data.repo.ConnectivityObserver
import com.joel.data.repo.RecipeRepository
import com.joel.data.repo.defaul.DefaultConnectivityObserver
import com.joel.data.repo.defaul.DefaultRecipeRepository
import com.joel.domain.use_case.RecipeUseCase
import com.joel.domain.use_case.recipe.GetRandomRecipeUseCase
import com.joel.domain.use_case.recipe.GetRecipeInfoUseCase
import com.joel.mealmaster.BuildConfig
import com.joel.network.service.RecipeService
import com.joel.network.source.DefaultRecipeRemoteSource
import com.joel.network.source.RecipeRemoteSource
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

interface AppContainer {
    val recipeRemoteSource : RecipeRemoteSource
    val connectivityObserver : ConnectivityObserver
    val repository : RecipeRepository
    val recipeUseCase : RecipeUseCase
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer{

    private val spoonacularBaseUrl = "https://api.spoonacular.com"

    private val json = Json { ignoreUnknownKeys = true }

    private val client : OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(provideLoggingInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .baseUrl(spoonacularBaseUrl)
        .build()

    private val retrofitService: RecipeService by lazy {
        retrofit.create(RecipeService::class.java)
    }

    override val recipeRemoteSource: RecipeRemoteSource by lazy {
        DefaultRecipeRemoteSource(
            recipeService = retrofitService,
            apiKey = BuildConfig.API_KEY
        )
    }
    override val connectivityObserver: ConnectivityObserver by lazy {
        DefaultConnectivityObserver(context)
    }
    override val repository: RecipeRepository by lazy {
        DefaultRecipeRepository(recipeRemoteSource)
    }

    override val recipeUseCase: RecipeUseCase by lazy {
        RecipeUseCase(
            getRandomRecipeUseCase = GetRandomRecipeUseCase(repository),
            getRecipeInfoUseCase = GetRecipeInfoUseCase(repository)
        )
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().also {
            it.level = level
        }
    }


}

