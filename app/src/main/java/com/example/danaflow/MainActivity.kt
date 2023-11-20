package com.example.danaflow

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.danaflow.model.BottomNavItem
import com.example.danaflow.ui.theme.DanaFlowTheme
import com.example.danaflow.view.DebtScreen
import com.example.danaflow.view.HomeScreen
import com.example.danaflow.view.ReportScreen
import com.example.danaflow.viewmodel.DebtViewModel
import com.example.danaflow.viewmodel.ReportViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DanaFlowTheme {
                val report: ReportViewModel = viewModel()
                val debt: DebtViewModel = viewModel()
                var selectedItem by remember { mutableStateOf(0) }
                val items = listOf(
                    BottomNavItem("Home", R.drawable.home_24px),
                    BottomNavItem("Report", R.drawable.ic_receipt_long),
                    BottomNavItem("Debt", R.drawable.credit_card_24px),
                )

                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = items,
                                onItemSelected = { index ->
                                    navController.navigate(index)
                                },
                            )
                        }
                    ) {
                        NavHost(navController = navController, startDestination = "Home") {
                            composable("Home") {
                                HomeScreen(report, debt)
                            }
                            composable("Report") {
                                ReportScreen(report)
                            }
                            composable("Debt") {
                                DebtScreen(debt)
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
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = null) },
                label = { Text(text = item.label) },
                selected = false,
                onClick = { onItemSelected(item.label) },
                alwaysShowLabel = true,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DanaFlowTheme {

    }
}