package com.example.ob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ob.data.Card
import com.example.ob.ui.theme.ObTheme
import com.example.ob.utils.GRID_SIZE
import com.example.ob.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            viewModel.let {
                it.getCards()
                it.getSfxs()
                it.playSound(R.raw.matchthepictures)
            }
            ObTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                ) {
                    SetupGame(viewModel)
                }
            }
        }
    }
}

@Composable
fun SetupGame(viewModel: GameViewModel) {
    val state = viewModel.cards.collectAsStateWithLifecycle().value
    val sfxs = viewModel.sfxs.collectAsStateWithLifecycle().value
    val isGameCompleted = viewModel.isGameCompleted.collectAsStateWithLifecycle().value

    if (!isGameCompleted) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center
        ) {
            LoadPhotos(cards = state, viewModel = viewModel)
            LoadSfx(sfxs = sfxs, viewModel = viewModel)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    R.drawable.star
                ), contentDescription = ""
            )
        }
    }
}


@Composable
fun LoadPhotos(modifier: Modifier = Modifier, cards: List<Card>, viewModel: GameViewModel) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(GRID_SIZE)
    ) {
        items(cards) { card ->
            CardItem(card, viewModel) {
                viewModel.selectCard(it)
            }
        }
    }
}

@Composable
fun LoadSfx(modifier: Modifier = Modifier, sfxs: List<Card>, viewModel: GameViewModel) {
    LazyVerticalGrid(
        modifier = modifier.padding(top = 10.dp),
        columns = GridCells.Fixed(GRID_SIZE)
    ) {
        items(sfxs) { card ->
            CardItem(card, viewModel) {
                viewModel.selectCard(it)
            }
        }
    }
}

@Composable
fun CardItem(item: Card, viewmodel: GameViewModel, onClick: (Card) -> Unit) {
    if (!item.isHidden) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(5.dp)
                .clickable { onClick(item) },
            border = BorderStroke(
                2.dp, if (item.isSelected) {
                    colorResource(R.color.golden)
                } else {
                    colorResource(R.color.light_blue)
                }
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Image(
                modifier = Modifier.background(
                    if (item.isMatched) {
                        colorResource(R.color.light_blue)
                    } else {
                        Color.White
                    }
                ),
                painter = painterResource(
                    if (item.isMatched) {
                        R.drawable.star
                    } else {
                        item.imageRes
                    }
                ),
                contentDescription = null,
            )

            if (item.isMatched) {
                LaunchedEffect(item) {
                    delay(500)
                    viewmodel.hideCard(item)
                }
            }
        }
    } else {
        Spacer(Modifier.width(30.dp))
    }
}