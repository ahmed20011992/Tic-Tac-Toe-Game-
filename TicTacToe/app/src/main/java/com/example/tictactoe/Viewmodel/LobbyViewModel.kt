package com.example.tictactoe.Viewmodel

import androidx.lifecycle.ViewModel
import io.garrit.android.multiplayer.SupabaseService

class LobbyViewModel: ViewModel() {
    val players = SupabaseService.users
}