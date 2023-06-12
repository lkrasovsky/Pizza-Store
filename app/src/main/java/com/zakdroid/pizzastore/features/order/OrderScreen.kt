package com.zakdroid.pizzastore.features.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zakdroid.pizzastore.navigation.NavRoutes

@Composable
fun OrderScreen(navController: NavHostController, pizzaName: String?, pizzaPrice: Float) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Success order")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "NAME: $pizzaName")
            Text(text = "PRICE: $pizzaPrice")
            Button(onClick = {
                navController.navigate(
                    route = NavRoutes.Catalog.route,
                    builder = {
                        popUpTo(NavRoutes.Catalog.route) { inclusive = true }
                    }
                )
            }) {
                Text(text = "Complete")
            }
        }

    }
}