package func

import data.Cashflow
import data.CashflowList
import data.Debt
import java.lang.Exception
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import manager.DebtManager

object Functionals {
    private val date = getDate()

    fun addIncome(){
        println("silahkan masukkan pemasukkan")
        val income = intCheck()
        CashflowList.addIncome(income,date)
    }

    fun addOutcome(){
        val outcome = intCheck()
        CashflowList.addOutcome(outcome,date)
    }

    fun removeIncome(){
        if(CashflowList.getAllIncome().isEmpty()){
            println("list income kosong")
        }
        else{
            getAllIncome()
        }
        println("pilih data yang dihapus")
        val x = intCheck()
        CashflowList.removeIncome(CashflowList.getAllIncome()[x-1])
    }


    fun removeOutcome(){
        if(CashflowList.getAllOutcome().isEmpty()){
            println("list outcome kosong")
        }
        else{
            getAllOutcome()
        }
        println("pilih data yang dihapus")
        val x = intCheck()
        CashflowList.removeOutcome(CashflowList.getAllOutcome()[x-1])
    }


    fun getAllIncome() {
        var j = 1
        for (i in CashflowList.getAllIncome()) {
            println("$j. ${i.getDate()} ${i.getIncome()}")
            j++
        }
    }

    fun getAllOutcome() {
        var j = 1
        for (i in CashflowList.getAllOutcome()) {
            println("$j. ${i.getDate()} ${i.getOutcome()}")
            j++
        }
    }

    fun addDebt(){
        println("silahkan masukkan hutang")
        val debt = intCheck()
        println("nama hutang")
        val name = readln()
        val ddate = checkDate()
        DebtManager.addPayable(debt,name, ddate)
    }

    fun addReceivable(){
        println("silahkan masukkan deposit")
        val debt = intCheck()
        println("nama deposit")
        val name = readln()
        val ddate = checkDate()
        DebtManager.addPayable(debt,name, ddate)
    }

    fun deleteDebt(){
        println("nama yang ingin dihapus")
        val nama = readln()
        DebtManager.deletePayable(nama)
    }

    fun deleteDeposit(){
        println("nama yang ingin dihapus")
        val nama = readln()
        DebtManager.deleteReceivable(nama)
    }

    fun getAllPayable(){
        var j = 1
        for (i in DebtManager.getAllPayable()){
            println("$j. ${i.getname()} ${i.getAmount()} ${i.getDueDate()}")
            j++
        }
    }

    fun getAllReceivable(){
        var j = 1
        for (i in DebtManager.getAllReceivable()){
            println("$j. ${i.getname()} ${i.getAmount()} ${i.getDueDate()}")
            j++
        }
    }

    fun setPayable(){
        getAllPayable()
        println("masukkan nama yang ingin diubah")
        val nama = readln()
        println("ubah jumlah utang")
        val amount = intCheck()
        DebtManager.setPayable(nama,amount)
    }

    fun setReceivable(){
        getAllReceivable()
        println("masukkan nama yang ingin diubah")
        val nama = readln()
        println("ubah jumlah deposit")
        val amount = intCheck()
        DebtManager.setReceivable(nama,amount)
    }
}


fun getDate(): Date {
    val localdate = LocalDate.now()
    val zonedDateTime = localdate.atStartOfDay(ZoneId.systemDefault())
    val instant = zonedDateTime.toInstant()
    return Date.from(instant)
}

fun intCheck(): Int {
    var x = 0
    var conf :Boolean
    do {
        try {
            var num = readln()
            x = num.toInt()
            conf = false
        }catch (e:Exception){
            println("masukkan angka")
            conf = true
        }
    }while (conf)

    return x
}

fun checkDate(): Date {
    print("masukkan tanggal dengan format yyyy-MM-dd: ")


    lateinit var date :LocalDate
    var conf :Boolean
    do {
        try {
            val input = readLine() // read a line of input from the user
            date = LocalDate.parse(input) // convert the input to a LocalDate object
            conf = false
        }catch (e:Exception){
            println("masukkan tanggal dengan format yyyy-MM-dd")
            conf = true
        }
    }while (conf)
    val zonedDateTime = date.atStartOfDay(ZoneId.systemDefault())
    val instant = zonedDateTime.toInstant()
    return Date.from(instant)
}