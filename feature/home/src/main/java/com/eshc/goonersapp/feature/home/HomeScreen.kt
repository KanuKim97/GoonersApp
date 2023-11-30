package com.eshc.goonersapp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eshc.goonersapp.core.designsystem.theme.pretendard


@Composable
fun HomeRoute() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            modifier = Modifier.padding(top = 8.dp, start = 8.dp),
            text = "HOME",
            //fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "Recently Result",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.height(12.dp))

        RecentlyMatchCard(
            homeShortName = "WHU",
            homeUrl = "https://www.arsenal.com/sites/default/files/styles/feed_crest_thumbnail/public/logos/westhamunited.png?auto=webp&itok=37U68OD4",
            time = "2023.11.02 04:30",
            location = "London Stadium",
            competitionUrl = "https://www.arsenal.com/sites/default/files/styles/small/public/logos/Unknown-1.png?auto=webp&itok=vWaxFMmA"
        )

    }
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecentlyMatchCard(
    homeShortName: String = "ARS",
    homeUrl: String = "https://www.arsenal.com/sites/default/files/styles/feed_crest_thumbnail/public/logos/arsenal-1.png?auto=webp&itok=7a6a0zug",
    awayShortName: String = "ARS",
    awayUrl: String = "https://www.arsenal.com/sites/default/files/styles/feed_crest_thumbnail/public/logos/arsenal-1.png?auto=webp&itok=7a6a0zug",
    time: String,
    location: String,
    competitionUrl: String,
    score : String = "0 : 3"
) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFF151D2D)),//0xFFDA0209)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    //.background(Color(1f, 0f, 0f, 0.9f))
                    .fillMaxWidth()
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GlideImage(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.Crop,
                        model = homeUrl,
                        contentDescription = null
                    ) {
                        it.diskCacheStrategy(
                            DiskCacheStrategy.ALL
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = homeShortName,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 24.sp,
                            )
                            Text(
                                text = score,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 24.sp,
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = awayShortName,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 24.sp,
                            )
                        }

                    }

                    GlideImage(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.Crop,
                        model = awayUrl,
                        contentDescription = null
                    ) {
                        it.diskCacheStrategy(
                            DiskCacheStrategy.ALL
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .width(0.dp)
                    .height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier
                        .height(0.dp)
                        .width(8.dp))

                        Column(
                            modifier = Modifier.wrapContentHeight().weight(1f).padding(start = 28.dp,bottom = 12.dp,end = 12.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                text = "7` Saka",
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                fontSize = 18.sp,
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                text = "17` Saka",
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                fontSize = 18.sp,
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                text = "27` Saka",
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                fontSize = 18.sp,
                            )

                        }

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp).padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "${time}\n${location}",
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontSize = 14.sp,
            )

            GlideImage(
                modifier = Modifier.height(30.dp),
                model = competitionUrl,
                contentDescription = null
            ) {
                it.diskCacheStrategy(
                    DiskCacheStrategy.ALL
                )
            }
        }
    }
}
