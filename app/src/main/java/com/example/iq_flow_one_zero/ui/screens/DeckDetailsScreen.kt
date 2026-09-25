package com.example.iq_flow_one_zero.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import com.example.iq_flow_one_zero.data.Flashcard
import com.example.iq_flow_one_zero.data.tlgi_flashcards

@Composable
fun DeckDetailsScreen(
    flashcardSetName: String,
    onAddNewCardsClicked: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    flashcardsList: List<Flashcard>,
    onStartLearningClicked: () -> Unit,
){
        Column(modifier = Modifier.fillMaxSize()
            .padding(contentPadding)
            .padding(12.dp)) {

            Card(modifier = Modifier.align(Alignment.End)
                .fillMaxWidth()
                .fillMaxHeight(0.3f)) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter) {
                    Button(onClick = onStartLearningClicked,
                        Modifier.fillMaxWidth(0.9f)
                            .fillMaxHeight(0.25f)
                            .padding(bottom = 12.dp)) {
                        Text(text = "Start Learning $flashcardSetName")
                    }
                }
            }
            LazyColumn(modifier = Modifier) {
                items(flashcardsList) { flashcard ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)) {
                        Text(text = flashcard.front, modifier = Modifier.padding(12.dp))
                        HorizontalDivider()
                        Text(
                            text = flashcard.back,
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                }
            }
            Row() {
                Spacer(modifier = Modifier.weight(1f))
                FloatingActionButton(onClick = onAddNewCardsClicked,
                    containerColor = Color.Red,
                    contentColor = MaterialTheme.colorScheme.onPrimary) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "sldkj")
            }
        }
    }
}
@Preview
@Composable
fun DeckDetailsScreenPreview(){
    DeckDetailsScreen(
        flashcardsList = tlgi_flashcards,
        onStartLearningClicked = {},
        flashcardSetName = "",
        onAddNewCardsClicked = {}
    )
}