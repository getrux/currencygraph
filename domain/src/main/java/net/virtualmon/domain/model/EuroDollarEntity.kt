package net.virtualmon.domain.model


data class EuroDollarEntity(
        val baseSymbol: String,
         val startDate: String,
        val endDate: String,
        val currencyInfo:  Map<String, Map<String,Double>>


)