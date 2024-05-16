package com.joel.mealmaster

import android.os.Bundle
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.joel.mealmaster.navigation.BottomNavigationBar
import com.joel.mealmaster.navigation.MealMasterNavHost
import com.joel.mealmaster.navigation.Screens
import com.joel.mealmaster.ui.theme.MealMasterTheme
import com.joel.mealmaster.utils.vm.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var splashViewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onBoardingViewModel = ViewModelProvider(this)[OnBoardingViewModel::class.java]

        installSplashScreen().setKeepOnScreenCondition {
            !onBoardingViewModel.isLoading.value
        }
        setContent {
            MealMasterTheme {
                // A surface container using the 'background' color from the theme
                val screen by onBoardingViewModel.startDestination
                MealApp(startDestination = screen)
//                OnBoardingScreen()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
////                    OnboardingScreen()
////                    val screen by splashViewModel.startDestination
////                    MealApp(startDestination = screen)
//                }
            }
        }
    }
}


@Composable
fun MealApp(startDestination : String){
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val fabBottomState = rememberSaveable { mutableStateOf(true) }

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
