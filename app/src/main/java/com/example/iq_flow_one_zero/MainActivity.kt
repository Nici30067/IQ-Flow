package com.example.iq_flow_one_zero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.iq_flow_one_zero.ui.FlashCardAppTopBar
import com.example.iq_flow_one_zero.ui.theme.IQFlow_one_zeroTheme
import com.example.iq_flow_one_zero.ui.FlashcardApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IQFlow_one_zeroTheme {
                    FlashcardApp()
                //das hier habe nur ich hier hin geschrieben
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IQFlow_one_zeroTheme {
        FlashcardApp()
    }
}

//future ideas:
//Surface composables. You can experiment with various values of Elevation,
// Color, and BorderStroke for Modifier.border to create different UIs
// within Surface composables.
//Spacing and alignment. You can use Modifier arguments, such as padding and
// weight, to help with the composables arrangement.