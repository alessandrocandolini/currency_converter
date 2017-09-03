package com.alessandrocandolini.currency_converter.business.entity

import java.math.BigDecimal

/**
 * Created by alessandro.candolini on 03/09/2017.
 */

/**
 * Typealias for the amount in the price.
 *
 * It's a {@link BigDecimal}. This is consistent with Java/Oracle recommendations,
 * Money and Currency API (JSR 354) of Java 9 and joda-money library
 */
typealias MoneyValue = BigDecimal

/** Typealias for exchange rate, consistent with {@link MoneyValue} */
typealias ExchangeRateValue = BigDecimal

//sealed class Currency(val currency : String)
//class Dollar() : Currency("USD")
//class Euro() : Currency("EUR")
//class Pound() : Currency("GBP")

/** Exhaustive list of supported currencies */
enum class Currency(val currencyCode: String) {

    EUR("EUR"),
    USD("USD"),
    GBP("GBP");

}

/** Data class for an amount of money and the currency. Naming convention consistent with Java 9 / joda-money */
data class Money(val amount: MoneyValue, val currency: Currency)

/** Type for representing a function that returns the exchange rate between two currencies */
// Additional constrains can't be embedded into the weak type system of kotlin, for example
// ExchangeRate(x,y) = 1/ ExchangeRate(y,x) (assuming no bank costs)
// transitivity,
// non-zero value (dependent types
// the fact that this function is total (should provide implementation for all supported currencies)
// Also, I cant force purity of this function
typealias ExchangeRate = (originalCurrency: Currency, newCurrency: Currency) -> ExchangeRateValue

/** Type for representing a function that performs the conversion */
// same limitations as per previous function
typealias CurrencyConverter = (money: Money, newCurrency: Currency) -> Money




