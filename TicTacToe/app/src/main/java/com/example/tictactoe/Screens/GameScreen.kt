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
import com.example.tictactoe.Viewmodel.SquareValue


@Composable
fun GameScreen(viewModel : GameViewModel = viewModel(),navController: NavController ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = "Player X", fontSize = 16.sp)

            Text(text = "Player O", fontSize = 16.sp)

        }
       Text(
           text = "click your winning!",
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
                    squareView(viewModel = viewModel,  sq)
                }




            }

        }
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ){
            Text(text = "Player  'O' turn",
                fontSize = 25.sp,
                fontStyle = FontStyle.Italic
                )
            Button(
                onClick = { navController.navigate(Screen.Game.route) },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(),
                colors = ButtonDefaults.buttonColors(


                )

            ) {
                Text(text = "Playe More")

            }

        }
    }
}

@Composable
fun squareView(viewModel: GameViewModel, sq: SquareValue){

    Button(
        onClick = { viewModel.sqClicked(sq) },
        modifier = Modifier.aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        shape = RectangleShape,


        ) {
        Text(text = sq.value)
        
    }


}



