package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iq_flow_one_zero.ui.DisplayUiState

@Composable
fun PersonalScreen(modifier: Modifier = Modifier,
                   selectDesign: (Boolean) -> Unit = {},
                   contentPadding: PaddingValues = PaddingValues(0.dp),
                   uiState: DisplayUiState = DisplayUiState()){

    val isDarkMode = uiState.isDarkMode

     Column(modifier = Modifier.fillMaxSize()
        .padding(contentPadding)) {
        Card(modifier.fillMaxHeight(0.3f)
            .fillMaxWidth()
            .padding(12.dp)) {
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text("Dark mode enabled",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(12.dp))

                Switch(
                    checked = isDarkMode,
                    onCheckedChange = {selectDesign(!isDarkMode)
                    },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun PersonalScreenPreview(){
    PersonalScreen()
}