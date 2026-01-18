package com.ryben.stackexchange.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ryben.stackexchange.ui.search.SearchRoute
import com.ryben.stackexchange.ui.theme.StackExchangeTheme
import com.ryben.stackexchange.ui.userinfo.UserInfoRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@Serializable
object Search

@Serializable
object UserInfo

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            StackExchangeTheme(darkTheme = false) {
                NavHost(navController = navController, startDestination = Search) {
                    composable<Search> {
                        SearchRoute(navToUserInfoRoute = { navController.navigate(UserInfo) })
                    }
                    composable<UserInfo> {
                        UserInfoRoute(
                            onBack = { navController.navigateUp() },
                        )
                    }
                }
            }
        }
    }
}