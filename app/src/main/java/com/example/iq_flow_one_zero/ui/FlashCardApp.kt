package com.example.iq_flow_one_zero.ui
import android.graphics.drawable.Icon
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlin.system.measureTimeMillis



enum class Destination(
    val route: String,
    val icon: ImageVector,
    val label: String,
    val contentDescription: String
){
    List("liste", Icons.Filled.ListAlt, label = "Liste", contentDescription = "null"),
    Library("library", Icons.Filled.Search, label = "Library", contentDescription = "null"),
    Statistics("statistics", Icons.Filled.Start, label = "Stats", contentDescription = "null"),
    Personal("personal", Icons.Filled.Person, label = "Personal", contentDescription = "null")
}

enum class DetailScreens(){
    CARD_REVIEW
}
@Composable
fun StatisticsScreen(modifier: Modifier = Modifier){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Stats")
        //
        //STILL TODO
        //
    }
}
@Composable
fun PersonalScreen(modifier: Modifier = Modifier){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Personal")
        //
        //STILL TODO
        //
    }
}
@Composable
fun FlashcardApp(displayViewModel: DisplayViewModel = viewModel(),
                 navController: NavHostController = rememberNavController()){

    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack: Boolean = backStackEntry?.destination?.route != Destination.List.name
    Scaffold(topBar = {
        FlashCardAppTopBar(canNavigateBack = canNavigateBack,
            navigateUp = { navController.navigateUp() }) },
        bottomBar = {

        }
    ){ innerPadding ->
        NavigationBar(modifier = Modifier.padding(innerPadding),
            navController = navController,
            displayViewModel = displayViewModel)
    }
}


@Composable
fun NavigationBar(displayViewModel: DisplayViewModel,
                  navController: NavHostController,
                  modifier: Modifier = Modifier) {

    val startDestination = Destination.List
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal)}

    Scaffold(
        modifier = modifier,
        bottomBar = {
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
    ) { contentPadding ->
        AppBottomNavHost(
            displayViewModel = displayViewModel,
        navController = navController,
           startDestination = startDestination,
            modifier = Modifier.padding(contentPadding))
    }
}
@Composable
fun AppBottomNavHost(
    displayViewModel: DisplayViewModel,
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    val displayUiState by displayViewModel.uiState.collectAsState()

    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.List -> FlashcardListScreen(
                        {navController.navigate(DetailScreens.CARD_REVIEW)}
                    )
                    Destination.Library -> LibraryScreen()
                    Destination.Statistics -> StatisticsScreen()
                    Destination.Personal -> PersonalScreen()
                    else -> {FlashcardListScreen({})}
                }
            }
        }
        composable(route = DetailScreens.CARD_REVIEW.name) {
            //                enterTransition = { scaleIn(animationSpec = tween(150)) },
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




@Preview(showBackground = false)
@Composable
fun FlashCardPreview(){
    FlashCardTest(onNextButtonClicked = {}, onBackButtonClicked = {})
}
