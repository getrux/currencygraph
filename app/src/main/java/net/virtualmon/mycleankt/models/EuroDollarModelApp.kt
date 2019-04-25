package net.virtualmon.mycleankt.models


data class EuroDollarEntityApp(
        val baseSymbol: String,
        val startDate: String,
        val endDate: String,
        val currencyInfo:  Map<String, Map<String,Double>>


)

