package com.example.iq_flow_one_zero.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
import com.example.iq_flow_one_zero.ui.screens.secondaryScreens.FlashcardAddScreen
import kotlinx.coroutines.launch

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
    val coroutineScope = rememberCoroutineScope()


    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(
                destination.route
//                enterTransition = { EnterTransition.None},
//                exitTransition = { ExitTransition.None}
            ) {
                when (destination) {
                    Destination.List -> FlashcardListScreen(
                        flashcardSets = mainFlashcardList,
                        onFlashcardsetClicked = { flashcardSetNameAsString ->
                            navController.navigate("${DetailScreens.DECK_DETAILS.name}/$flashcardSetNameAsString")
//                            displayViewModel.updateCurrentlyLearningFlashcards(it)
                        })

                    Destination.Library -> LibraryScreen(contentPadding = contentPadding)
                    Destination.Statistics -> StatisticsScreen(contentPadding = contentPadding)
                    Destination.Personal -> PersonalScreen(contentPadding = contentPadding)
                }
            }
        }
        val flashcardNameArgument = "flashcardSetName"
        composable(
            route = DetailScreens.DECK_DETAILS.name + "/{$flashcardNameArgument}",
            arguments = listOf(navArgument(flashcardNameArgument) { type = NavType.StringType })
        ) { backStackEntry ->
            val flashcardSetName = backStackEntry.arguments?.getString(flashcardNameArgument)?: error("kann nicht null sein")
            DeckDetailsScreen(
                flashcardSetName = flashcardSetName,
                onStartLearningClicked = {
                    navController.navigate(DetailScreens.CARD_REVIEW.name)
                },
                onAddNewCardsClicked = {navController.navigate(DetailScreens.ADD_CARDS.name)},
                flashcardsList = displayUiState.nameOfCurrentlyLearningFlashcardSet,
                contentPadding = contentPadding

            )
        }
        composable(route = DetailScreens.ADD_CARDS.name) {
            FlashcardAddScreen(onAddNewCardsClicked = {front, back ->
                coroutineScope.launch {
                    displayViewModel.saveFlashcard(front, back)
                }
                navController.navigateUp()
            })
        }
        composable(
            route = DetailScreens.CARD_REVIEW.name,
//            enterTransition = { EnterTransition.None },
//            exitTransition = { ExitTransition.None }
        ) {

            FlashCardTest(
//                currentDisplayedCard = displayUiState.currentFlashcardId,
//                onSeeBacksideClicked = { displayViewModel.updateFlashcardState() },
                onBackButtonClicked = {},
                backIsVisible = displayUiState.isBacksideShown,
                contentPadding = contentPadding,
                flashcardSet = displayUiState.nameOfCurrentlyLearningFlashcardSet,
                modifier = Modifier
            )
        }
    }
}