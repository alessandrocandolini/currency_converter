package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.UseCase
import com.alessandrocandolini.currency_converter.business.entity.Currency
import com.alessandrocandolini.currency_converter.business.entity.Rate
import com.alessandrocandolini.currency_converter.business.entity.Rates
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers


/** Auxiliary class to trigger refresh of the exchange rates*/
enum class RefreshRates {
    IGNORE
}

/**
 * Use case to retrieve all [Rates] (for all supported currencies) in response of a stream of [RefreshRates]
 *
 * The fact that this is a [ObservableTransformer] allows us to decouple the source of refresh from the actual process of refreshing
 */
interface RefreshRatesUseCase : UseCase,  ObservableTransformer<RefreshRates, Rate>

/**
 * Implementation of [RefreshRatesUseCase] that chains [GetExchangeRateUseCase] for all possible values
 *
 * Another implementation is possible to limit the bandwidth usage, by retrieving just the minimum amount of exchange rates needed and then perform the DFS on it
 */
class RefreshRatesUseCaseImpl(
        private val emitSupportedCurrenciesUseCase : EmitSupportedCurrenciesUseCase,
        private val exchangeRatesUseCase : ExchangeRatesUseCase,
        private val fetchExchangeRateFromRepositoryUseCase : FetchExchangeRateFromRepositoryUseCase
)
    : RefreshRatesUseCase {

    override fun apply(upstream: Observable<RefreshRates>): ObservableSource<Rate> {

        val stream : Observable<Observable<Rate>> = emitSupportedCurrenciesUseCase.apply()
//                .compose(exchangeRatesUseCase)
                .map { currency -> fetchExchangeRateFromRepositoryUseCase.apply(currency).toObservable() }

//        val stream1 : Observable<Rate> = Observable.just(Currency.USD).compose(exchangeRatesUseCase)
//        val stream2 : Observable<Rate> = Observable.just(Currency.EUR).compose(exchangeRatesUseCase)
//        val stream3 : Observable<Rate> = Observable.just(Currency.GBP).compose(exchangeRatesUseCase)
//
//        return Observable.zip<Rate, Rate,Rate,Rate>(stream1,stream2, stream3, Function3 { t1, t2, t3 -> t1 } )

        val rate : Rate? = null

        return Observable.empty()

    }


}