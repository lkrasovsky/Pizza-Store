package com.zakdroid.pizzastore.features.pizzaselection

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zakdroid.pizzastore.features.PizzaViewModel
import com.zakdroid.pizzastore.model.Pizza
import com.zakdroid.pizzastore.navigation.NavRoutes

@Composable
fun PizzaSelectionScreen(
    pizza: Pizza,
    pizzaViewModel: PizzaViewModel,
    navController: NavHostController
) {
    val pizzas by pizzaViewModel.pizzas.collectAsState()
    val currentPizza by pizzaViewModel.currentPizza.collectAsState()


    val selectedPizzaName = remember { mutableStateOf("") }
    val selectedPizzaPrice = remember { mutableStateOf(pizza.price) }

    val selectedButton = remember { mutableStateOf("One Flavor") }
    val buttonOneFlavor = "One Flavor"
    val buttonTwoFlavors = "Two Flavors"
    val pizzaFlavorBoolean = remember { mutableStateOf(false) }

    //val pizzaPrice = remember { mutableStateOf(0.0) }
    //pizzaPrice.value = (pizza.price + selectedPizzaPrice.value) / 2
    //(pizza.price + selectedPizzaPrice.value) / 2
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(contentAlignment = Alignment.Center) {
                DividedCircle(pizzaFlavorBoolean.value)
            }
            if (!pizzaFlavorBoolean.value) {
                pizzaViewModel.updateCurrentPizzaState(pizza.name, pizza.price)
                Text(text = currentPizza.name)
                Text(text = currentPizza.price.toString())

            } else if (pizza.name != selectedPizzaName.value) {
                pizzaViewModel.updateCurrentPizzaState(
                    name = "${pizza.name} ${selectedPizzaName.value}",
                    currentPizza.price
                )
                Text(text = currentPizza.name)
                Text(text = currentPizza.price.toString())
            } else {
                pizzaViewModel.updateCurrentPizzaState(name = pizza.name, currentPizza.price)
                Text(text = currentPizza.name)
                Text(text = currentPizza.price.toString())


            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(
                    onClick = {
                        selectedButton.value = buttonOneFlavor
                        pizzaFlavorBoolean.value = false
                    },
                    enabled = selectedButton.value != buttonOneFlavor
                ) {
                    Text(text = buttonOneFlavor)
                }
                Button(
                    onClick = {
                        selectedButton.value = buttonTwoFlavors
                        pizzaFlavorBoolean.value = true
                    },
                    enabled = selectedButton.value != buttonTwoFlavors
                ) {
                    Text(text = buttonTwoFlavors)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (pizzaFlavorBoolean.value) {
                LazyColumn {
                    items(pizzas) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = item.name == selectedPizzaName.value,
                                onClick = {
                                    selectedPizzaPrice.value = item.price
                                    selectedPizzaName.value = item.name
                                    val newPrice = (pizza.price + item.price) / 2
                                    pizzaViewModel.updateCurrentPizzaState(pizza.name, newPrice)
                                }
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = item.name)
                        }
                    }
                }
            }
            Button(onClick = {
                navController.navigate("${NavRoutes.OrderPizza.route}/${currentPizza.name}/${currentPizza.price.toFloat()}")

            }) {
                Text(text = "Confirm order")
            }
        }
    }
}

@Composable
fun DividedCircle(line: Boolean) {
    Canvas(modifier = Modifier.size(150.dp)) {
        val radius = size.minDimension / 2f
        val center = Offset(size.width / 2f, size.height / 2f)
        val strokeWidth = 10f

        drawCircle(
            color = Color.Black,
            radius = radius,
            center = center,
            style = Stroke(strokeWidth)
        )
        if (line) {
            drawLine(
                color = Color.Black,
                start = Offset(center.x, center.y - radius),
                end = Offset(center.x, center.y + radius),
                strokeWidth = strokeWidth
            )
        }
    }
}