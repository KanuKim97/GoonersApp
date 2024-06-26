package com.eshc.goonersapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.eshc.goonersapp.core.data.util.NetworkConnectivityManager
import com.eshc.goonersapp.core.designsystem.IconPack
import com.eshc.goonersapp.core.designsystem.component.GnrNavigationBar
import com.eshc.goonersapp.core.designsystem.component.GnrNavigationBarItem
import com.eshc.goonersapp.core.designsystem.component.TopLevelTopBar
import com.eshc.goonersapp.core.designsystem.iconpack.IcInfo
import com.eshc.goonersapp.core.designsystem.iconpack.IcSearch
import com.eshc.goonersapp.core.designsystem.iconpack.IcUser
import com.eshc.goonersapp.feature.home.navigation.navigateToHome
import com.eshc.goonersapp.feature.login.navigation.navigateToLogin
import com.eshc.goonersapp.feature.match.navigation.navigateToMatch
import com.eshc.goonersapp.feature.team.navigation.navigateToClubDetail
import com.eshc.goonersapp.feature.team.navigation.navigateToTeam
import com.eshc.goonersapp.feature.team.navigation.navigateToTeamHistory
import com.eshc.goonersapp.navigation.GnrNavHost
import com.eshc.goonersapp.navigation.TopLevelDestination
import kotlinx.coroutines.launch

@Composable
fun GnrApp(
    networkConnectivityManager: NetworkConnectivityManager
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val isOnline by networkConnectivityManager.isOnline.collectAsStateWithLifecycle(initialValue = true)

    LaunchedEffect(key1 = isOnline) {
        if(isOnline.not()){
            snackbarHostState.showSnackbar(
                "You aren’t connected to the internet",
                duration = SnackbarDuration.Indefinite
            )
        }
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            GnrNavHost(
                navController = navController,
                onShowSnackbar = { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message
                        )
                    }
                },
                topBar = { topLevelDestination ->
                    GnrTopLevelBar(
                        topLevelDestination = topLevelDestination,
                        icons = {
                            when (topLevelDestination) {
                                TopLevelDestination.TEAM -> {
                                    Icon(
                                        imageVector = IconPack.IcInfo,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                            .size(24.dp)
                                            .clickable {
                                                navController.navigateToClubDetail()
                                            }
                                    )
                                    Icon(
                                        imageVector = IconPack.IcSearch,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                            .size(24.dp)
                                            .clickable {
                                                navController.navigateToTeamHistory()
                                            }
                                    )
                                }

                                TopLevelDestination.HOME -> {
                                    Icon(
                                        imageVector = IconPack.IcUser,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                            .size(24.dp)
                                            .clickable {
                                                navController.navigateToLogin()
                                            }
                                    )
                                }

                                else -> {

                                }
                            }
                        }
                    )
                },
                bottomBar = {
                    GnrBottomBar(
                        destinations = TopLevelDestination.entries,
                        onNavigateToDestination = {
                            val topLevelNavOptions = navOptions {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            when (it) {
                                TopLevelDestination.HOME -> navController.navigateToHome(
                                    topLevelNavOptions
                                )

                                TopLevelDestination.MATCH -> navController.navigateToMatch(
                                    topLevelNavOptions
                                )

                                else -> navController.navigateToTeam(
                                    topLevelNavOptions
                                )
                            }
                        }
                    )
                }
            )

        }

    }
}

@Composable
fun GnrTopLevelBar(
    topLevelDestination: TopLevelDestination,
    icons: @Composable () -> Unit
) {
    TopLevelTopBar(
        modifier = Modifier.padding(horizontal = 8.dp),
        title = topLevelDestination.name
    ) {
        icons()
    }
}

@Composable
fun GnrBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    GnrNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            GnrNavigationBarItem(
                selected = false,
                onClick = {
                    onNavigateToDestination(destination)
                },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }

    }
}