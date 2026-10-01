package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.iq_flow_one_zero.data.FlashcardSet
import com.example.iq_flow_one_zero.data.mainFlashcardList
import com.example.iq_flow_one_zero.ui.DisplayViewModel
import com.example.iq_flow_one_zero.ui.components.DialogWithTextField
import com.example.iq_flow_one_zero.ui.components.FlashcardSetButton

@Composable
fun FlashcardListScreen(
    flashcardSets: List<FlashcardSet>,
    onFlashcardsetClicked: (String) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onAddFlashcardSetClicked: () -> Unit,
    isAddDecksDialogShown: Boolean,
    onDismissRequestAction: () -> Unit

) {
    if (isAddDecksDialogShown) {
        Box(){
            DialogWithTextField(
                onDismissRequest = onDismissRequestAction,
                onConfirmation = {}
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {

            Card(
                elevation = CardDefaults.elevatedCardElevation(3.dp),
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    items(flashcardSets) { flashcardSet ->
                        FlashcardSetButton(
                            onFlashcardsetClicked = onFlashcardsetClicked,
                            textOnButton = stringResource(flashcardSet.flashcardListName)
                        )
                        Button(
                            onClick = onAddFlashcardSetClicked,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "",
                                modifier = Modifier.padding(12.dp))
                        }

                    }

                }

            }
        }
    }
}



@Preview
@Composable
fun FlashcardListPreview(){
    FlashcardListScreen(
        flashcardSets = mainFlashcardList,
        onFlashcardsetClicked = {},
        onAddFlashcardSetClicked = {},
        isAddDecksDialogShown = true,
        onDismissRequestAction = {}
    )
}