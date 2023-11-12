package data

import java.util.Date

class Cashflow (income: Int = 0, outcome:Int =0, date:Date){
    private var income = income
    private var outcome = outcome
    private var date = date

    fun getIncome():Int{
        return income
    }
    fun getOutcome():Int {
        return outcome
    }

    fun getDate():Date{
        return date
    }
}