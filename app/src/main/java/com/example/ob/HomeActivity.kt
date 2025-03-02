package com.example.ob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ob.data.Card
import com.example.ob.ui.theme.ObTheme
import com.example.ob.utils.GRID_SIZE

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    setupGame()
                }
            }
        }
    }
}

fun setupGame() {

}

@Preview
@Composable
fun PreviewGrids(modifier: Modifier = Modifier) {
    GameScreen(modifier)
}

@Composable
fun GameScreen(modifier: Modifier = Modifier, cards: List<Card>) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        columns = GridCells.Fixed(GRID_SIZE)
    ) {
        items(10) { it ->
            CardItem {

            }
        }
    }
}

@Composable
fun CardItem(onClick: () -> Unit, item: Card) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp)
            .clickable { onClick() },
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
            painter = painterResource(id = R.drawable.aubergine),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun previewCardItem() {
    CardItem {

    }
}