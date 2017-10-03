package com.alessandrocandolini.currency_converter.business.entity

import java.math.BigDecimal

/**
 * Typealias for the amount in the price.
 *
 * It's a [BigDecimal]. This is consistent with Java/Oracle recommendations, Java9's
 * Money and Currency API (JSR 354) and the pre-Java9 joda-money library
 */
typealias MoneyValue = BigDecimal