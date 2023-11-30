package com.example.tictactoe.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import androidx.compose.runtime.mutableStateOf


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import io.garrit.android.multiplayer.ActionResult
import io.garrit.android.multiplayer.GameResult
import io.garrit.android.multiplayer.SupabaseCallback
import io.garrit.android.multiplayer.SupabaseService
import kotlinx.coroutines.launch


data class SquareValue(
    val x: Int,
    val y: Int,
    val value: String = "") {
    // A square on the Tic Tac Toe board can have three states: 'X', 'O', or empty (' ')
}


class GameViewModel : ViewModel(), SupabaseCallback {
    // Represents the Tic Tac Toe board
    var board = mutableStateListOf<SquareValue>()


    // Represents the current player ('X' or 'O')
    var currentPlayer by mutableStateOf("X")

    // Represents the winner of the game
    var winner by mutableStateOf<Char?>(null)
    var backgroundColor by mutableStateOf(Color.Gray)
        private set // här bara för att läsa inte ändra på
    // Function to make a move on the board
    fun changeBackGroundColr(){
        backgroundColor= Color.White
    }
    init {
        SupabaseService.callbackHandler = this
        for(y in 0 until 3 ){
            for(x in 0 until 3){
                board.add(SquareValue(x,y))
            }
        }
    }
    fun sqClicked(sq: SquareValue) {
        println("Sq: $sq")
        if(backgroundColor != Color.White) {
            backgroundColor = Color.White
            return
        }
        val newSq = sq.copy(value = currentPlayer)
        val i = sq.y * 3 + sq.x
        viewModelScope.launch {
            SupabaseService.sendTurn(sq.x, sq.y)
        }
        board[sq.y * 3 + sq.x] = newSq
        println("board: $board")
        for(s in board) {
            println(s)
        }
}
    // Function to check if there is a winner
    fun checkForWinner() {
        // Check rows
        for (i in 0 until 3) {
            if (checkRow(i)) {
                winner = currentPlayer.first() // The first character of currentPlayer is the winning symbol
                return
            }
        }

        // Check columns
        for (i in 0 until 3) {
            if (checkColumn(i)) {
                winner = currentPlayer.first()
                return
            }
        }

        // Check diagonals
        if (checkDiagonal() != null) {
            winner = currentPlayer.first()
        }
    }

    private fun checkRow(row: Int): Boolean {
        val startIndex = row * 3
        val symbols = board.subList(startIndex, startIndex + 3).map { it.value }
        return symbols.all { it == currentPlayer }
    }

    private fun checkColumn(column: Int): Boolean {
        val symbols = (0 until 3).map { board[it * 3 + column].value }
        return symbols.all { it == currentPlayer }
    }

    private fun checkDiagonal(): Boolean? {
        val leftToRight = (0 until 3).map { board[it * 3 + it].value }
        val rightToLeft = (0 until 3).map { board[it * 3 + (2 - it)].value }

        return when {
            leftToRight.all { it == currentPlayer } -> true
            rightToLeft.all { it == currentPlayer } -> true
            else -> null
        }
    }


    // Function to reset the game
    fun resetGame() {
        board.clear()
        currentPlayer = "X"
        winner = null
    }

    override suspend fun playerReadyHandler() {
        TODO("Not yet implemented")
    }

    override suspend fun releaseTurnHandler() {

        currentPlayer = if (currentPlayer == "X") "O" else "X"

    }

    override suspend fun actionHandler(x: Int, y: Int) {

        println("actionHandler $x $y")
        board[y * 3 + x] = SquareValue(x, y, if (currentPlayer == "X") "O" else "X")
    }

    override suspend fun answerHandler(status: ActionResult) {



    }

    override suspend fun finishHandler(status: GameResult) {


            // Add more cases as needed based on your game's logic
        }
    }






