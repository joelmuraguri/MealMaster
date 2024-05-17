package com.joel.mealmaster

import android.app.Application
import com.joel.mealmaster.di.AppContainer
import com.joel.mealmaster.di.DefaultAppContainer
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MealMasterApp : Application() {

    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        plantDebugBuildLogger()
        container = DefaultAppContainer(this)
    }

    private fun plantDebugBuildLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}