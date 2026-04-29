package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.R
import com.example.letssopt.presentation.home.ContentItem
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun UpcomimgSection(
    contents: List<ContentItem>,
    modifier : Modifier = Modifier
){
    Column(
        modifier = Modifier
    ) {
        Row() {
            Text(
                text = "공개 예정 콘텐츠",
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
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                color = Color(0xFF999999),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(300)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(contents) { content ->
                Image(
                        painter = painterResource(id = content.contentImage),
                        contentDescription = content.title,
                        modifier = Modifier
                            .size(width = 103.dp,height = 153.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }

@Preview
@Composable
private fun UpcomimgSectionPreview(){
    LETSSOPTTheme {
        UpcomimgSection(
            contents = listOf(
                ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요"),
                ContentItem(R.drawable.img_main_five, "기묘한 이야기 시즌 5"),
                ContentItem(R.drawable.img_main_hailmary,"프로젝트 헤일메리"),
                ContentItem(R.drawable.img_main_love, "이 사랑 통역 되나요")
            )
        )
    }
}