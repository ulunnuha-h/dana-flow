package com.example.danaflow.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.danaflow.model.CashflowModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date


@RequiresApi(Build.VERSION_CODES.O)
class ReportViewModel : ViewModel() {
    private val _data = MutableStateFlow(mutableMapOf<Date, MutableList<CashflowModel>>())
    val data: StateFlow<Map<Date, MutableList<CashflowModel>>> = _data.asStateFlow()

    var totalIncome by mutableStateOf(0)
    var totalOutcome by mutableStateOf(0)
    var totalBalance by mutableStateOf(0)

    init {
        val customDateTime = LocalDateTime.of(2023, 4, 12, 12, 0, 0)
        val customDate = Date.from(customDateTime.atZone(ZoneId.systemDefault()).toInstant())

        var curData : MutableMap<Date, MutableList<CashflowModel>> = data.value.toMutableMap()
        if(curData[customDate] == null){
            curData[customDate] = mutableListOf()
        }
        curData[customDate]?.add(CashflowModel(3000000, "Uang bulanan April", true))
        totalIncome += 3000000
        totalBalance += 3000000
        curData[customDate]?.add(CashflowModel(10000, "Jajan cimol ", false))
        totalOutcome += 10000
        totalBalance -= 10000
        _data.update {it ->
            curData
        }
    }

    fun addCashflow(amount : Int, name : String, isIncome : Boolean){
        val date = LocalDate.now()
        val customDateTime = LocalDateTime.of(date.year, date.monthValue, date.dayOfMonth, 12, 0, 0)
        val customDate = Date.from(customDateTime.atZone(ZoneId.systemDefault()).toInstant())
        var curData : MutableMap<Date, MutableList<CashflowModel>> = data.value.toMutableMap()
        if(curData[customDate] == null){
            curData[customDate] = mutableListOf()
        }
        curData[customDate]?.add(CashflowModel(amount, name, isIncome));

        if(isIncome){
            totalIncome+=amount;
            totalBalance+=amount;
        } else {
            totalOutcome+=amount;
            totalBalance-=amount;
        }
        _data.update {it ->
            curData
        }
        Log.d("Tes", data.value.toString())
    }

    fun deleteCashflow(dateIndex: Date, index: Int){
        val date = dateIndex.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val customDateTime = LocalDateTime.of(date.year, date.monthValue, date.dayOfMonth, 12, 0, 0)
        val customDate = Date.from(customDateTime.atZone(ZoneId.systemDefault()).toInstant())
        var curData : MutableMap<Date, MutableList<CashflowModel>> = data.value.toMutableMap()
        val dataToDelete = curData[customDate]?.get(index);
        curData[customDate]?.removeAt(index)
        if (dataToDelete != null) {
            if(dataToDelete.isIncome){
                totalIncome-=dataToDelete.amount;
                totalBalance-=dataToDelete.amount;
            } else {
                totalOutcome-=dataToDelete.amount;
                totalBalance+=dataToDelete.amount;
            }
        }

        if(curData[customDate]?.isEmpty() == true){
            curData.remove(customDate)
        }

        _data.update {it ->
            curData
        }
    }

}