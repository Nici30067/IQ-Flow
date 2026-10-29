package com.example.iq_flow_one_zero.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iq_flow_one_zero.data.FlashcardSet

@Composable
fun FlashcardSetButton(
    onFlashcardsetClicked: (String) -> Unit,
    textOnButton: String
) {
    Button(
        colors = ButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.error,
            disabledContainerColor = MaterialTheme.colorScheme.error
        ),
        onClick = { onFlashcardsetClicked(textOnButton) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(color = MaterialTheme.colorScheme.onPrimary,
            text = textOnButton,
            modifier = Modifier.padding(12.dp)
        )
    }
}

//TODO("weitere Korrekture der Farben notwendig")

@Preview
@Composable
fun FlashcardSetButtonPreview(){
    FlashcardSetButton(
        onFlashcardsetClicked = {},
    textOnButton = "sldkjf"
    )
}