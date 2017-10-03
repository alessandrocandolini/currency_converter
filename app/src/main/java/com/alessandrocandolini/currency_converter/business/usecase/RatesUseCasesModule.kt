package com.alessandrocandolini.currency_converter.business.usecase

import com.alessandrocandolini.currency_converter.business.usecase.rates.*
import dagger.Module
import dagger.Provides

@Module
class RatesUseCasesModule {

    @Provides
    fun providesBuildExchangeRateFunctionFromValuesUseCase(): BuildExchangeRateFunctionFromValuesUseCase = BuildExchangeRateFunctionFromValuesUseCaseImpl()

    @Provides
    fun providesExchangeRatesUseCase(fetchExchangeRateFromRepositoryUseCase : FetchExchangeRateFromRepositoryUseCase) : ExchangeRatesUseCase = ExchangeRatesUseCaseImpl(fetchExchangeRateFromRepositoryUseCase)


    // repository will be provided by the data layer
    @Provides
    fun providesFetchExchangeRateFromRepositoryUseCase(rateRepository: RateRepository) : FetchExchangeRateFromRepositoryUseCase = FetchExchangeRateFromRepositoryUseCaseImpl(rateRepository)

    @Provides
    fun providesRefreshSourceUseCase() : RefreshSourceUseCase = TimerRefreshSourceUseCase(30)


}