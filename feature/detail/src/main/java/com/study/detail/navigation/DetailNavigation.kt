package com.study.detail.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.study.detail.DetailRoute

const val ITEM_ID = "itemId"
const val NAV_ROUTE = "detail_screen_route"
const val DETAIL_ROUTE = NAV_ROUTE +
        "/{$ITEM_ID}"


fun NavController.navigateToDetail(id: Int){
    this.navigate("$NAV_ROUTE/$id")
}

fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit
) {
    composable(route = DETAIL_ROUTE, arguments = listOf(
        navArgument(ITEM_ID) { type = NavType.IntType }
    )) {
        BackHandler (true) { onBackClick() }
        DetailRoute(
            onBackClick = onBackClick
        )

    }
}