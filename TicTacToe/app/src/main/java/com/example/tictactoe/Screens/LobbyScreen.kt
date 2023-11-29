package com.example.tictactoe.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.Viewmodel.LobbyViewModel
import io.garrit.android.multiplayer.Game
import io.garrit.android.multiplayer.Player

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(navController: NavController , lobbyViewModel: LobbyViewModel= viewModel()){
    if(lobbyViewModel.server.collectAsState().value.toString()=="GAME"){
        navController.navigate(Screen.Game.route)
    }
    if (lobbyViewModel.findInvite()!=null){
        InviteReq(lobbyViewModel = lobbyViewModel, game = lobbyViewModel.findInvite()!!)
    }
Scaffold (
    modifier = Modifier
        .fillMaxSize()
    , topBar = {
        TopAppBar(title = { /*TODO*/ })
    },
    bottomBar = {
        BottomAppBar {

        }
    },
    content = {padd ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padd)
        ){
            items(lobbyViewModel.players){player ->
                LobbyView(player = player,lobbyViewModel)
            }
        }
    }
)

}

@Composable
fun LobbyView(player: Player,lobbyViewModel: LobbyViewModel){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clickable {
                lobbyViewModel.sendInvite(player)
            }
    ){
        Text(text = player.name)
    }
}
@Composable
fun InviteReq(lobbyViewModel: LobbyViewModel,game: Game){
    AlertDialog(onDismissRequest = { /*TODO*/ }
        , text = { Text(text = "some one invite you!")}
        , confirmButton = {
    Button(onClick = {lobbyViewModel.accept(game) }) {
        Text(text = "Confirm")
    }
        Button(onClick = { lobbyViewModel.diclin(game) }) {
            Text(text = "Cancel")
        }
    })
}
