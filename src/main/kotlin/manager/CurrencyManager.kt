package manager


class CurrencyManager() {
    lateinit var currencyRate : Map<String,Int>

    fun updateCurrency(){
        TODO("make API for currency rate")
    }

    fun calculateCurrency(from:String,to:String,amount:Int): Int {
//        rate = currencyRate[from]/currencyRate[to]
//        return amount*rate
        return 0 //delete this return after done
    }
}