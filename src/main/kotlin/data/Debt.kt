package data

import java.util.Date

class Debt (amount:Int, name: String, dueDate: Date ){
    private var amount = amount
    private var name = name
    private var dueDate = dueDate

    fun setAmount(amount: Int){
        this.amount = amount
    }

    fun setname(name: String){
        this.name = name
    }

    fun setDueDate(dueDate: Date){
        this.dueDate = dueDate
    }

    fun getAmount(): Int {
        return amount
    }

    fun getname(): String {
        return name
    }

    fun getDueDate(): Date {
        return dueDate
    }

}