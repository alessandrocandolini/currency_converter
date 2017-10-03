package com.alessandrocandolini.currency_converter.business.entity

import java.util.*

/**
 * List of all [Rate].
 * Currencies we are not interested in will be automatically filtered when building the [EnumMap]
 * The type system here does encode the exhaustiveness of this map however (we can still have missing currencies)
 */

data class Rates(val rates : EnumMap<Currency, Map<Currency, ExchangeRate>>)