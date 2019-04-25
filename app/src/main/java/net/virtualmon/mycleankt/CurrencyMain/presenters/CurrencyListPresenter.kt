package net.virtualmon.mycleankt.CurrencyMain.presenters

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import net.virtualmon.domain.interactors.GetCurrencyUseCase
import net.virtualmon.domain.model.EuroDollarEntity
import net.virtualmon.mycleankt.CurrencyMain.CurrencyListView
import net.virtualmon.mycleankt.base.BasePresenter
import net.virtualmon.mycleankt.injection.scope.PerActivity
import net.virtualmon.mycleankt.mappers.EuroDollarEntityMapper
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by AlbertM on 21/04/19.
 */
@PerActivity
class CurrencyListPresenter @Inject constructor(val view: CurrencyListView,
                                                val getCurrency: GetCurrencyUseCase,
                                                @Named("main")val mainDisp : CoroutineDispatcher,
                                                @Named("back") val backgrDispatcher: CoroutineDispatcher): BasePresenter(mainDisp,backgrDispatcher)  {



   fun execute(startDate: String, endDate: String)
    {
        backgroundScope.launch {
            getCurrency.execute(startDate, endDate, object : GetCurrencyUseCase.Callback {
                override fun onCurrencyHistLoaded(euroDollarEntity: EuroDollarEntity) {
                    val mapper = EuroDollarEntityMapper()
                    val newModel = mapper.transform(euroDollarEntity)
                    view.renderHistoricalCurrency(newModel)
                }


                override fun onError() {
                    view.showError()
                }

            })
        }



    }

}