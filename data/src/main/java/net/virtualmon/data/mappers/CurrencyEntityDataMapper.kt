package net.virtualmon.data.mappers

import net.virtualmon.data.models.EuroDollarModel

/**
 * Created by AlbertM on 21/04/19.
 */
class CurrencyEntityDataMapper {

    fun transform(euroDollarModel: EuroDollarModel) = euroDollarModel.toTransform()

}