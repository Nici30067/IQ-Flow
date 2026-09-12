package com.example.iq_flow_one_zero.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iq_flow_one_zero.R
import com.example.iq_flow_one_zero.data.flashcards


@Composable
fun FlashCardTest(
    modifier: Modifier = Modifier,
    currentDisplayedCard: Int = 0,
    onNextButtonClicked: () -> Unit,
    backIsVisible: Boolean = false,
    onBackButtonClicked: () -> Unit
){
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Flashcard(modifier = modifier,
            frontsideText = stringResource(flashcards[currentDisplayedCard].front),
            backsideText = stringResource(flashcards[currentDisplayedCard].back),
            backIsVisible = backIsVisible)

        Row(modifier = modifier.fillMaxSize()
            .padding(20.dp), verticalAlignment = Alignment.Bottom) {
            OutlinedButton(onClick = {  }
                , modifier = modifier.weight(0.5f)
                    .padding(end = 10.dp)
                    .size(height = 50.dp, width = 80.dp)
            ) {
                Text(text = stringResource(R.string.see_previous_card))
            }
            ElevatedButton(onClick = onNextButtonClicked,
//                val vibrator = context.getSystemService(Vibrator::class.java)
//                vibrator?.vibrate(VibrationEffect.createOneShot(200, 30))
             modifier = modifier
                .weight(0.5f)
                .padding(start = 10.dp)
                .size(height = 50.dp, width = 80.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(6.dp),
                colors = ButtonDefaults.elevatedButtonColors(Color.White),
            ) {
                Text(text = stringResource(if(!backIsVisible){
                    R.string.button_get_answer
                }else{
                    R.string.button_next_card
                }))
            }
        }
    }

}

@Composable
fun Flashcard(modifier: Modifier = Modifier,
              frontsideText: String = "",
              backsideText: String = "",
              backIsVisible: Boolean = false){
    val gradientBrush =
        Brush.horizontalGradient(
            colors = listOf(Color.Red, Color.Blue, Color.Green),
            startX = 0.0f,
            endX = 50.0f,
            tileMode = TileMode.Mirror,
        )
    Column(modifier = modifier.fillMaxWidth(0.9f)
        .fillMaxHeight(0.8f)
//        .shadow(20.dp, RoundedCornerShape(40.dp), spotColor = Color.Blue)
        .border(width = 2.dp, brush = Brush.radialGradient(
            listOf(Color(0xCCCCCCCC), Color(0xCCCCCCCC))
        ), shape = RoundedCornerShape(40.dp)
        )
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = frontsideText,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.9f), thickness = 2.dp, color = Color.DarkGray)
        Text(text = backsideText,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp)
                .alpha(if(backIsVisible){
                    1f
                }else{
                    0f
                }))
    }

}

@Preview(showBackground = true)
@Composable
fun FlashCardScreenPreview(){
    FlashCardTest(onNextButtonClicked = {}, onBackButtonClicked = {})
}