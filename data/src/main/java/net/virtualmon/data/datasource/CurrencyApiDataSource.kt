package net.virtualmon.data.datasource

import net.virtualmon.data.mappers.CurrencyEntityDataMapper
import net.virtualmon.data.models.EuroDollarModel
import net.virtualmon.data.net.ConnectionChecker
import net.virtualmon.data.net.rest.CurrencyService
import net.virtualmon.domain.model.EuroDollarEntity
import net.virtualmon.domain.repository.EuroDollarRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by AlbertM on 21/04/19.
 */
class CurrencyApiDataSource @Inject constructor(private val retrofit: Retrofit, private val netChecker: ConnectionChecker): EuroDollarRepository {



    private  var newModel : EuroDollarEntity? = null

    override suspend fun getEuroDollarRecord(startDate: String, endDate: String, callback: EuroDollarRepository.CurrencyRepositoryCallback) {

        val currencyService = retrofit.create(CurrencyService::class.java)
        val res: Call<EuroDollarModel> = currencyService.getEuroDolar(startDate, endDate, "USD")

        if(netChecker.thereIsConnectivity())
        {

            newModel?.let {
                doAsync {
                    uiThread {
                        callback.onCurrentCurrencyLoaded(newModel!!)
                    }
                }

            }?: run {
                res.enqueue(object : Callback<EuroDollarModel> {
                    override fun onFailure(call: Call<EuroDollarModel>?, t: Throwable?) {
                        callback.onError()
                    }

                    override fun onResponse(call: Call<EuroDollarModel>?, response: Response<EuroDollarModel>?) {

                        val body = response?.body()?.let { it } ?: throw Exception("Error downloading data")
                        newModel = CurrencyEntityDataMapper().transform(body)
                        callback.onCurrentCurrencyLoaded(newModel!!)

                    }
                })

            }
        }else{
            callback.onError()
        }


    }



}

