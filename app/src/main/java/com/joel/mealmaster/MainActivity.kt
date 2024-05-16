package com.joel.mealmaster

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.joel.mealmaster.navigation.BottomNavigationBar
import com.joel.mealmaster.navigation.MealMasterNavHost
import com.joel.mealmaster.navigation.Screens
import com.joel.mealmaster.ui.theme.MealMasterTheme
import com.joel.mealmaster.utils.vm.OnBoardingViewModel
import com.joel.preference.PreferenceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onBoardingViewModel = ViewModelProvider(this)[OnBoardingViewModel::class.java]
        val preferenceViewModel = ViewModelProvider(this)[PreferenceViewModel::class.java]
        installSplashScreen().setKeepOnScreenCondition {
            !onBoardingViewModel.isLoading.value
        }
        setContent {
            MealMasterTheme {
                MealApp()
            }
        }
    }
}


@Composable
fun MealApp(){
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val fabBottomState = rememberSaveable { mutableStateOf(true) }

    val onBoardingViewModel = hiltViewModel<OnBoardingViewModel>()
    val preferenceViewModel = hiltViewModel<PreferenceViewModel>()

    val onBoardingStatus by onBoardingViewModel.onboardingCompleted
    val preferenceStatus by preferenceViewModel.preferenceOnboardingCompleted

    val startDestination = rememberSaveable {
        if (onBoardingStatus && preferenceStatus) {
            Screens.Explore.route
        } else {
            onBoardingViewModel.startDestination.value
        }
    }

    LaunchedEffect(Unit) {
        if (preferenceStatus && onBoardingStatus) {
            navController.navigate(Screens.Explore.route) {
                popUpTo(Screens.Preference.route) {
                    inclusive = true
                }
            }
        }
//        } else {
//            navController.navigate(
//                if (!onBoardingStatus) {
//                    Screens.Onboarding.route
//                } else {
//                    Screens.Preference.route
//                }
//            )
//        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { if (bottomBarState.value) BottomNavigationBar(navController) },
        floatingActionButton = { if (fabBottomState.value) FAB(onNavToMealPlan = {navController.navigate(Screens.MealPlan.route)})},
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            MealMasterNavHost(
                navController = navController,
                updateBottomBarState = { bottomBarState.value = it },
                startDestination = startDestination,
                updateFABState = { fabBottomState.value = it }
            )
        }
    }
}

@Composable
fun FAB(
    onNavToMealPlan : () -> Unit
){
    FloatingActionButton(onClick = { onNavToMealPlan() }) {
        Icon(painter = painterResource(id = R.drawable.round_add_24), contentDescription = null)
    }
}
