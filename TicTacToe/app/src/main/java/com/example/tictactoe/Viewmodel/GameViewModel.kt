package com.example.tictactoe.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import androidx.compose.runtime.mutableStateOf


import androidx.compose.runtime.mutableStateOf
import io.garrit.android.multiplayer.SupabaseService


class SquareValue(x:Int,y:Int) {
    // A square on the Tic Tac Toe board can have three states: 'X', 'O', or empty (' ')
    var value by mutableStateOf(' ')
    var x = mutableStateOf(x)
    var y = mutableStateOf(y)
}

class GameViewModel : ViewModel() {
    // Represents the Tic Tac Toe board
    var board = mutableListOf<SquareValue>()


    // Represents the current player ('X' or 'O')
    var currentPlayer by mutableStateOf('X')

    // Represents the winner of the game
    var winner by mutableStateOf<Char?>(null)

    // Function to make a move on the board

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
}





