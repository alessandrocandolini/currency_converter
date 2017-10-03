package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.UseCase
import com.alessandrocandolini.currency_converter.business.entity.Currency
import com.alessandrocandolini.currency_converter.business.entity.Rate
import io.reactivex.Single


/**
 * Use case to retrieve [Rate] for a given [originalCurrency] via the repository
 *
 * This is not a transformer, it acts as a source stream and can be triggered in isolation if you want to
 * retry the [Rate] of a specific [Currency] outside an external stream
 */
interface FetchExchangeRateFromRepositoryUseCase : UseCase {

    fun apply(originalCurrency: Currency): Single<Rate>

}

/**
 * Straightforward implementation of [FetchExchangeRateFromRepositoryUseCase] that delegates to [RateRepository]
 */
class FetchExchangeRateFromRepositoryUseCaseImpl(private val rateRepository: RateRepository) : FetchExchangeRateFromRepositoryUseCase {

    override fun apply(originalCurrency: Currency): Single<Rate> = rateRepository.fetch(originalCurrency)
}

