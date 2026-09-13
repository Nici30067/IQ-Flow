package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iq_flow_one_zero.ui.components.StatisticsCard

@Composable
fun StatisticsScreen(modifier: Modifier = Modifier,
                     contentPadding: PaddingValues = PaddingValues(0.dp)){
    Column(modifier = Modifier.fillMaxSize()
        .padding(contentPadding)){
        StatisticsCard(0.3f, "Statistics", modifier = Modifier)
        StatisticsCard(0.3f, "Statistics", modifier = Modifier)
    }
}

@Preview
@Composable
fun StatisticsScreenPreview(){
    StatisticsScreen()
}