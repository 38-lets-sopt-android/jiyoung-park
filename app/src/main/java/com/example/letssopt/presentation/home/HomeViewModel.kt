package com.example.letssopt.presentation.home

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

class HomeViewModel : ViewModel() {
    private val contents = listOf(
        ContentItem(R.drawable.img_main_manifest, "매니페스트"),
        ContentItem(R.drawable.img_main_crime, "범죄도시"),
        ContentItem(R.drawable.img_main_jeju, "폭싹 속았수다")
    )

    fun getContent() = contents

    private val upcomings = listOf(
        ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
        ContentItem(R.drawable.img_main_five, "기묘한 이야기 시즌 5"),
        ContentItem(R.drawable.img_main_hailmary, "프로젝트 헤일메리"),
        ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
    )

    fun getUpcoming() = upcomings

    private val whatgorisms = listOf(
        ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
        ContentItem(R.drawable.img_main_five, "기묘한 이야기 시즌 5"),
        ContentItem(R.drawable.img_main_hailmary, "프로젝트 헤일메리"),
        ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
    )

    fun getWhatgorisms() = whatgorisms

    private val watchaParties = listOf(
        WatchaPartyItem(R.drawable.img_main_king, "오늘 21:13에 시작", "# 왕과사는 남자"),
        WatchaPartyItem(R.drawable.img_main_exhuma, "오늘 22:22에 시작", "# 파묘")
    )

    fun getWatchaParties() = watchaParties
}