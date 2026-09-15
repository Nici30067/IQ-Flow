package com.example.iq_flow_one_zero.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardWithInnerPadding(cardHeight: Float, text: String, modifier: Modifier.Companion){
    Card(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight(cardHeight)
        .padding(12.dp)) {
        Text(text = text, modifier = Modifier.padding(8.dp), fontSize = 30.sp)
    }
}