package com.example.tictactoe.Viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
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

    var board = mutableStateListOf<SquareValue>()



    var playerXScore  by mutableStateOf(0) // här jag måste göra dem mutable remember by
    var playerOScore by mutableStateOf(0)  //
    var gameIsfinish = false


    var isPlayerXTurn by mutableStateOf(true)
// Lägg till en ny variabel för att hålla koll på om det är spelare X:s tur

    // Represents the current player ('X' or 'O')
    var currentPlayer by mutableStateOf("X")

    // Represents the winner of the game
    var winner by mutableStateOf<Char?>(null)//?
    var backgroundColor by mutableStateOf(Color.Gray)
        private set // här bara för att läsa inte ändra på
    // Function to make a move on the board
    fun changeBackGroundColr(){
        backgroundColor= Color.White
    }
    init {
        SupabaseService.callbackHandler = this// """is assigning the current instance of the GameViewModel class as the
        // callback handler for the SupabaseServiceis assigning the current instance of the GameViewModel class
        // as the callback handler for the SupabaseService"""
        for(y in 0 until 3 ){
            for(x in 0 until 3){
                board.add(SquareValue(x,y))
            }
        }
    }
    fun sqClicked(sq: SquareValue) {
        println("Sq: $sq")
        if (board[sq.y * 3 + sq.x].value.isNotBlank()) {

            return
        }
        if(backgroundColor != Color.White) {/// here it is another way to make the back.. white inted of using view model as lärare done..
            backgroundColor = Color.White
            return
        }
        val newSq = sq.copy(value = currentPlayer)
        val i = sq.y * 3 + sq.x
        viewModelScope.launch {
            SupabaseService.sendTurn(sq.x, sq.y)//?// här den sickar den squer som är klicked till andra plyer ,,
            ////SupabaseService.releaseTurn() ///
        }
        board[sq.y * 3 + sq.x] = newSq
        println("board: $board")
        for(s in board) {
            println(s)
        }
       /// / om inte non har vinn jag måse sicka x turn is fules
        // jag måste bestämma vim har tåg plats first
        /// i nottun play more bara gå till baka till Lobby screen



        isPlayerXTurn = !isPlayerXTurn///// den betyder att jag inte current player nu spärra skärmen(inte min tur )
        checkForWinner()
        ////Anropa callback för att meddela att ett drag har gjorts
}
    // Function to check if there is a winner
    fun checkForWinner() {
        // Check rows
        for (i in 0 until 3) {
            if (checkRow(i)) {
                winner = currentPlayer.first() // The first character of currentPlayer is the winning symbol
                updateScore()
                return
            }
        }

        // Check columns
        for (i in 0 until 3) {
            if (checkColumn(i)) {
                winner = currentPlayer.first()
                updateScore()
                return
            }
        }

        // Check diagonals
        if (checkDiagonal() != null) {
            winner = currentPlayer.first()
            updateScore()
        }




    }

    private fun updateScore() {

        when (winner) {
            'X' -> {playerXScore++}
            'O' -> {playerOScore++}
        }

        isPlayerXTurn = false // späära
        gameIsfinish = true //
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

        println("ARE YOU READY!")

        // jag behöver implementera här
    }

    override suspend fun releaseTurnHandler() {// den bestämer vem är aktuell player nu

        currentPlayer = if (currentPlayer == "X") "O" else "X"//
        isPlayerXTurn = true
    }

    override suspend fun actionHandler(x: Int, y: Int) {
        // den betyder ge mig den click som andra spelare har clikat oc den funkar i båda sidor för o och X
        val sq :SquareValue = SquareValue(x, y, if (currentPlayer == "X") "O" else "X")
        println("actionHandler $x $y")// här jag printer den andra spelare clikcat
        board[y * 3 + x] = sq
        //currentPlayer = "O" // for att shika säkert att den o tur innan check winner
        currentPlayer = "O"
        checkForWinner()
        if ( ! gameIsfinish ){
             currentPlayer = "X"
            isPlayerXTurn = true // den gör att skärmen är inte spärrat , den gör den free av spärriing

        }


    }

    override suspend fun answerHandler(status: ActionResult) {




    }

    override suspend fun finishHandler(status: GameResult) {


            // Add more cases as needed based on your game's logic
        }
    }








