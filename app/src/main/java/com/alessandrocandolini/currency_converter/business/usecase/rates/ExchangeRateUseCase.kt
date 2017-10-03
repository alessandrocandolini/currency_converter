package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.UseCase
import com.alessandrocandolini.currency_converter.business.entity.Currency
import com.alessandrocandolini.currency_converter.business.entity.Rate
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

/**
 * Use case to transform a stream of [Currency] into a stream of the corresponding [Rates] of conversion
 */
interface ExchangeRatesUseCase : UseCase, ObservableTransformer<Currency, Rate>

/**
 * Implementation of [ExchangeRatesUseCase] that flatmaps into [FetchExchangeRateFromRepositoryUseCase]
 * The flatmap is hidden inside the transformer so the main stream does not need to be aware of this choice
 * or about the existence of [FetchExchangeRateFromRepositoryUseCase]
 */
class ExchangeRatesUseCaseImpl(private val fetchExchangeRateFromRepositoryUseCase : FetchExchangeRateFromRepositoryUseCase)
    : ExchangeRatesUseCase {

    override fun apply(upstream: Observable<Currency>): ObservableSource<Rate> =
       upstream.flatMap { currency -> fetchExchangeRateFromRepositoryUseCase.apply(currency).toObservable() }

}