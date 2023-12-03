
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tictactoe.R
import com.example.tictactoe.Screens.Screen
import com.example.tictactoe.Viewmodel.LobbyViewModel
import io.garrit.android.multiplayer.Game
import io.garrit.android.multiplayer.Player

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(navController: NavController, lobbyViewModel: LobbyViewModel = viewModel()) {
    val serverState = lobbyViewModel.server.collectAsState().value

    if (lobbyViewModel.server.collectAsState().value.toString() == "GAME") {
        navController.navigate(Screen.Game.route)
    }

    val invite = lobbyViewModel.findInvite()
    if (invite != null) {
        InviteReq(lobbyViewModel = lobbyViewModel, game = invite)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        topBar = { LobbyTopAppBar() },
        bottomBar = { LobbyBottomAppBar() },
        content = {
                padd ->
            Box {
                Image(
                    painter = painterResource(id = R.drawable.tic_back_ground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

            }

            LobbyContent(lobbyViewModel, padd) }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyTopAppBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
        title = { Text(text = "Tic Tac Toe", fontWeight = FontWeight.Bold) },
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
    BottomAppBar(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)) {

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
    Card(
        modifier = Modifier.run {
            fillMaxWidth()
                .height(80.dp)
                .clickable { lobbyViewModel.sendInvite(player) }
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium)
        },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = player.name,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}




@Composable
fun InviteReq(lobbyViewModel: LobbyViewModel, game: Game) {
    AlertDialog(
        onDismissRequest = { /* TODO */ },
        text = { Text(text = "Someone has invited you!", fontWeight = FontWeight.Bold) },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { lobbyViewModel.accept(game) }) {
                    Text(text = "Accept", fontWeight = FontWeight.Bold)
                }
                Button(onClick = { lobbyViewModel.diclin(game) }) {
                    Text(text = "Decline", fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}
