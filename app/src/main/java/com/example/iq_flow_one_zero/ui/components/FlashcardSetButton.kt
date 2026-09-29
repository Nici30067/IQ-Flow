package com.example.iq_flow_one_zero.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.iq_flow_one_zero.data.FlashcardSet

@Composable
fun FlashcardSetButton(
    onFlashcardsetClicked: (String) -> Unit,
    textOnButton: String
) {
    Button(
        onClick = { onFlashcardsetClicked(textOnButton) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = textOnButton,
            modifier = Modifier.padding(12.dp)
        )
    }

}