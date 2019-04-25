package net.virtualmon.data.net.rest

import net.virtualmon.data.models.EuroDollarModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by AlbertM on 21/04/19.
 */
interface CurrencyService {


    @GET("history")
    fun getEuroDolar(@Query("start_at") startAt: String, @Query("end_at") endAt: String,@Query("symbols") symbols: String): Call<EuroDollarModel>


}