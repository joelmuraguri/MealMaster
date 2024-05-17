package com.joel.mealmaster.di

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.joel.discover.DiscoverViewModel
import com.joel.mealmaster.MealMasterApp
import com.joel.mealmaster.utils.connectivity.ConnectivityObserverViewModel

object ViewModelFactory {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    val Factory = viewModelFactory {
        initializer {
            ConnectivityObserverViewModel(
                connectivityObserver = mealMasterApplication().container.connectivityObserver
            )
        }
        initializer {
            DiscoverViewModel(
                useCase = mealMasterApplication().container.recipeUseCase
            )
        }
    }
}

fun CreationExtras.mealMasterApplication() : MealMasterApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MealMasterApp)