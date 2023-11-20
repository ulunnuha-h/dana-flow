package com.example.danaflow.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danaflow.viewmodel.DebtViewModel
import com.example.danaflow.viewmodel.ReportViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(report: ReportViewModel, debt: DebtViewModel) {
    Column {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .background(MaterialTheme.colorScheme.primary)
            ,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "DanaFlow Application",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.White)
            Text(text = "\"Efficiency in accounting, prosperity in business.\"",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White)
        }
        Column (
            modifier = Modifier
                .padding(0.dp, 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            HomeItem(name = "Balance", amount = report.totalBalance)
            HomeItem(name = "Income", amount = report.totalIncome)
            HomeItem(name = "Outcome", amount = report.totalOutcome)
            HomeItem(name = "Payable", amount = debt.totalPayables)
            HomeItem(name = "Receviable", amount = debt.totalReceivables)

        }
    }
}

@Composable
fun HomeItem(name : String, amount : Int) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Total " + name,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface);
        Text(text = String.format("Rp.%,d", amount),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        )

    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeItem(name = "Hanif", amount = 200000 )
}