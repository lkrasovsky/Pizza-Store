package com.zakdroid.pizzastore.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zakdroid.pizzastore.model.Pizza

@Composable
fun PizzaCard(pizza: Pizza, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = pizza.name, style = MaterialTheme.typography.displaySmall)
            Text(text = "Price: ${pizza.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}