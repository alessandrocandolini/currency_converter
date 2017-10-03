package com.alessandrocandolini.currency_converter.business.usecase.conversion

import com.alessandrocandolini.currency_converter.business.entity.*
import com.alessandrocandolini.currency_converter.business.entity.Currency
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.math.BigDecimal

/**
 * Created by alessandro.candolini on 03/09/2017.
 */
class ConvertMoneyUseCaseImplTest {

    private val usecase: ConvertMoneyUseCase = ConvertMoneyUseCaseImpl()

    @Test
    fun test_WhenExchangeRateIsOneAndCurrencyIsTheSame_MustReturnSameMoney() {// ideally, this must be embedded in the type system

        // given
        val fakeExchangeRate: ExchangeRate = { _, _ -> BigDecimal.ONE }
        val fakeOriginalMoney = Money(BigDecimal.valueOf(1.2), Currency.EUR)
        val fakeNewCurrency = Currency.EUR
        val stream: Observable<Triple<Money, Currency, ExchangeRate>> = Observable.just(Triple(fakeOriginalMoney, fakeNewCurrency, fakeExchangeRate))

        // when
        val testObserver = stream.compose(usecase).test()

        // then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValues(fakeOriginalMoney)


    }

    @Test
    fun test_WhenExchangeRateIsNotOne_MustReturnConvertedAmount() {

        // given
        val fakeExchangeRate: ExchangeRate = { _, _ -> BigDecimal.valueOf(10) }
        val fakeMoney = Money(BigDecimal.valueOf(1.2), Currency.EUR)
        val fakeNewCurrency = Currency.USD
        val stream: Observable<Triple<Money, Currency, ExchangeRate>> = Observable.just(Triple(fakeMoney, fakeNewCurrency, fakeExchangeRate))

        // when
        val testObserver = stream.compose(usecase).test()

        // then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValues(Money(BigDecimal.valueOf(12), Currency.USD))

    }

    @Test
    fun test_WhenMultipleValuesAreEmitted_MustReturnCorrectConvertedAmount() {

        // given
        val event1 = Triple<Money, Currency,ExchangeRate>(Money(BigDecimal.valueOf(1.2), Currency.EUR),
                Currency.USD, { _, _ -> BigDecimal.valueOf(10) } )

        val event2 = Triple<Money, Currency,ExchangeRate>(Money(BigDecimal.valueOf(2.7), Currency.USD),
                Currency.GBP,  { _, _ -> BigDecimal.valueOf(20) })

        val stream: Observable<Triple<Money, Currency, ExchangeRate>> = Observable.just(event1,event2)

        // when
        val testObserver = stream.compose(usecase).test()

        // then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValues(
                Money(BigDecimal.valueOf(12), Currency.USD),
                Money(BigDecimal.valueOf(54), Currency.GBP)
                )

    }
}