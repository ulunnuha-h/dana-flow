package com.example.danaflow.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.danaflow.R
import com.example.danaflow.viewmodel.ReportViewModel
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.reflect.KFunction3

@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReportScreen(report: ReportViewModel) {
    val data by report.data.collectAsState()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    val inputState = remember { mutableStateOf(false) }

    fun addCashflow(amount : Int, name : String , isIncome: Boolean){
        report.addCashflow(amount, name, isIncome);
        inputState.value = false;
    }

    fun deleteCashflow(date: Date, index : Int){
        report.deleteCashflow(date, index);
    }

    CashflowInfo(balance = report.totalBalance)
    LazyColumn (
        modifier = Modifier.padding(0.dp, 64.dp)
    ) {
        items(data.entries.toList()){ it ->
            Text(text = dateFormat.format(it.key),
                fontSize = 18.sp,
                modifier = Modifier.padding(12.dp, 18.dp))
            it.value.forEachIndexed{idx, it2->
                CashflowItem(isIncome = it2.isIncome,
                    amount = it2.amount,
                    name = it2.name,
                    delete = {deleteCashflow(it.key, idx)})
            }
        }
    }
    when{
        inputState.value->{
            InputDialog(inputState, ::addCashflow)
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp, 64.dp)){

        FloatingActionButton(onClick = { inputState.value = true },
            modifier = Modifier.align(Alignment.BottomEnd)) {
            Icon(painter = painterResource(id = R.drawable.add_24px), contentDescription = "Add")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDialog(inputState: MutableState<Boolean>, addCashflow: KFunction3<Int, String, Boolean, Unit>) {
    val name = remember{mutableStateOf("")}
    val amount = remember{mutableStateOf("0")}
    val isIncome = remember{ mutableStateOf(true) }

    Dialog(onDismissRequest = { inputState.value = false },) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column (modifier = Modifier
                .padding(24.dp)) {
                Text(text = "Input Cashflow",
                    fontSize = 24.sp)
                TextField(
                    value = name.value,
                    onValueChange = {it -> name.value = it},
                    label = { Text(text = "Cashflow Name")})
                TextField(
                    value = amount.value,
                    onValueChange = {it -> amount.value = it},
                    label = { Text(text = "Amount")})
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isIncome.value == true,
                        onClick = { isIncome.value = true }
                    )
                    Text("Income")

                    RadioButton(
                        selected = isIncome.value == false,
                        onClick = { isIncome.value = false }
                    )
                    Text("Outcome")
                }
                Button(onClick = { addCashflow(amount.value.toInt(), name.value, isIncome.value) }) {
                    Text(text = "Add new cashflow +")
                }
            }

        }
    }
}

@Composable
fun CashflowInfo(balance: Int) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Total Balance : " + String.format("Rp.%,d", balance),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.5f),
            fontSize = 20.sp,
            color = Color.White)
    }
}

@Composable
fun CashflowItem(isIncome : Boolean, amount : Int, name : String, delete : ()-> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                val mod = if(isIncome) "+" else "-";
                Text(
                    text = name,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(0.6f)
                )
                Text(
                    text = mod + " Rp."+ String.format("%,d", amount),
                    color = if(isIncome) Color.Green else Color.Red,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(0.4f)
                )
            }
            IconButton(onClick = { delete() },
                modifier = Modifier.width(32.dp)) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    tint = Color.Red)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
//    CashflowInfo(income = 5000, outcome = 5000)
//    CashflowItem(isIncome = true, amount = 5000, name = "Awaw")
//    Icon(
//        imageVector = Icons.Default.Delete,
//        contentDescription = "",
//        modifier = Modifier.background(Color.Red)
//    )
}