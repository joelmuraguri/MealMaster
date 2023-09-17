package com.joel.mealmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.joel.mealmaster.navigation.BottomNavigationBar
import com.joel.mealmaster.navigation.MealMasterNavHost
import com.joel.mealmaster.ui.theme.MealMasterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var splashViewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }
        setContent {
            MealMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val screen by splashViewModel.startDestination
                    MealApp(startDestination = screen)
                }
            }
        }
    }
}


@Composable
fun MealApp(startDestination : String){
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { if (bottomBarState.value) BottomNavigationBar(navController) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            MealMasterNavHost(
                navController = navController,
                updateBottomBarState = { bottomBarState.value = it },
                startDestination = startDestination
            )
        }
    }
}
