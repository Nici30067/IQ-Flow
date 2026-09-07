package com.example.iq_flow_one_zero.ui
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iq_flow_one_zero.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlin.system.measureTimeMillis


enum class FlashCardAppScreen{
    SET_LIST,
    REVIEW
}
@Composable
fun FlashcardApp(displayViewModel: DisplayViewModel = viewModel(),
                 navController: NavHostController = rememberNavController()){

    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack: Boolean = backStackEntry?.destination?.route != FlashCardAppScreen.SET_LIST.name
    Scaffold(topBar = {
        FlashCardAppTopBar(canNavigateBack = canNavigateBack,
            navigateUp = { navController.navigateUp() }) },
        bottomBar = {

        }
    ){ innerPadding ->
        val displayUiState by displayViewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = FlashCardAppScreen.SET_LIST.name,
            modifier = Modifier.padding(innerPadding)
        ){

            composable(route = FlashCardAppScreen.SET_LIST.name,
                enterTransition = { scaleIn(animationSpec = tween(150)) },
                exitTransition = { scaleOut(animationSpec = tween(200)) }
            ) {
                FlashcardList(
                    onFlashcardsetClicked = {navController.navigate(FlashCardAppScreen.REVIEW.name)}
                )
            }
            composable(route = FlashCardAppScreen.REVIEW.name) {
                FlashCardTest(currentDisplayedCard = displayUiState.currentFlashcardId,
                    onNextButtonClicked = { displayViewModel.updateFlashcardState() },
                    onBackButtonClicked = {},
                    backIsVisible = displayUiState.isBacksideShown,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardAppTopBar(canNavigateBack: Boolean,
                       navigateUp: () -> Unit) {
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
        }

    )
}
@Composable
fun FlashcardNavigationBar() {
//    BottomAppBar() {
//        NavigationBarItem(
//            selected = it,
//            onClick = {},
//            icon =
//
//        )
//    }


    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val items = listOf("Liste", "Bibliothek", "Statistik", "Profil")
    val selectedIcons = listOf(Icons.AutoMirrored.Filled.List, Icons.Filled.Search,
        Icons.AutoMirrored.Filled.ShowChart, Icons.Filled.Person
    )


    NavigationBar(modifier = Modifier) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        selectedIcons[index] ,
                        contentDescription = item,
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun FlashCardPreview(){
    FlashCardTest(onNextButtonClicked = {}, onBackButtonClicked = {})
}
