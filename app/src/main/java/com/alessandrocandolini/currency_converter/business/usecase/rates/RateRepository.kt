package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.entity.Currency
import com.alessandrocandolini.currency_converter.business.entity.Rate
import io.reactivex.Single

/**
 * Interface for the repository. The actual implementation is provided by the data layer.
 * The signature of this interface is somehow inspired by the API format, but ideally should be designed upfront
 * without being aware of the api layer, adapters will be created in the data layer
 */

interface RateRepository {

    fun fetch(originalCurrency: Currency): Single<Rate>

}
