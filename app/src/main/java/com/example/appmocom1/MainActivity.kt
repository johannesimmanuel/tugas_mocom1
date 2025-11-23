package com.example.appmocom1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmocom1.screens.AddItemScreen
import com.example.appmocom1.screens.InventoryScreen
import com.example.appmocom1.screens.LoginScreen
import com.example.appmocom1.ui.theme.Appmocom1Theme

class MainActivity : ComponentActivity() {

    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Appmocom1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: InventoryViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                onLoginClicked = { username ->
                    viewModel.setUsername(username)
                    navController.navigate("inventory") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("inventory") {
            InventoryScreen(
                viewModel = viewModel,
                onAddItemClicked = {
                    navController.navigate("add_item")
                }
            )
        }

        composable("add_item") {
            AddItemScreen(
                onSaveClicked = { name, quantity ->
                    viewModel.addItem(name, quantity)
                    navController.popBackStack()
                }
            )
        }
    }
}