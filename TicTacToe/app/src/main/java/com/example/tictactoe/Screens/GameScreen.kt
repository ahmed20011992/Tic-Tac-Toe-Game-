package com.example.tictactoe.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tictactoe.Viewmodel.GameViewModel
import com.example.tictactoe.Viewmodel.LobbyViewModel
import com.example.tictactoe.Viewmodel.MainViewModel
import com.example.tictactoe.Viewmodel.SquareValue
import io.garrit.android.multiplayer.Player
import java.util.UUID


@Composable
fun GameScreen(name11: LobbyViewModel =viewModel(), viewModel: GameViewModel = viewModel(), navController: NavController) {
    val winnerState = viewModel.theWinner.collectAsState()
    val playerTurn = if(viewModel.isPlayerXTurn.value) viewModel.me else viewModel.oponent
    var playMore = false

     // den här kan ändras när det händer en recompose playerTurn ta en ny value varje gong


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
        text = "Player X Score: ${viewModel.playerXScore}",
        fontSize = 18.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,  fontFamily = FontFamily.Cursive,

        modifier = Modifier.padding(8.dp)
    )
        Text( text = "Player O Score: ${viewModel.playerOScore}",
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive,

            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            gobackToLobby(navController = navController)


            if (viewModel.winner != null) {

                Text(
                     //text = "Player ${viewModel.winner.name} has won!", fontFamily = FontFamily.Cursive,
                    text = "Player ${viewModel.winner!!.name} has won!", fontFamily = FontFamily.Cursive,
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                ///navController.navigate(Screen.Main.route)

            }


        }
       Text(
           text = "<<X>> VS <<O>>",
           fontSize = 45.sp,
           fontWeight = FontWeight.Bold,
           fontFamily = FontFamily.Cursive,
           color = Color.Black,
           modifier = Modifier.graphicsLayer {  }

       )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp),

                    )
                .clip(RoundedCornerShape(20.dp))
                .background(viewModel.backgroundColor),
            contentAlignment = Alignment.Center

        ){
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
                ){
                items(viewModel.board){sq ->
                    squareView(viewModel = viewModel,  sq, isPlayerXTurn = viewModel.isPlayerXTurn.value)
                }




            }

        }
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ){
            Text(fontSize = 25.sp,
                fontStyle = FontStyle.Italic,
                text = "It's ${playerTurn!!.name}'s turn"
                )

            Button(
                onClick = {
                    viewModel.resetGame()
                    viewModel.oponentReset()
                    viewModel.gameIsfinish= false
                    playMore = true
                    viewModel.sendReady()


                },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(),
                colors = ButtonDefaults.buttonColors(),
                ///enabled = viewModel.isReady.value && viewModel.isOpReady.value

            ) {
                Text(text = "Ready")


            }

        }
    }
    if (playMore){
        Box(modifier = Modifier.fillMaxSize()){
            Text(text = "Do you want to palay More!!")

        }}
}

@Composable
fun squareView(viewModel: GameViewModel, sq: SquareValue, isPlayerXTurn: Boolean){

    Button(
        onClick = { viewModel.sqClicked(sq) },// Efter klicket, meddela GameViewModel att ett drag har gjorts
        modifier = Modifier.aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        shape = RectangleShape,
        enabled = isPlayerXTurn, // Här reglerar du klickbarheten baserat på turordningen
        //enabled = viewModel.isPlayerXTurn.value && viewModel.isReady.value && viewModel.isOpReady.value

        ) {
        Text(text = sq.value)
        
    }
    println("isPlayerXTrun: $isPlayerXTurn $sq")


}
@Composable
fun gobackToLobby(navController: NavController ,mainViewModel: MainViewModel = viewModel(), kk : GameViewModel = viewModel()){
    var name by remember {
        mutableStateOf("")
    }
    Button(onClick = {
        //SupabaseService.gameFinish(GameResult.SURRENDER)
        kk.xxx()
        mainViewModel.joinLobby(Player(UUID.randomUUID().toString(),name))
        navController.navigate(Screen.Lobby.route)
    }){
        Text(text = "Leave-->>")
    }

}
@Composable
fun theGameISFinish(){

}

