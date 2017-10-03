package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.entity.Currency
import org.junit.Test

/**
 * Created by alessandro.candolini on 04/09/2017.
 */
class EmitSupportedCurrenciesUseCaseImplTest {

    private val usecase: EmitSupportedCurrenciesUseCase = EmitSupportedCurrenciesUseCaseImpl()

    @Test
    fun test_AllAndOnlySupportedCurrenciesAreListed() {

        // when
        val testObserver = usecase.apply().test()

        //then
        testObserver.assertValues(Currency.GBP,Currency.EUR, Currency.USD)

    }

}