package com.example.ob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ob.data.Card
import com.example.ob.ui.theme.ObTheme
import com.example.ob.utils.GRID_SIZE
import com.example.ob.utils.Resource
import com.example.ob.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObTheme {
                Scaffold(modifier = Modifier.fillMaxSize().padding(2.dp)) {
                    SetupGame(viewModel)
                }
            }
        }
    }
}

@Composable
fun SetupGame(viewModel: GameViewModel) {
    with(viewModel) {
        getCards()
        val state = cards.observeAsState().value
        when (state) {
            is Resource.Error -> {}
            is Resource.Loading -> {}
            is Resource.Success -> {
                state.data?.let {
                    GameScreen(cards = it)
                }
            }

            null -> {}
        }
    }
}


@Composable
fun GameScreen(modifier: Modifier = Modifier, cards: List<Card>, viewModel: GameViewModel) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        columns = GridCells.Fixed(GRID_SIZE)
    ) {
        items(cards, key = { it.id }) {
            CardItem(it) {
                viewModel.
            }
        }
    }
}

@Composable
fun CardItem(item: Card, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp)
            .clickable { onClick(item.id) },
        border = BorderStroke(
            1.dp, if (item.isSelected) {
                Color.Green
            } else {
                Color.Blue
            }
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Image(
            painter = painterResource(item.imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}