package com.example.tictactoe.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tictactoe.Viewmodel.LobbyViewModel
import io.garrit.android.multiplayer.Game
import io.garrit.android.multiplayer.Player

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(navController: NavController, lobbyViewModel: LobbyViewModel = viewModel()) {
    val serverState = lobbyViewModel.server.collectAsState().value

    if(lobbyViewModel.server.collectAsState().value.toString()=="GAME") {
        navController.navigate(Screen.Game.route)
    }

    val invite = lobbyViewModel.findInvite()
    if (invite != null) {
        InviteReq(lobbyViewModel = lobbyViewModel, game = invite)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        topBar = { LobbyTopAppBar() },
        bottomBar = { LobbyBottomAppBar() },
        content = { padd -> LobbyContent(lobbyViewModel, padd) }
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyTopAppBar() {
    TopAppBar(
        title = { Text(text = "Tic Tac Toe") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        }
    )
}

@Composable
fun LobbyBottomAppBar() {
    BottomAppBar {
        // Bottom app bar content
    }
}

@Composable
fun LobbyContent(lobbyViewModel: LobbyViewModel, padd: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padd),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(lobbyViewModel.players) { player ->
                LobbyView(player = player, lobbyViewModel = lobbyViewModel)
            }
        }
    }
}

@Composable
fun LobbyView(player: Player, lobbyViewModel: LobbyViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clickable { lobbyViewModel.sendInvite(player) }
            .background(Color.Yellow)
    ) {
        Text(text = player.name)
    }
}

@Composable
fun InviteReq(lobbyViewModel: LobbyViewModel, game: Game) {
    AlertDialog(
        onDismissRequest = { /* TODO */ },
        text = { Text(text = "Someone has invited you!") },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { lobbyViewModel.accept(game) }) {
                    Text(text = "Accept")
                }
                Button(onClick = { lobbyViewModel.diclin(game) }) {
                    Text(text = "Decline")
                }
            }
        }
    )
}
