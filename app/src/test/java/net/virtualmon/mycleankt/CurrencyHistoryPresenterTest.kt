package net.virtualmon.mycleankt

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import net.virtualmon.data.mappers.CurrencyEntityDataMapper
import net.virtualmon.data.models.EuroDollarModel
import net.virtualmon.domain.interactors.GetCurrencyUseCase
import net.virtualmon.mycleankt.CurrencyMain.CurrencyListView
import net.virtualmon.mycleankt.CurrencyMain.presenters.CurrencyListPresenter
import net.virtualmon.mycleankt.mappers.EuroDollarEntityMapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CurrencyHistoryPresenterTest {

    @Mock
    lateinit var useCase: GetCurrencyUseCase
    @Mock
    lateinit var view: CurrencyListView
    lateinit var presenter: CurrencyListPresenter

    private val startDate : String = "2019-01-01"
    private val endDate : String = "2019-04-30"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CurrencyListPresenter(view, useCase, Dispatchers.Unconfined, Dispatchers.Unconfined)
    }

    @Test
    fun `when there is no historical data show error()`() {
        runBlocking {
            presenter.execute("0","0")
            println("ERROR")
        }
    }


    @Test
    fun `when there is data, renderHistoricalData`() {
        val jsonString =
                "{\"rates\":{\"2019-03-06\":{\"USD\":1.1305},\"2019-03-01\":{\"USD\":1.1383},\"2019-03-04\":{\"USD\":1.1337},\"2019-03-07\":{\"USD\":1.1271},\"2019-03-05\":{\"USD\":1.1329}},\"end_at\":\"2019-03-07\",\"base\":\"EUR\",\"start_at\":\"2019-03-01\"}"
        val gson = Gson()
        val response = gson.fromJson(jsonString, EuroDollarModel::class.java)
        val currencyHistoryDomain = CurrencyEntityDataMapper().transform(response)
        val currencyUiModel = EuroDollarEntityMapper().transform(currencyHistoryDomain)


        presenter.execute(startDate,endDate)

        println("SUCCESS")

    }
}