package com.example.iq_flow_one_zero.ui
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.compose.rememberNavController
import com.example.iq_flow_one_zero.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.iq_flow_one_zero.navigation.Destination
import com.example.iq_flow_one_zero.navigation.DetailScreens
import com.example.iq_flow_one_zero.data.tlgi_flashcards
import com.example.iq_flow_one_zero.ui.screens.FlashCardTest
import com.example.iq_flow_one_zero.navigation.NavigationBar
import com.example.iq_flow_one_zero.navigation.NavigationHost


@Composable
fun FlashcardApp(modifier: Modifier = Modifier,
                 displayViewModel: DisplayViewModel = viewModel(factory = DisplayViewModel.factory),
                 navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack: Boolean = backStackEntry?.destination?.route != Destination.List.name
    val startDestination = Destination.List

    val homeUiState by displayViewModel.homeUiState.collectAsState()

//    val currentScreen = backStackEntry?.destination?: error("Top level nav item not found!")
//        Log.d("error now", "der FEhler liegt bie${currentScreen?.route}, der screentitle sollte ${currentScreen}")
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
            flashcardList = homeUiState.flashcardList,
            displayViewModel = displayViewModel,
            navController = navController,
            startDestination = startDestination,
            contentPadding = it,
            modifier = Modifier.fillMaxSize()
               )
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
    FlashCardTest(onBackButtonClicked = {},
        flashcardSet = tlgi_flashcards
    )
}
@Preview
@Composable
fun FlashcardAppPreview(){
    FlashcardApp()
}