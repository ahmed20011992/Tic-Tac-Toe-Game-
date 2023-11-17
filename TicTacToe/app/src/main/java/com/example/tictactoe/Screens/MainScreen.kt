package com.example.tictactoe.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.Viewmodel.MainViewModel
import io.garrit.android.multiplayer.Player
import java.util.UUID

@Composable
fun MainScreen(navController: NavController = rememberNavController(),mainViewModel: MainViewModel= viewModel()){
    var name by remember {
        mutableStateOf("")
    }
    Column (
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = "TIC TAC TOE GAME")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = name, onValueChange = {
            name=it
        })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            mainViewModel.joinLobby(Player(UUID.randomUUID().toString(),name))
            navController.navigate(Screen.Game.route)
        }) {
            Text(text = "Start Game")
        }
    }
}