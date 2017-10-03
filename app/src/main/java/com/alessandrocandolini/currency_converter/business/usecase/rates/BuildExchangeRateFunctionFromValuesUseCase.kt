package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.UseCase
import com.alessandrocandolini.currency_converter.business.entity.ExchangeRate
import java.math.BigDecimal

/**
 * Use case to distill a [ExchangeRate] function from a table of conversion rates
 *
 * The purpose of this use case is two fold:
 * * it provides the bridge between the source of exchange rates and the function performing the conversion, so
 *   to not expose specific data structures to perform the conversion; this increases the flexibility of the consumers that can
 *   just rely on the function, while the inner details of its implementation remain hidden
 * * it is also the place where we can ensure (via tests, not via type system unfortunately) that the generated [ExchangeRate] always fulfills some criteria (transitivity etc)
 *   that we can't force on the type system
 */

interface BuildExchangeRateFunctionFromValuesUseCase : UseCase {

    fun apply(): ExchangeRate

}

internal class BuildExchangeRateFunctionFromValuesUseCaseImpl : BuildExchangeRateFunctionFromValuesUseCase {

    override fun apply(): ExchangeRate = { _, _ -> BigDecimal.ONE } // TODO

}
