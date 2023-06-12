package com.zakdroid.pizzastore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.zakdroid.pizzastore.features.PizzaViewModel
import com.zakdroid.pizzastore.features.catalog.CatalogScreen
import com.zakdroid.pizzastore.features.order.OrderScreen
import com.zakdroid.pizzastore.features.pizzaselection.PizzaSelectionScreen
import com.zakdroid.pizzastore.model.Pizza

@Composable
fun NavGraph(navController: NavHostController, mainPizzaViewModel: PizzaViewModel) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Catalog.route
    ) {
        composable(route = NavRoutes.Catalog.route) {
            CatalogScreen(viewModel = mainPizzaViewModel, navToPizzaSelection = { pizza ->
                val jsonPizza = Gson().toJson(pizza)
                navController.navigate("${NavRoutes.PizzaSelection.route}/$jsonPizza")
            })
        }
        composable(
            route = "${NavRoutes.PizzaSelection.route}/{pizza}",
            arguments = listOf(navArgument("pizza") { type = NavType.StringType })
        ) { backStackEntry ->
            val pizzaJSON = backStackEntry.arguments?.getString("pizza")
            val pizza = Gson().fromJson(pizzaJSON, Pizza::class.java)
            PizzaSelectionScreen(pizza, mainPizzaViewModel, navController)
        }
        composable(
            route = "${NavRoutes.OrderPizza.route}/{pizzaName}/{pizzaPrice}",
            arguments = listOf(
                navArgument("pizzaName") { type = NavType.StringType },
                navArgument("pizzaPrice") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val pizzaName = arguments.getString("pizzaName")
            val pizzaPrice = arguments.getFloat("pizzaPrice")
            OrderScreen(navController, pizzaName, pizzaPrice)
        }
    }
}

sealed class NavRoutes(val route: String) {

    object Catalog : NavRoutes("catalog")

    object PizzaSelection : NavRoutes("pizzaSelection")

    object OrderPizza : NavRoutes("order")
}