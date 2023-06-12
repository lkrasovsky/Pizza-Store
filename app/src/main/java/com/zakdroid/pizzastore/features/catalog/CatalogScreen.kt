package com.zakdroid.pizzastore.features.catalog

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.zakdroid.pizzastore.features.PizzaViewModel
import com.zakdroid.pizzastore.model.Pizza
import com.zakdroid.pizzastore.widget.PizzaCard

@Composable
fun CatalogScreen(viewModel: PizzaViewModel, navToPizzaSelection: (Pizza) -> Unit) {
    LaunchedEffect(Unit) {
        viewModel.fetchPizzas()
    }
    val pizzas by viewModel.pizzas.collectAsState()
    LazyColumn {
        items(pizzas) { pizza ->
            PizzaCard(pizza = pizza, onClick = { navToPizzaSelection(pizza) })
        }
    }
}