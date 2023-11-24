package com.example.tictactoe.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.garrit.android.multiplayer.Player
import io.garrit.android.multiplayer.SupabaseService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class MainViewModel : ViewModel(){
    var backgroundColor by mutableStateOf(Color.Gray)
    private set // här bara för att läsa inte ändra på
    fun changeBackGroundColr(){
       backgroundColor= Color.White
    }

    fun joinLobby(player: Player){
        viewModelScope.launch {
            try {
                SupabaseService.joinLobby(player)
            } catch (e: Exception) {
                // Log or handle the exception
                e.printStackTrace()
            }
        }

    }
}