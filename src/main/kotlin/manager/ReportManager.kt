package manager

import data.Cashflow
import data.CashflowList as cashflowList
import java.sql.Date
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.ZoneId


object ReportManager {

    fun getDataByDate(date: Date): MutableList<Cashflow> {
        lateinit var dateList: MutableList<Cashflow>
        for(i in cashflowList.getAllIncome()){
            if(date.equals(i.getDate())){
                dateList.add(i)
            }
        }
        for (i in cashflowList.getAllOutcome()){
            if(date.after(i.getDate())){
                dateList.add(i)
            }
        }
        return dateList
    }

    fun getDataByMonth(date: Month): MutableList<Cashflow> {
        lateinit var dateList: MutableList<Cashflow>
        for(i in cashflowList.getAllIncome()){
            if(date.equals(i.getDate().month)){
                dateList.add(i)
            }
        }
        for (i in cashflowList.getAllOutcome()){
            if(date.equals(i.getDate().month)){
                dateList.add(i)
            }
        }
        return dateList
    }

    fun getDataByYears(date: Year): MutableList<Cashflow> {
        lateinit var dateList: MutableList<Cashflow>
        for(i in cashflowList.getAllIncome()){
            if(date.equals(i.getDate().year)){
                dateList.add(i)
            }
        }
        for (i in cashflowList.getAllOutcome()){
            if(date.equals(i.getDate().year)){
                dateList.add(i)
            }
        }
        return dateList
    }




}