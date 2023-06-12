package com.zakdroid.pizzastore.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PizzaApiClient {
    private const val BASE_URL = "https://static.mozio.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PizzaApi by lazy {
        retrofit.create(PizzaApi::class.java)
    }
}