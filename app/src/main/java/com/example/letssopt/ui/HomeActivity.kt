package com.example.letssopt.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.ui.ui.theme.LETSSOPTTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding((innerPadding))
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val movies = viewModel.movies
    val dramas = viewModel.dramas
    val watchaParties = viewModel.watchaParties

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 23.dp, end = 23.dp, bottom = 23.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_main_watch_24),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Spacer(modifier = Modifier.width(14.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_main_noti_24),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Spacer(modifier = Modifier.width(14.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_main_profile_24),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        bottomBar = {
            val context = androidx.compose.ui.platform.LocalContext.current
            NavigationBar(
                containerColor = Color(0xFF1A1A1A),
                windowInsets = WindowInsets(0.dp)
            ) {
                viewModel.navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = viewModel.selectedTabIndex == index,
                        onClick = {
                            viewModel.updateSelectedTab(index)
                            val intent = Intent(context, item.activityClass)
                            context.startActivity(intent)
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(item.iconRes),
                                contentDescription = item.title,
                                tint = if (viewModel.selectedTabIndex == index) Color.White else Color.Gray
                            )
                        },
                        label = { Text(item.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF141414))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "방금 막 도착한 신상 콘텐츠",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 24.dp)
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
                items(movies.size) { index ->
                    Card(
                        modifier = Modifier
                            .width(280.dp)
                            .height(160.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = movies[index]),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                }
            }
            Spacer(Modifier.height(26.dp))
            Column(
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_main_recommend_24),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row() {
                    Text(
                        text = "예능부터 드라마까지!",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f),
                        color = Color(0xFF999999),
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
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    items(dramas.size) { index ->
                        Card(
                            modifier = Modifier
                                .width(103.dp)
                                .height(153.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = dramas[index]),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                    }
                }
            }
            Spacer(Modifier.height(23.dp))
            Column(
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
                    items(dramas.size) { index ->
                        Card(
                            modifier = Modifier
                                .width(103.dp)
                                .height(153.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = dramas[index]),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                    }
                }
            }
            Spacer(Modifier.height(23.dp))
            Column(
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
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(watchaParties.size) { index ->
                        val item = watchaParties[index]

                        //배경
                        Box(
                            modifier = Modifier
                                .width(196.dp)
                                .height(185.dp)
                                .background(Color(0xFF2A2A2A))
                        ) {

                            // 이미지
                            Image(
                                painter = painterResource(id = item.imageRes),
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
                                    text = item.time,
                                    color = Color(0xFFE8003C),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(500),
                                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                                )
                                Text(
                                    text = item.title,
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


    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LETSSOPTTheme {
        HomeScreen()
    }
}