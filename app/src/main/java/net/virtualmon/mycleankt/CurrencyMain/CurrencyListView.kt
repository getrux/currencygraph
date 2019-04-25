package net.virtualmon.mycleankt.CurrencyMain

import net.virtualmon.mycleankt.models.EuroDollarEntityApp

/**
 * Created by AlbertM on 21/04/19.
 */
interface CurrencyListView {

    fun renderHistoricalCurrency(euroDollarEntityApp: EuroDollarEntityApp)
    fun showError()


}