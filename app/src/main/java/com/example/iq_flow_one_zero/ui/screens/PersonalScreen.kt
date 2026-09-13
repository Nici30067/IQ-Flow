package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PersonalScreen(modifier: Modifier = Modifier,
                   contentPadding: PaddingValues = PaddingValues(0.dp)){
    Column(modifier = Modifier.fillMaxSize()
        .padding(contentPadding)) {
        Card(modifier.fillMaxHeight(0.3f)
            .fillMaxWidth()
            .padding(12.dp)) {
            Text("Design",
                fontSize = 30.sp,
                modifier = Modifier.padding(12.dp))
            Row(modifier = modifier.padding(12.dp)) {
                Text(text = "dark mode",
                    modifier = modifier
                        .padding(horizontal = 8.dp))
                Text(text = "light mode",
                    modifier = modifier
                        .padding(horizontal = 8.dp))
                Text(text = "System",
                    modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@Composable
@Preview
fun PersonalScreenPreview(){
    PersonalScreen()
}