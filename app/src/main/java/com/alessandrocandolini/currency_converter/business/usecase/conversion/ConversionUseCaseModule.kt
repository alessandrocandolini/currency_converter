package com.alessandrocandolini.currency_converter.business.usecase.conversion

import dagger.Module
import dagger.Provides

/** Module to provide implementation of the [ConvertMoneyUseCase] interface */
@Module
class ConversionUseCaseModule {

    @Provides
    fun providesConvertMoneyUseCase(): ConvertMoneyUseCase = ConvertMoneyUseCaseImpl()

}