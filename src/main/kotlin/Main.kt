
import func.Functionals

fun main(args: Array<String>) {
    println("Hello World!")
    var y = "a"
    while (y != "") {
        println("masukkan pilihan yang diinginkan")
        println("income outcome debt")
        y = readln()
        if (y == "income" || y == "outcome") {
            var x = "a"
            while (x != "") {
                println("ketik angka untuk memilih")
                println("1 tambah pemasukan")
                println("2 tambah pengeluaran")
                println("3 hapus pemasukan")
                println("4 hapus pengeluaran")
                println("5 tampilkan semua pemasukan")
                println("6 tampilkan semua pemasukan")
                x = readln()
                when (x) {
                    "1" -> Functionals.addIncome()
                    "2" -> Functionals.addOutcome()
                    "3" -> Functionals.removeIncome()
                    "4" -> Functionals.removeOutcome()
                    "5" -> Functionals.getAllIncome()
                    "6" -> Functionals.getAllOutcome()
                    "" -> print("")
                    else -> println("masukkan angka yang tepat")
                }
            }
        } else if (y == "debt") {
            var x = "a"
            while (x != "") {
                println("ketik angka untuk memilih")
                println("1 tambah utang")
                println("2 tambah deposit")
                println("3 hapus utang")
                println("4 hapus deposit")
                println("5 ganti data utang")
                println("6 ganti data deposit")
                x = readln()
                when (x) {
                    "1" -> Functionals.addDebt()
                    "2" -> Functionals.addReceivable()
                    "3" -> Functionals.deleteDebt()
                    "4" -> Functionals.deleteDeposit()
                    "5" -> Functionals.setPayable()
                    "6" -> Functionals.setReceivable()
                    "" -> print("")
                    else -> println("masukkan angka yang tepat")
                }
            }
        }
        else{
            break
        }
    }
}