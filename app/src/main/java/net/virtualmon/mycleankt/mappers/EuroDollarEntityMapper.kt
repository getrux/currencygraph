package net.virtualmon.mycleankt.mappers

import net.virtualmon.domain.model.EuroDollarEntity
import net.virtualmon.mycleankt.models.EuroDollarEntityApp

class EuroDollarEntityMapper
{
    fun transform(euroDollarEntity: EuroDollarEntity): EuroDollarEntityApp
    {
        return EuroDollarEntityApp(euroDollarEntity.baseSymbol,
                euroDollarEntity.startDate,
                euroDollarEntity.endDate,
                euroDollarEntity.currencyInfo)

    }
}