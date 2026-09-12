package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
        Card(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.3f)
            .padding(12.dp)) {
            Text(text = "staaaats")
        }
    }
}

@Preview
@Composable
fun StatisticsScreenPreview(){
    StatisticsScreen()
}