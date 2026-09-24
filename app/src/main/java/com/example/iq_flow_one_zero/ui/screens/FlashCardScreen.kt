package com.example.iq_flow_one_zero.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.iq_flow_one_zero.data.Flashcard
import com.example.iq_flow_one_zero.data.tlgi_flashcards
import com.spartapps.swipeablecards.ui.SwipeableCardDirection
import com.spartapps.swipeablecards.ui.lazy.items

import com.spartapps.swipeablecards.state.rememberSwipeableCardsState
import com.spartapps.swipeablecards.ui.lazy.LazySwipeableCards


@Composable
fun FlashCardTest(
    modifier: Modifier = Modifier,
//    onSeeBacksideClicked: () -> Unit,
    backIsVisible: Boolean = false,
    onBackButtonClicked: () -> Unit,
    flashcardSet: List<Flashcard>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
){
//    val context = LocalContext.current        for vibrations
    val state = rememberSwipeableCardsState(itemCount = { flashcardSet.size })

    Column(modifier = modifier
        .fillMaxSize()
        .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        LazySwipeableCards(
            modifier = Modifier.padding(10.dp),
            state = state,
            onSwipe = {item, direction ->
                if(item.backsideIsVisible){
                    //allow swipe
                }
            }
        ) {
            items(flashcardSet){item, index, offset ->
                var backsideIsVVVisible by remember { mutableStateOf(false) }
                Flashcard(modifier = modifier,
            frontsideText = stringResource(item.front),
            backsideText = stringResource(item.back),
            backIsVisible = backsideIsVVVisible,
                    onSeeBacksideClicked = {backsideIsVVVisible = true})

            }
        }
        ReactionButtonRow(backIsVisible = backIsVisible)
    }

}


@Composable
fun ReactionButtonRow(modifier: Modifier = Modifier,
                      backIsVisible: Boolean
                      ){
    Row(modifier = modifier
        .fillMaxSize()
        .padding(20.dp), verticalAlignment = Alignment.Bottom) {
        OutlinedButton(onClick = {  }
            , modifier = modifier
                .weight(0.5f)
                .padding(end = 10.dp)
                .size(height = 50.dp, width = 80.dp)
        ) {
            Text(text = stringResource(R.string.see_previous_card))
        }
        ElevatedButton(onClick = {},
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

@Composable
fun Flashcard(modifier: Modifier = Modifier,
              frontsideText: String = "",
              backsideText: String = "",
              backIsVisible: Boolean = false,
              onSeeBacksideClicked: () -> Unit){
    Column(modifier = modifier
        .fillMaxWidth(0.9f)
        .fillMaxHeight(0.8f)
        .border(
            width = 2.dp,
            brush = Brush.radialGradient(
                listOf(Color(0xCCCCCCCC), Color(0xCCCCCCCC))
            ), shape = RoundedCornerShape(40.dp)
        )
//        .background(Color.Black)
//        .shadow(20.dp, RoundedCornerShape(40.dp), spotColor = Color.Blue)
        ,horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Card(modifier = modifier        //using a card as the column is somehow invisible and shows other cards at stack behind
            .fillMaxSize()
            .clickable(
                enabled = !backIsVisible,
                onClick = onSeeBacksideClicked
            )

        ) {
            Text(text = frontsideText,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
                , thickness = 2.dp, color = Color.DarkGray)
            Text(text = backsideText,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(20.dp)
                    .alpha(
                        if (backIsVisible) {
                            1f
                        } else {
                            0f
                        }
                    ))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashCardScreenPreview(){
    FlashCardTest(onBackButtonClicked = {},
        flashcardSet = tlgi_flashcards
    )
}