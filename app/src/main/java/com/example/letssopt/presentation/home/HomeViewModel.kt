package com.example.letssopt.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.letssopt.R

data class WatchaPartyItem(
    val imageRes: Int,
    val time: String,
    val title: String,
)

data class ContentItem(
    val contentImage: Int,
    val title: String,
)

data class BottomNavItem(
    val title: String,
    val iconRes: Int
)

class HomeViewModel : ViewModel() {
    val contents = listOf(
        ContentItem(R.drawable.img_main_manifest, "매니페스트"),
        ContentItem(R.drawable.img_main_crime, "범죄도시"),
        ContentItem(R.drawable.img_main_jeju, "폭싹 속았수다")
    )

    val upcomings = listOf(
        ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
        ContentItem(R.drawable.img_main_five, "기묘한 이야기 시즌 5"),
        ContentItem(R.drawable.img_main_hailmary, "프로젝트 헤일메리"),
        ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
    )

    val whatgorisms = listOf(
        ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
        ContentItem(R.drawable.img_main_five, "기묘한 이야기 시즌 5"),
        ContentItem(R.drawable.img_main_hailmary, "프로젝트 헤일메리"),
        ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
    )

    val watchaParties = listOf(
        WatchaPartyItem(R.drawable.img_main_king, "오늘 21:13에 시작", "# 왕과사는 남자"),
        WatchaPartyItem(R.drawable.img_main_exhuma, "오늘 22:22에 시작", "# 파묘")
    )

    val navItems = listOf(
        BottomNavItem("메인", R.drawable.ic_bottom_bar_main_24),
        BottomNavItem("개별 구매", R.drawable.ic_bottom_bar_category_24),
        BottomNavItem("웹툰", R.drawable.ic_bottom_bar_wallet_24),
        BottomNavItem("찾기", R.drawable.ic_bottom_bar_search_24),
        BottomNavItem("보관함", R.drawable.ic_bottom_bar_folder_24)
    )

    var selectedTabIndex by mutableStateOf(0)
        private set

    fun updateSelectedTab(index: Int) {
        selectedTabIndex = index
    }
}