package com.joel.mealmaster.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.joel.mealmaster.ui.theme.MealMasterTheme
import com.joel.mealmaster.ui.theme.orange20

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(0xFF1D1D2A)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomBarNavigationList.forEach { destination ->
            val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(
                selected = selected,
                icon = {
                    Icon(
                        painter = painterResource(id = destination.icon!!),
                        contentDescription = destination.title,
                        tint = if (selected) Color(0xFF5180f1) else Color.White
                    )
                },
                label = {  },
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(Screens.Explore.route){
                            inclusive = true
                            saveState = true
                        }
                    }
                },
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    MealMasterTheme {
        BottomNavigationBar(rememberNavController())
    }
}