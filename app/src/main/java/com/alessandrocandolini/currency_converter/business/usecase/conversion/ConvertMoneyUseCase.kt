package com.alessandrocandolini.currency_converter.business.usecase.conversion

import com.alessandrocandolini.currency_converter.business.UseCase
import com.alessandrocandolini.currency_converter.business.entity.Currency
import com.alessandrocandolini.currency_converter.business.entity.ExchangeRate
import com.alessandrocandolini.currency_converter.business.entity.Money
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

/**
 * Use case that acts transforming a given stream of money to an output stream of the converted values
 *
 * Original stream emits triples of:
 * * [Money]: the money to convert
 * * [Currency]: the new currency
 * * [ExchangeRate] : the function that converts the amount from the original currency to the new currency
 *
 * The transformed stream emits the money in the new currency
 */

interface ConvertMoneyUseCase : UseCase, ObservableTransformer<Triple<Money, Currency, ExchangeRate>, Money>

internal class ConvertMoneyUseCaseImpl : ConvertMoneyUseCase {

        override fun apply(upstream: Observable<Triple<Money, Currency, ExchangeRate>>): ObservableSource<Money>
            = upstream.map { (money, currency, exchangeRate) -> Money(money.amount * exchangeRate(money.currency, currency), currency) }

}