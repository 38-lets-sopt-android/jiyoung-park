package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun NewContentSection(
    contents: List<ContentItem>,
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = "방금 막 도착한 신상 콘텐츠",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 19.dp),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "예능부터 드라마까지!",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 19.dp),
            color = Color(0xFFBABAC1),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(contents) { content ->
                Image(
                    painter = painterResource(id = content.contentImage),
                    contentDescription = content.title,
                    modifier = Modifier
                        .width(280.dp)
                        .height(160.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Preview
@Composable
private fun NewContentSectionPreview(){
    LETSSOPTTheme {
        NewContentSection(
            contents = listOf(
                ContentItem(R.drawable.img_main_manifest,"매니페스트"),
                ContentItem(R.drawable.img_main_crime,"범죄도시"),
                ContentItem(R.drawable.img_main_jeju,"폭싹 속았수다")
            )
        )
    }
}



