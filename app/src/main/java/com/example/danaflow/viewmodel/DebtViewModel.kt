package com.example.danaflow.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.danaflow.model.CashflowModel
import com.example.danaflow.model.DebtModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class DebtViewModel : ViewModel(){
    private val _data = MutableStateFlow(mutableListOf<DebtModel>())
    val data: StateFlow<MutableList<DebtModel>> = _data.asStateFlow()

    var totalReceivables by mutableStateOf(0)
    var totalPayables by mutableStateOf(0)

    init {
        var curData : MutableList<DebtModel> = data.value
        curData.add(DebtModel(2000, "Hutang beli bakso", false));
        curData.add(DebtModel(5000, "Hutang bayar buku", false));
        curData.add(DebtModel(10000, "Joni Hutang buat rental PS", true));
        totalPayables = 7000;
        totalReceivables = 10000;
        _data.update{it -> curData}
    }

    fun addDebt(amount : Int, name : String, isReceivable : Boolean){
        var curData : MutableList<DebtModel> = data.value
        curData.add(DebtModel(amount, name, isReceivable));

        if(isReceivable) totalReceivables += amount
        else totalPayables += amount

        _data.update{it -> curData}
    }

    fun removeDebt(index : Int){
        var curData : MutableList<DebtModel> = data.value
        var lastData = curData.get(index);
        curData.removeAt(index)

        if(lastData.isReceivable) totalReceivables -= lastData.amount
        else totalPayables -= lastData.amount

        _data.update{it -> curData}
    }
}