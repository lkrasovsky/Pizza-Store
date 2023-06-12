package com.zakdroid.pizzastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zakdroid.pizzastore.features.MainPizzaScreen
import com.zakdroid.pizzastore.features.PizzaViewModel
import com.zakdroid.pizzastore.features.catalog.CatalogScreen
import com.zakdroid.pizzastore.ui.theme.PizzaStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaStoreTheme {
                val mainPizzaViewModel: PizzaViewModel by viewModels()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPizzaScreen(mainPizzaViewModel = mainPizzaViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PizzaStoreTheme {
    }
}