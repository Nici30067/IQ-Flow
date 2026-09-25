package com.example.iq_flow_one_zero.ui.screens.secondaryScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FlashcardAddScreen(onAddNewCardsClicked: (front: String, back: String) -> Unit){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {

        var frontInput by remember {  mutableStateOf("") }
        var backInput by remember {  mutableStateOf("") }
        Text(text = "neue karteikarten adden",
            fontSize = 30.sp,
            modifier = Modifier.padding(12.dp))


        OutlinedTextField(value = frontInput,
            label = {Text("Vorderseite")},
            onValueChange = {frontInput = it},
            modifier = Modifier.padding(12.dp))
        OutlinedTextField(value = backInput,
            label = {Text("Rückseite")},
            onValueChange = {backInput = it},
            modifier = Modifier.padding(12.dp))
        Button(onClick = {
            onAddNewCardsClicked(frontInput, backInput)
        }) {
            Text(text = "Karte hinzufügen")
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun FlashcardAddScreenPreview(){
//    FlashcardAddScreen(onAddNewCardsClicked = {})
//}