package com.example.iq_flow_one_zero.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iq_flow_one_zero.data.FlashcardSet
import com.example.iq_flow_one_zero.data.mainFlashcardList
import com.example.iq_flow_one_zero.data.tlgi_flashcards
import com.example.iq_flow_one_zero.ui.DisplayViewModel
import com.example.iq_flow_one_zero.ui.screens.DeckDetailsScreen
import com.example.iq_flow_one_zero.ui.screens.FlashCardTest
import com.example.iq_flow_one_zero.ui.screens.FlashcardListScreen
import com.example.iq_flow_one_zero.ui.screens.LibraryScreen
import com.example.iq_flow_one_zero.ui.screens.PersonalScreen
import com.example.iq_flow_one_zero.ui.screens.StatisticsScreen

@Composable
fun NavigationHost(
    singleFlashcardSet: FlashcardSet = mainFlashcardList[0],
    displayViewModel: DisplayViewModel,
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
) {
    val displayUiState by displayViewModel.uiState.collectAsState()

    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route
//                enterTransition = { EnterTransition.None},
//                exitTransition = { ExitTransition.None}
            ){
                when (destination) {
                    Destination.List -> FlashcardListScreen(
                        onFlashcardsetClicked =     {
                            navController.navigate(DetailScreens.DECK_DETAILS.name)
                            displayViewModel.updateCurrentlyLearningFlashcards(it)
                        })
                    Destination.Library -> LibraryScreen(contentPadding = contentPadding)
                    Destination.Statistics -> StatisticsScreen(contentPadding = contentPadding)
                    Destination.Personal -> PersonalScreen(contentPadding = contentPadding)
                }
            }
        }
        composable(route = DetailScreens.DECK_DETAILS.name) {
            DeckDetailsScreen(
                onStartLearningClicked = {
                    navController.navigate(DetailScreens.CARD_REVIEW.name)
                },
                flashcardsList = displayUiState.nameOfCurrentlyLearningFlashcardSet
            )
        }
        composable(route = DetailScreens.CARD_REVIEW.name,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {

            FlashCardTest(currentDisplayedCard = displayUiState.currentFlashcardId,
                onNextButtonClicked = { displayViewModel.updateFlashcardState() },
                onBackButtonClicked = {},
                backIsVisible = displayUiState.isBacksideShown,
                contentPadding = contentPadding,
                flashcardSet = displayUiState.nameOfCurrentlyLearningFlashcardSet,
                modifier = Modifier)
        }
    }
}