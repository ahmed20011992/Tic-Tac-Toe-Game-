package com.example.tictactoe.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.garrit.android.multiplayer.Player
import io.garrit.android.multiplayer.SupabaseService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    fun joinLobby(player: Player){
        viewModelScope.launch {
            SupabaseService.joinLobby(player)
        }
    }
}