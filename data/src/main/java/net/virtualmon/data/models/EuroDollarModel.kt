package net.virtualmon.data.models
import com.google.gson.annotations.SerializedName
import net.virtualmon.domain.model.EuroDollarEntity


data class EuroDollarModel(
        @SerializedName("base") val baseSymbol: String,
        @SerializedName("start_at") val startDate: String,
        @SerializedName("end_at") val endDate: String,
        @SerializedName("rates") val currencyInfo:  Map<String, Map<String,Double>>


){

    fun toTransform() : EuroDollarEntity = EuroDollarEntity(baseSymbol,startDate,endDate,currencyInfo)

}


//fun toTransform() : EuroDollarEntity = EuroDollarEntity(baseSymbol, .toCityEntity(), cnt, cod, list.map { it.toXentity() }, message)