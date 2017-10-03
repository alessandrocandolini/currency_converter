package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.UseCase
import com.alessandrocandolini.currency_converter.business.entity.Currency
import io.reactivex.Observable

/**
 * Use case to emit all and only the supported [Currency] values.
 *
 * This is not an observable transformer, it does not modify an existing stream.
 * It acts as a stream source that emits all possible supported currencies.
 */
interface EmitSupportedCurrenciesUseCase : UseCase {

    fun apply(): Observable<Currency>

}

/**
 * Simple implementation of [EmitSupportedCurrenciesUseCase] by unwrapping the [Currency] enum.
 *
 * This use case is almost useless now, we could have used `Currency.values().toList()` directly.
 * However, in the future in might be the case that we want the list of supported currencies to be  dynamic.
 */
class EmitSupportedCurrenciesUseCaseImpl : EmitSupportedCurrenciesUseCase {

    override fun apply(): Observable<Currency> = Observable.fromIterable(Currency.values().toList())
}