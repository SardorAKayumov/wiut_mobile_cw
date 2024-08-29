package com.example.mad_cw_00011193

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mad_cw_00011193.detailedView.DetailedView
import com.example.mad_cw_00011193.list.ListView
import com.example.mad_cw_00011193.permissions.SinglePermissionCamera
import com.example.mad_cw_00011193.settings.Settings
import com.example.mad_cw_00011193.ui.theme.MAD_CW_00011193Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SinglePermissionCamera()

            MAD_CW_00011193Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = stringResource(id = R.string.navigation_products_title),
                                    route = "productsList",
                                    icon = Icons.Default.List
                                ),
                                BottomNavItem(
                                    name = stringResource(id = R.string.navigation_settings_title),
                                    route = "settings",
                                    icon = Icons.Default.Settings
                                )

                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "productsList") {
        composable("productsList") {
            ListView(
                onProductClick = { productId ->
                    navController.navigate("detailedView/$productId")
                }
            )
        }

        composable("settings") {
            Settings()
        }

        composable(
            "detailedView/{productId}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("productId")?.let { DetailedView(it) }
        }

    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = colorResource(id = R.color.blue_200),
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        )
                    }
                }
            )
        }
    }
}
