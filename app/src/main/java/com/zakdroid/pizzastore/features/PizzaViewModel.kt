package com.zakdroid.pizzastore.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zakdroid.pizzastore.model.Pizza
import com.zakdroid.pizzastore.network.PizzaApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PizzaViewModel : ViewModel() {
    private val _pizzas = MutableStateFlow<List<Pizza>>(listOf())
    val pizzas: StateFlow<List<Pizza>> = _pizzas.asStateFlow()

    val currentPizza = MutableStateFlow(Pizza())


    fun updateCurrentPizzaState(name: String, price: Double) {
        currentPizza.update { value ->
            value.copy(name = name, price = price)
        }

    }

    fun fetchPizzas() {
        viewModelScope.launch {
            try {
                val response = PizzaApiClient.api.getPizzas()
                _pizzas.emit(response)
            } catch (e: Exception) {
                // TODO "Show error"
            }
        }
    }
}