package com.example.letssopt.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.letssopt.R

// 1. 데이터 클래스들을 뷰모델 파일 상단이나 외부에 정의합니다.
data class WatchaPartyItem(
    val imageRes: Int,
    val time: String,
    val title: String,
)

// 바텀바 아이템을 위한 데이터 클래스도 만들면 관리가 편해요!
data class BottomNavItem(
    val title: String,
    val iconRes: Int,
    val activityClass: Class<*>
)

class HomeViewModel : ViewModel() {

    // 2. 리스트 데이터들을 뷰모델 안으로 옮깁니다.
    val movies = listOf(
        R.drawable.img_main_manifest,
        R.drawable.img_main_crime,
        R.drawable.img_main_jeju,
    )

    val dramas = listOf(
        R.drawable.img_main_love,
        R.drawable.img_main_five,
        R.drawable.img_main_hailmary,
        R.drawable.img_main_love
    )

    val watchaParties = listOf(
        WatchaPartyItem(R.drawable.img_main_king, "오늘 21:13에 시작", "# 왕과사는 남자"),
        WatchaPartyItem(R.drawable.img_main_exhuma, "오늘 22:22에 시작", "# 파묘"),
    )

    // 3. 바텀바 상태 관리
    val navItems = listOf(
        BottomNavItem("메인", R.drawable.ic_bottom_bar_main_24, HomeActivity::class.java),
        BottomNavItem("개별 구매", R.drawable.ic_bottom_bar_category_24, PurchaseActivity::class.java),
        BottomNavItem("웹툰", R.drawable.ic_bottom_bar_wallet_24, WebtoonActivity::class.java),
        BottomNavItem("찾기", R.drawable.ic_bottom_bar_search_24, SearchActivity::class.java),
        BottomNavItem("보관함", R.drawable.ic_bottom_bar_folder_24, StorageActivity::class.java)
    )

    var selectedTabIndex by mutableStateOf(0)
        private set

    fun updateSelectedTab(index: Int) {
        selectedTabIndex = index
    }
}