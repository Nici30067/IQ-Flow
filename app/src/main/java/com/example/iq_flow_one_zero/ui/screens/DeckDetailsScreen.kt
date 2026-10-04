package com.example.iq_flow_one_zero.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iq_flow_one_zero.data.Flashcard
import com.example.iq_flow_one_zero.data.tlgi_flashcards

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DeckDetailsScreen(
    flashcardSetName: String,
    onAddNewCardsClicked: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    flashcardsList: List<Flashcard>,
    onStartLearningClicked: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNewCardsClicked,
                containerColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        },
        modifier = Modifier
            .padding(contentPadding)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
            //                .padding(it)          // this led to big gap between navBar and scrollableDetailsView

        ) {
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                InformationAndStartLearningBox(
                    onStartLearningClicked = onStartLearningClicked,
                    flashcardSetName = flashcardSetName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }

            if (flashcardsList.isEmpty()) {
                item() {
                    NoCardsAvailable()
                }

            } else {
                items(flashcardsList) { flashcard ->
                    FlashcardBulkDetailView(flashcard = flashcard)
                }
            }

        }
    }
}

@Composable
fun NoCardsAvailable(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            "Sieht ziemlich leer aus hier. Bitte füge Karteikarten hinzu, " +
                    "um mit dem lernen zu beginnen",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun InformationAndStartLearningBox(
    onStartLearningClicked: () -> Unit, flashcardSetName: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(220.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = onStartLearningClicked,
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.25f)
                    .padding(bottom = 12.dp)
            ) {
                Text(text = "Start Learning $flashcardSetName")
            }
        }
    }
}

@Composable
fun FlashcardBulkDetailView(flashcard: Flashcard) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = flashcard.front, modifier = Modifier.padding(12.dp))
        HorizontalDivider()
        Text(
            text = flashcard.back,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Preview
@Composable
fun DeckDetailsScreenPreview() {
    DeckDetailsScreen(
        flashcardsList = listOf(),//tlgi_flashcards
        onStartLearningClicked = {},
        flashcardSetName = "",
        onAddNewCardsClicked = {}
    )
}
