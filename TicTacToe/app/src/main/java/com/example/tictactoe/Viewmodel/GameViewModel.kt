package com.example.tictactoe.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import androidx.compose.runtime.mutableStateOf


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import io.garrit.android.multiplayer.ActionResult
import io.garrit.android.multiplayer.GameResult
import io.garrit.android.multiplayer.SupabaseCallback
import io.garrit.android.multiplayer.SupabaseService


class SquareValue(x:Int,y:Int) {
    // A square on the Tic Tac Toe board can have three states: 'X', 'O', or empty (' ')
    var value by mutableStateOf(' ')
    var x = mutableStateOf(x)
    var y = mutableStateOf(y)
}

class GameViewModel : ViewModel() ,SupabaseCallback {
    // Represents the Tic Tac Toe board
    var board = mutableListOf<SquareValue>()


    // Represents the current player ('X' or 'O')
    var currentPlayer by mutableStateOf('X')

    // Represents the winner of the game
    var winner by mutableStateOf<Char?>(null)
    var backgroundColor by mutableStateOf(Color.Gray)
        private set // här bara för att läsa inte ändra på
    // Function to make a move on the board
    fun changeBackGroundColr(){
        backgroundColor= Color.White
    }
    init {
        for(y in 0 until 3 ){
            for(x in 0 until 3){
                board.add(SquareValue(x,y))
            }
        }
    }
    // Function to check if there is a winner


    // Function to reset the game
    fun resetGame() {
        board.clear()
        currentPlayer = 'X'
        winner = null
    }

    override suspend fun playerReadyHandler() {
        TODO("Not yet implemented")
    }

    override suspend fun releaseTurnHandler() {
        TODO("Not yet implemented")

    }

    override suspend fun actionHandler(x: Int, y: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun answerHandler(status: ActionResult) {
        TODO("Not yet implemented")
    }

    override suspend fun finishHandler(status: GameResult) {
        TODO("Not yet implemented")
    }
}





