package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iq_flow_one_zero.data.mainFlashcardList

@Composable
fun FlashcardListScreen(onFlashcardsetClicked: () -> Unit,
                        contentPadding: PaddingValues = PaddingValues(0.dp)
                        ) {

    Column(modifier = Modifier.fillMaxSize()
        .padding(contentPadding)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .weight(1f)
        ) {

            Card(
                elevation = CardDefaults.elevatedCardElevation(3.dp),
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
                    .padding(20.dp)
                //                    .clickable(enabled = true, onClick = onFlashcardsetClicked)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    items(mainFlashcardList) { flashcards ->
                        Button(
                            onClick = onFlashcardsetClicked,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Text(
                                text = stringResource(flashcards.flashcardListName),
                                modifier = Modifier.padding(12.dp)
                            )
                        }

                    }

                }
            }
        }
//        FlashcardNavigationBar()
    }
}



@Preview
@Composable
fun FlashcardListPreview(){
    FlashcardListScreen(onFlashcardsetClicked = {})
}