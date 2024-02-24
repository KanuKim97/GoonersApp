package com.eshc.goonersapp.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eshc.goonersapp.core.common.util.DateUtil
import com.eshc.goonersapp.feature.home.component.DashboardCard
import com.eshc.goonersapp.feature.home.component.RecentlyMatchCard
import com.eshc.goonersapp.feature.home.component.UpcomingMatchTicketCard


@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        viewModel
    )
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val upcomingMatches by viewModel.upcomingMatches.collectAsStateWithLifecycle()
    val recentlyMatch by viewModel.recentlyMatch.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 12.dp)
    ) {

//TODO
//        item {
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(
//                modifier = Modifier.padding(start = 8.dp),
//                text = "Team Dashboard",
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                fontSize = 24.sp,
//            )
//            DashboardCard()
//        }



        item {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Upcoming Matches",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 24.sp,
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),

                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = upcomingMatches, key = {
                    it.id
                }){
                    UpcomingMatchTicketCard(
                        homeShortName = it.homeTeamNickname,
                        homeUrl = it.homeTeamImageUrl,
                        awayShortName = it.awayTeamNickname,
                        awayUrl = it.awayTeamImageUrl,
                        time = DateUtil.getYearAndMonthAndDateAndTimeString(it.matchDate),
                        location = it.stadiumName,
                        competitionUrl = it.leagueImageUrl
                    )
                }
            }
        }
        recentlyMatch?.let { match ->
            item {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Recently Result",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 24.sp,
                )

                RecentlyMatchCard(
                    homeShortName = match.homeTeamNickname,
                    homeUrl = match.homeTeamImageUrl,
                    awayShortName = match.awayTeamNickname,
                    awayUrl = match.awayTeamImageUrl,
                    time = DateUtil.getYearAndMonthAndDateAndTimeString(match.matchDate),
                    location = match.stadiumName,
                    competitionUrl = match.leagueImageUrl,
                    score = "${match.homeScore} : ${match.awayScore}"
                )
            }
        }




    }
}

