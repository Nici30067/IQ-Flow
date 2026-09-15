package com.example.iq_flow_one_zero.ui
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iq_flow_one_zero.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.DefaultNavTransitions.enterTransition
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.iq_flow_one_zero.data.Destination
import com.example.iq_flow_one_zero.data.DetailScreens
import com.example.iq_flow_one_zero.data.tlgi_flashcards
import com.example.iq_flow_one_zero.ui.screens.DeckDetailsScreen
import com.example.iq_flow_one_zero.ui.screens.FlashCardTest
import com.example.iq_flow_one_zero.ui.screens.FlashcardListScreen
import com.example.iq_flow_one_zero.ui.screens.LibraryScreen
import com.example.iq_flow_one_zero.ui.screens.StatisticsScreen
import com.example.iq_flow_one_zero.ui.screens.PersonalScreen


@Composable
fun FlashcardApp(modifier: Modifier = Modifier,
                 displayViewModel: DisplayViewModel = viewModel(),
                 navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack: Boolean = backStackEntry?.destination?.route != Destination.List.name
    val startDestination = Destination.List

    Scaffold(topBar = {
        FlashCardAppTopBar( canNavigateBack = canNavigateBack,
                            navigateUp = { navController.navigateUp() })
                      },
        bottomBar = {
            if (navController.currentBackStackEntry?.destination?.route != DetailScreens.CARD_REVIEW.name) {
                NavigationBar(modifier = Modifier,
                    startDestination = startDestination,
                    navController = navController,
                    displayViewModel = displayViewModel)
            }
        },
        modifier = Modifier.fillMaxSize()
    ){
        NavigationHost(
            displayViewModel = displayViewModel,
            navController = navController,
            startDestination = startDestination,
            contentPadding = it,
            modifier = Modifier.fillMaxSize()
               )
    }
}


@Composable
fun NavigationBar(
    displayViewModel: DisplayViewModel,
                  startDestination: Destination,
                  navController: NavHostController,
                  modifier: Modifier = Modifier) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal)}

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
}
@Composable
fun NavigationHost(
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
            composable(destination.route,
                enterTransition = { EnterTransition.None},
                exitTransition = { ExitTransition.None}) {
                when (destination) {
                    Destination.List -> FlashcardListScreen(
                    onFlashcardsetClicked =     {
                        navController.navigate(DetailScreens.DECK_DETAILS.name)
//                        displayViewModel.updateCurrentlyLearningFlashcards(it)
                    }
                    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardAppTopBar(canNavigateBack: Boolean,
                       navigateUp: () -> Unit,
                       modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 30.sp

            )
        },
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        modifier = modifier
    )
}




@Preview(showBackground = false)
@Composable
fun FlashCardPreview(){
    FlashCardTest(onNextButtonClicked = {},
        onBackButtonClicked = {},
        flashcardSet = tlgi_flashcards
    )
}
@Preview
@Composable
fun FlashcardAppPreview(){
    FlashcardApp()
}