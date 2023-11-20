package com.example.danaflow.view

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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.danaflow.R
import com.example.danaflow.viewmodel.DebtViewModel
import kotlin.reflect.KFunction3

@Composable
fun DebtScreen(debt: DebtViewModel) {
    val data = debt.data.collectAsState()
    val inputState = remember { mutableStateOf(false) }

    fun addDebt(amount : Int, name : String , isReceivable: Boolean){
        debt.addDebt(amount, name, isReceivable);
        inputState.value = false;
    }

    DebtInfo(receviables = debt.totalReceivables, payables = debt.totalPayables)
    Column {
        Text(text = "Receivable",
            fontSize = 18.sp,
            modifier = Modifier.padding(12.dp, 64.dp, 12.dp, 18.dp))
        LazyColumn (
        ){
            itemsIndexed(data.value){ idx, it ->
                if(it.isReceivable){
                    DebtItem(
                        amount = it.amount,
                        name = it.name
                    ) { debt.removeDebt(idx) }
                }
            }
        }
        Text(text = "Payable",
            fontSize = 18.sp,
            modifier = Modifier.padding(12.dp, 18.dp))
        LazyColumn (
        ){
            itemsIndexed(data.value){ idx, it ->
                if(!it.isReceivable){
                    DebtItem(
                        amount = it.amount,
                        name = it.name
                    ) { debt.removeDebt(idx) }
                }
            }
        }
    }
    when{
        inputState.value->{
            InputDebtDialog(inputState, ::addDebt )
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
fun InputDebtDialog(inputState: MutableState<Boolean>, addDebt: KFunction3<Int, String, Boolean, Unit>) {
    val name = remember{mutableStateOf("")}
    val amount = remember{mutableStateOf("0")}
    val isReceivable = remember{ mutableStateOf(true) }

    Dialog(onDismissRequest = { inputState.value = false },) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column (modifier = Modifier
                .padding(24.dp)) {
                Text(text = "Input Debt",
                    fontSize = 24.sp)
                TextField(
                    value = name.value,
                    onValueChange = {it -> name.value = it},
                    label = { Text(text = "Debt Name")})
                TextField(
                    value = amount.value,
                    onValueChange = {it -> amount.value = it},
                    label = { Text(text = "Amount")})
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isReceivable.value == true,
                        onClick = { isReceivable.value = true }
                    )
                    Text("Receivable")

                    RadioButton(
                        selected = isReceivable.value == false,
                        onClick = { isReceivable.value = false }
                    )
                    Text("Payable")
                }
                Button(onClick = { addDebt(amount.value.toInt(), name.value, isReceivable.value) }) {
                    Text(text = "Add new cashflow +")
                }
            }

        }
    }
}

@Composable
fun DebtItem(amount: Int, name: String, delete: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 3.dp),
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
                Text(
                    text = name,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(0.6f)
                )
                Text(
                    text = " Rp."+ String.format("%,d", amount),
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

@Composable
fun DebtInfo(receviables : Int, payables : Int) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Total Receviables : " + String.format("Rp.%,d", receviables),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.5f),
            fontSize = 20.sp,
            color = Color.White)
        Text(text = "Total Paybales : " + String.format("Rp.%,d", payables),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.5f),
            fontSize = 20.sp,
            color = Color.White)
    }
}