package net.virtualmon.domain.repository

import net.virtualmon.domain.model.EuroDollarEntity


/**
 * Created by AlbertM on 21/04/19.
 */
interface EuroDollarRepository {

    interface CurrencyRepositoryCallback {
        fun onCurrentCurrencyLoaded(euroDllarEntity: EuroDollarEntity)
        fun onError()

    }

    suspend fun getEuroDollarRecord(lat: String, lon: String, callback : CurrencyRepositoryCallback)
}