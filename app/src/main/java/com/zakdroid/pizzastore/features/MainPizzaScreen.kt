package com.zakdroid.pizzastore.features

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.zakdroid.pizzastore.navigation.NavGraph

@Composable
fun MainPizzaScreen(mainPizzaViewModel: PizzaViewModel) {

    val navController = rememberNavController()
    NavGraph(navController, mainPizzaViewModel)

}