package com.example.letssopt.presentation.main.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.letssopt.R
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.collection.navigation.CollectionRoute
import com.example.letssopt.presentation.home.navigation.HomeDestination
import com.example.letssopt.presentation.purchase.navigation.PurchaseRoute
import com.example.letssopt.presentation.search.navigation.SearchRoute
import com.example.letssopt.presentation.webtoon.navigation.WebtoonRoute

enum class MainTab(
    @param:DrawableRes val iconRes: Int,
    @param:StringRes val labelRes: Int,
    val route: Route,
){
    HOME(
        iconRes = R.drawable.ic_bottom_bar_main_24,
        labelRes = R.string.bottom_main,
        route = HomeDestination.Home,
    ),
    PURCHASE(
        iconRes = R.drawable.ic_bottom_bar_category_24,
        labelRes = R.string.bottom_purchase,
        route = PurchaseRoute.Purchase,
    ),
    WEBTOON(
        iconRes = R.drawable.ic_bottom_bar_wallet_24,
        labelRes = R.string.bottom_webtoon,
        route = WebtoonRoute.Webtoon,
    ),
    SEARCH(
        iconRes = R.drawable.ic_bottom_bar_search_24,
        labelRes = R.string.bottom_search,
        route = SearchRoute.Search,
    ),
    COLLECTION(
        iconRes = R.drawable.ic_bottom_bar_folder_24,
        labelRes = R.string.bottom_collection,
        route = CollectionRoute.Collection,
    ),
}