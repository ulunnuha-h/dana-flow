package com.example.danaflow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.danaflow.model.BottomNavItem
import com.example.danaflow.ui.theme.DanaFlowTheme
import com.example.danaflow.view.CurrencyScreen
import com.example.danaflow.view.DebtScreen
import com.example.danaflow.view.HomeScreen
import com.example.danaflow.view.ReportScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DanaFlowTheme {
                var selectedItem by remember { mutableStateOf(0) }
                val items = listOf(
                    BottomNavItem("Home", R.drawable.ic_launcher_background),
                    BottomNavItem("Report", R.drawable.ic_launcher_foreground),
                    BottomNavItem("Debt", R.drawable.ic_launcher_foreground),
                    BottomNavItem("Currency", R.drawable.ic_launcher_foreground),
                )

                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = items,
                                onItemSelected = { index ->
                                    navController.navigate(index)
                                },
                                selectedItem = navController.currentDestination?.route
                            )
                        }
                    ) {
                        NavHost(navController = navController, startDestination = "Home") {
                            composable("Home") {
                                HomeScreen()
                            }
                            composable("Report") {
                                ReportScreen()
                            }
                            composable("Debt") {
                                DebtScreen()
                            }
                            composable("Currency") {
                                CurrencyScreen()
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    onItemSelected: (String) -> Unit,
    selectedItem: String?
) {
    BottomNavigation(
//        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = null) },
                label = { Text(text = item.label) },
                selected = selectedItem == item.label,
                onClick = { onItemSelected(item.label) },
                alwaysShowLabel = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DanaFlowTheme {
        Greeting("Android")
    }
}