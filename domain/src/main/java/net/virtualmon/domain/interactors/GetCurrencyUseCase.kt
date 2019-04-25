package net.virtualmon.domain.interactors

import net.virtualmon.domain.model.EuroDollarEntity

/**
 * Created by AlbertM on 21/04/19.
 */
interface GetCurrencyUseCase
{
    interface Callback {
        fun onCurrencyHistLoaded(euroDollarEntity: EuroDollarEntity)
        fun onError()
    }

    suspend fun execute(startDate: String, endDate: String, callback: Callback)


}