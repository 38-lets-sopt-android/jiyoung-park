package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R
import com.example.letssopt.presentation.home.WatchaPartyItem
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun PartySection(
    contents: List<WatchaPartyItem>,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Row() {
            Text(
                text = "왓챠 파티",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "더보기",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color(0xFF999999),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(300)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(contents) { content ->
                Box(
                    modifier = Modifier
                        .width(196.dp)
                        .height(185.dp)
                        .background(Color(0xFF2A2A2A))
                ) {

                    // 이미지
                    Image(
                        painter = painterResource(id = content.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .width(196.dp)
                            .height(139.dp),
                        contentScale = ContentScale.Crop
                    )

                    // 시간 + 제목 텍스트 (하단)
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = content.time,
                            color = Color(0xFFE8003C),
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            fontFamily = FontFamily(Font(R.font.pretendard_regular))
                        )
                        Text(
                            text = content.title,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            fontFamily = FontFamily(Font(R.font.pretendard_regular))
                        )

                    }

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_main_notification_24),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 7.dp, end = 5.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PartySectionPreview(){
    LETSSOPTTheme {
        PartySection(
            contents = listOf(
                WatchaPartyItem(R.drawable.img_main_king, "오늘 21:13에 시작", "# 왕과사는 남자"),
                WatchaPartyItem(R.drawable.img_main_exhuma, "오늘 22:22에 시작", "# 파묘")
            )
        )
    }
}
