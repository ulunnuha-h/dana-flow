package data

import java.time.LocalDate
import java.util.Date

object CashflowList {
    private var listIncome: MutableList<Cashflow> = mutableListOf()
    private var listOutcome: MutableList<Cashflow> = mutableListOf()

    fun addIncome(income: Int, date: Date){
        val cashflow = Cashflow(income=income,date=date)
        listIncome.add(cashflow)
    }

    fun addOutcome(outcome : Int,date: Date){
        val cashflow = Cashflow(outcome=outcome * -1,date=date)
        listIncome.add(cashflow)
    }

    fun removeIncome(cashflow: Cashflow){
        listIncome.remove(cashflow)
    }

    fun removeOutcome(cashflow: Cashflow){
        listOutcome.remove(cashflow)
    }

    fun getAllIncome(): MutableList<Cashflow> {
        return listIncome
    }

    fun getAllOutcome(): MutableList<Cashflow> {
        return listOutcome
    }
}