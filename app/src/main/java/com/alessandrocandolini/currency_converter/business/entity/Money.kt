package com.alessandrocandolini.currency_converter.business.entity

import java.math.BigDecimal
import java.util.*

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

/** Exhaustive list of supported currencies */
//sealed class Currency(val currencyCode : String)
//class Dollar() : Currency("USD")
//class Euro() : Currency("EUR")
//class Pound() : Currency("GBP")

enum class Currency(val currencyCode: String) {

    EUR("EUR"),
    USD("USD"),
    GBP("GBP");

}

/** Data class for an amount of money and the currency. Naming convention consistent with Java 9 / joda-money */
data class Money(val amount: MoneyValue, val currency: Currency) {

    // for our needs we want the use "compareTo" instead of equals on bigdecimals, so we override the equals and hasCode
    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Money

        if (currency != other.currency) return false

        if (amount.compareTo(other.amount) != 0) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 17
        val value : Double = amount.toDouble()
        result = 31 * result + value.hashCode()
        result = 31 * result + currency.hashCode()
        return result
    }
}

/** Type for representing a function that returns the exchange rate between two currencies */
// Additional constrains can't be embedded into the weak type system of kotlin, for example
// ExchangeRate(x,y) = 1/ ExchangeRate(y,x) (assuming no bank costs)
// transitivity,
// the rate must be one if newCurrency is equal to the original currency
// non-zero value (dependent types
// the fact that this function is total (should provide implementation for all supported currencies)
// Also, I cant force purity of this function
typealias ExchangeRate = (originalCurrency: Currency, newCurrency: Currency) -> ExchangeRateValue

/** Type for representing a function that performs the conversion */
typealias CurrencyConverter = (money: Money, newCurrency: Currency) -> Money




