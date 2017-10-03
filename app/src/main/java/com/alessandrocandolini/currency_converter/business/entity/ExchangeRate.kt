package com.alessandrocandolini.currency_converter.business.entity

/** Typealias for exchange rate, consistent with [MoneyValue] */
typealias ExchangeRateValue = MoneyValue

/**
 * Type for representing a function that returns the exchange rate between two currencies
 *
 * The weak type system of Kotlin does not allow to embed additional constrains on the expected behaviour of this function, for example:
 * * ExchangeRate(x,y) = 1/ ExchangeRate(y,x) and transitivity (assuming no bank costs, in a IDEAL world, so probably better).. would be nice to have pattern matching
 * * the rate must be one if [newCurrency] is equal to [originalCurrency]
 * * non-zero output value (dependent types)
 * * the fact that this function is total (should provide implementation for all supported currencies)
 *
 * We can partially alleviate this by relying on [BuildExchangeRateFunctionFromValuesUseCase] as a source of these functions.
 *
 * Also, I can't force purity of this function at type system level, but ideally this function should be pure.
 * (Different exchange rates at different times would be described by a stream of different pure functions)
 */

typealias ExchangeRate = (originalCurrency: Currency, newCurrency: Currency) -> ExchangeRateValue