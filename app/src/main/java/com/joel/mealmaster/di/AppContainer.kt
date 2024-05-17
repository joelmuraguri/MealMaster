package com.joel.mealmaster.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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
}

class DefaultAppContainer : AppContainer{

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

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().also {
            it.level = level
        }
    }


}

