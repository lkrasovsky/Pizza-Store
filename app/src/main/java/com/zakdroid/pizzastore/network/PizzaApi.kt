package com.zakdroid.pizzastore.network

import com.zakdroid.pizzastore.model.Pizza
import retrofit2.http.GET

interface PizzaApi {
    @GET("mobile/tests/pizzas.json")
    suspend fun getPizzas(): List<Pizza>
}
