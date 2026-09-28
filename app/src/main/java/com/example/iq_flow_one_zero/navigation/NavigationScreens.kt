package com.example.iq_flow_one_zero.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.iq_flow_one_zero.R

//enum class NavigationScreens(screenTitle: Int) {
//    LIST(R.string.app_navigation_item_1),
//    LIBRARY(R.string.app_navigation_item_2),
//    STATS(R.string.app_navigation_item_3),
//    PERSONAL_ACCOUNT(R.string.app_navigation_item_4)
//}

enum class Destination(
    val icon: ImageVector,
    val label: String,
    val contentDescription: String,
    @StringRes val topAppBarName: Int
){
    List( icon = Icons.AutoMirrored.Filled.ListAlt, label = "List", contentDescription = "null", topAppBarName = R.string.app_name),
    Library(icon = Icons.Filled.Search, label = "Library", contentDescription = "null", topAppBarName = R.string.app_navigation_item_2_en),
    Statistics(icon = Icons.Filled.BarChart, label = "Stats", contentDescription = "null", topAppBarName = R.string.app_navigation_item_3_en),
    Personal(icon =  Icons.Filled.Person, label = "Personal", contentDescription = "null", topAppBarName = R.string.app_navigation_item_4_en)
}
enum class DetailScreens(){
    DECK_DETAILS,
    CARD_REVIEW,
    ADD_CARDS
}