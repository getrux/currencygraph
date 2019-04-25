package net.virtualmon.domain.interactors

import net.virtualmon.domain.model.EuroDollarEntity
import net.virtualmon.domain.repository.EuroDollarRepository
import javax.inject.Inject

/**
 * Created by AlbertM on 21/04/19.
 */
class GetCurrencyUseCaseImpl @Inject constructor(private val euroDollarRepository: EuroDollarRepository) : GetCurrencyUseCase, EuroDollarRepository.CurrencyRepositoryCallback
{
    override fun onCurrentCurrencyLoaded(euroDllarEntity: EuroDollarEntity) {
        callbackInt.onCurrencyHistLoaded(euroDllarEntity)
    }

    lateinit var callbackInt: GetCurrencyUseCase.Callback

    override fun onError() {
        callbackInt.onError()
    }

    override suspend fun execute(startDate: String, endDate: String, callback: GetCurrencyUseCase.Callback) {
        callbackInt = callback
        euroDollarRepository.getEuroDollarRecord(startDate, endDate, this)
    }


}