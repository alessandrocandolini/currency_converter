package com.alessandrocandolini.currency_converter.business.usecase

import com.alessandrocandolini.currency_converter.business.entity.*
import junit.framework.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.math.BigDecimal

/**
 * Created by alessandro.candolini on 03/09/2017.
 */
class ConvertMoneyUseCaseImplTest {

    @Rule
    @JvmField
    val mockitoRule : MockitoRule = MockitoJUnit.rule()

    private val usecase: ConvertMoneyUseCase = ConvertMoneyUseCaseImpl()

    @Test
    fun test_WhenExchangeRateIsOneAndCurrencyIsTheSame_MustReturnSameMoney() {// ideally, this must be embedded in the type system

        // given
        val fakeExchangeRate : ExchangeRate = { _, _ -> BigDecimal.ONE }
        val fakeMoney = Money( BigDecimal.valueOf(1.2), Currency.EUR)
        val fakeNewCurrency = Currency.EUR

        // when
        val output = usecase.execute(fakeMoney,fakeNewCurrency,fakeExchangeRate)

        // then
        Assert.assertEquals(fakeMoney,output)


    }

    @Test
    fun test_WhenExchangeRateIsNotOne_MustReturnConvertedAmount() {

        // given
        val fakeExchangeRate : ExchangeRate = { _, _ -> BigDecimal.valueOf(10) }
        val fakeMoney = Money( BigDecimal.valueOf(1.2), Currency.EUR)
        val fakeNewCurrency = Currency.USD

        // when
        val output = usecase.execute(fakeMoney,fakeNewCurrency,fakeExchangeRate)

        // then
        Assert.assertEquals(Money(BigDecimal.valueOf(12),Currency.USD),output)

    }
}