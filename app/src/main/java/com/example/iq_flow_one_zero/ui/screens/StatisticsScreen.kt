package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StatisticsScreen(modifier: Modifier = Modifier,
                     contentPadding: PaddingValues = PaddingValues(0.dp)){
    Column(modifier = Modifier.fillMaxSize()
        .padding(contentPadding)){
        Text(text = "Stats")
        Text(text = "Stats2")
        Text(text = "Stats3")
        Text(text = "Stats4")
        Card(modifier = Modifier.size(89.dp)) { }
        //
        //STILL TODO
        //
    }
}

@Preview
@Composable
fun StatisticsScreenPreview(){
    StatisticsScreen()
}