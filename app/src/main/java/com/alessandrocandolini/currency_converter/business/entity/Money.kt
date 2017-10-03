package com.alessandrocandolini.currency_converter.business.entity

/** Data class for an amount of money and the currency. Naming convention consistent with Java 9 / joda-money
 *
 * For our needs we want the use [BigDecimal.compareTo] instead of [BigDecimal.equals] (we don't want to have money different because of scale),
 * so we override the equals and hasCode.
 *
 * */
data class Money(val amount: MoneyValue, val currency: Currency) {

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