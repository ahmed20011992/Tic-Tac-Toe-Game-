package com.example.tictactoe.Screens

sealed class Screen (val route: String){
    object Main : Screen (route= "main")
    object Game : Screen (route = "game")
    object Lobby: Screen (route = "lobby")
}

