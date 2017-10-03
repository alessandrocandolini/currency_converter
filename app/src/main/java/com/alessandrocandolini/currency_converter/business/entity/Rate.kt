package com.alessandrocandolini.currency_converter.business.entity

import java.util.*

/**
 * Data model for conversion rates.
 *
 * [base] is the original currency, [conversionMap] stores the exchange factors from [base] to any other listed currency.
 * It's also useful to keep track of the latest update.
 */

data class Rate(val baseCurrency : Currency, val conversionMap : Map<Currency, ExchangeRateValue>, val lastUpdate : Date)
