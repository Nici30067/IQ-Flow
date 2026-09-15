package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iq_flow_one_zero.data.Flashcard
import com.example.iq_flow_one_zero.data.tlgi_flashcards

@Composable
fun DeckDetailsScreen(flashcardsList:  List<Flashcard>,
                      onStartLearningClicked: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()
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
                    Text(text = "Start Learning")
                }
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(flashcardsList) { flashcard ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)) {
                    Text(text = stringResource(flashcard.front), modifier = Modifier.padding(12.dp))
                    HorizontalDivider()
                    Text(
                        text = stringResource(flashcard.back),
                        modifier = Modifier.padding(12.dp)
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun DeckDetailsScreenPreview(){
    DeckDetailsScreen(tlgi_flashcards,
        onStartLearningClicked = {})
}