package com.example.tictactoe

import LobbyScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.Screens.GameScreen

import com.example.tictactoe.Screens.MainScreen
import com.example.tictactoe.Screens.Screen
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            val navController = rememberNavController()
            TicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Screen.Main.route) {
                        composable(route = Screen.Main.route) {
                            MainScreen(navController = navController)
                        }
                        composable(route = Screen.Game.route) {
                            GameScreen(navController = navController)
                        }
                        composable(route= Screen.Lobby.route){
                            LobbyScreen(navController=navController)
                        }

                    }
                }
            }
        }
    }
}