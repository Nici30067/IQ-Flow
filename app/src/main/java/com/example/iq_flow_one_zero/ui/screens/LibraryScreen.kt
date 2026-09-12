package com.example.iq_flow_one_zero.ui.screens

import android.util.Log.i
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarState
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iq_flow_one_zero.R
import kotlinx.coroutines.launch

data class CarouselItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    val contentDescriptionRes: String,
)

val items =
    listOf(
        CarouselItem(0, R.drawable.gimp, "tempString"),
        CarouselItem(1, R.drawable.gimp, "tempstring"),
        CarouselItem(2, R.drawable.gimp, "tempstring"),
        CarouselItem(3, R.drawable.gimp, "tempstring"),
        CarouselItem(4, R.drawable.gimp, "tempstring"),
    )




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center) {

        val state = rememberCarouselState { items.count() }
        val animationScope = rememberCoroutineScope()
        HorizontalCenteredHeroCarousel(
            state = state,
            modifier = Modifier.fillMaxWidth().height(221.dp).padding(horizontal = 24.dp),
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) { i ->
            val item = items[i]
            Image(
                modifier =
                    Modifier.fillMaxWidth()
                        .height(205.dp)
                        .maskClip(MaterialTheme.shapes.extraLarge)
                        .clickable(true, "Tap to focus", Role.Image) {
                            animationScope.launch { state.animateScrollToItem(i) }
                        },
                painter = painterResource(id = item.imageResId),
                contentDescription = item.contentDescriptionRes,
                contentScale = ContentScale.Crop,
            )
        }
    }
}


@Preview
@Composable
fun LibraryScreenPreview(){
    LibraryScreen()
}