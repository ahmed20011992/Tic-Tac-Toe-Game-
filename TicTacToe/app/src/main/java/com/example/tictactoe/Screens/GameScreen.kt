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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.Viewmodel.GameViewModel
import com.example.tictactoe.Viewmodel.MainViewModel
import okio.blackholeSink
import org.w3c.dom.Text




@Composable
fun GameScreen(viewModel: MainViewModel = viewModel(),navController: NavController = rememberNavController()) {

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
                items(9){
                    squareView()
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
fun squareView(){
     val viewModel: MainViewModel = viewModel()
   //val viewModel : GameViewModel = viewModel()
    Button(
        onClick = { viewModel.changeBackGroundColr()},
        modifier = Modifier.aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        shape = RectangleShape,


        ) {
        Text(text = "click")
        
    }


}



