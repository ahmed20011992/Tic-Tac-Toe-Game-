package com.example.tictactoe.Viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.garrit.android.multiplayer.Game
import io.garrit.android.multiplayer.Player
import io.garrit.android.multiplayer.SupabaseService
import kotlinx.coroutines.launch

class LobbyViewModel: ViewModel() {
    val players :MutableList<Player> = SupabaseService.users
    val games = SupabaseService.games
    val server = SupabaseService.serverState
    fun findInvite():Game?{
        var foundGame =games.find { it?.player2!!.id==SupabaseService.player!!.id }
        //(SupabaseService.player) based on the player's ID. If found, it returns the game; otherwise,
        // it returns null. "here is it that can be null"
        if(foundGame!=null){
            return foundGame
        }
        return null
    }
    fun sendInvite (player: Player)
    {
        viewModelScope.launch {
            SupabaseService.invite(player)
        }
    }
    fun accept(game: Game){
        viewModelScope.launch {
            SupabaseService.acceptInvite(game)
        }
    }
    fun diclin(game: Game){
        viewModelScope.launch {
            SupabaseService.declineInvite(game)
        }
    }
}