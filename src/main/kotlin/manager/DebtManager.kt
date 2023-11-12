package manager

import data.Cashflow
import data.Debt
import java.util.Date

object DebtManager {
    var payable: MutableList<Debt> = mutableListOf()
    var receivable: MutableList<Debt> = mutableListOf()

    fun addPayable(amount: Int, name: String, date: Date) {
        val debt = Debt(amount = amount * -1, name = name, dueDate = date)
        payable.add(debt)
    }

    fun addReceivable(amount: Int, name: String, date: Date) {
        val debt = Debt(amount = amount, name = name, dueDate = date)
        payable.add(debt)
    }

    fun deletePayable(name: String) {
        payable.removeIf { it.getname() == name }
    }

    fun deleteReceivable(name: String) {
        receivable.removeIf { it.getname() == name }
    }

    fun getAllPayable(): MutableList<Debt> {
        return payable
    }

    fun getAllReceivable(): MutableList<Debt> {
        return receivable
    }

    fun setPayable(name: String, amount: Int) {
        for (i in payable) {
            if (i.getname() == name) {
                i.setAmount(amount)
            }
        }
    }

    fun setReceivable(name: String, amount: Int) {
        for (i in receivable) {
            if (i.getname() == name) {
                i.setAmount(amount)
            }
        }
    }
}