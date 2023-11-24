package com.example.tictactoe.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.Viewmodel.LobbyViewModel
import com.example.tictactoe.Viewmodel.MainViewModel
import io.garrit.android.multiplayer.Player
import java.util.Collections.list
import java.util.UUID

@Composable
fun LobbyScreen(navController: NavController = rememberNavController(), lobbyViewModel: LobbyViewModel= viewModel()){
Column (
    modifier = Modifier
        .fillMaxSize()
) {

    listView(players = lobbyViewModel.players)
    }
}

@Composable
fun listView(players: SnapshotStateList<Player>){
    LazyColumn(){

    }

}

@Composable
fun LobbyView(player: Player){
    Text(text = player.name )

}

