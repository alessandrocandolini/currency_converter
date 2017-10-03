package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.entity.Currency
import com.alessandrocandolini.currency_converter.business.entity.ExchangeRateValue
import com.alessandrocandolini.currency_converter.business.entity.Rate
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by alessandro.candolini on 04/09/2017.
 */
class FetchExchangeRateFromRepositoryUseCaseImplTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: RateRepository

    @InjectMocks
    private lateinit var usecase : FetchExchangeRateFromRepositoryUseCaseImpl

    @Test
    fun test_WhenRepositorySucceed_MustReturnSameValueOfRepository() {

        // given (data preparation)
        val fakeValues : EnumMap<Currency, ExchangeRateValue> = EnumMap<Currency, ExchangeRateValue>(Currency::class.java)
        fakeValues.put(Currency.EUR, BigDecimal.valueOf(2))
        fakeValues.put(Currency.GBP, BigDecimal.valueOf(2))
        val fakeRate = Rate(Currency.USD, fakeValues,Date())
        val dummyOriginalCurrency = Currency.USD
        Mockito.doReturn(Single.just(fakeRate)).`when`(repository).fetch(dummyOriginalCurrency)

        // when
        val testObservable = usecase.apply(dummyOriginalCurrency).test()

        // then
        testObservable.assertNoErrors()
        testObservable.assertComplete()
        testObservable.assertValues(fakeRate)

    }

    @Test
    fun test_WhenRepositoryThrowsError_MustPropagateameError() {

        // given (data preparation)
        val fakeError: Throwable = Mockito.mock(Throwable::class.java)
        val dummyOriginalCurrency = Currency.USD
        Mockito.doReturn(Single.error<Rate>(fakeError)).`when`(repository).fetch(dummyOriginalCurrency)

        // when
        val testObservable = usecase.apply(dummyOriginalCurrency).test()

        // then
        testObservable.assertError(fakeError)
        testObservable.assertNoValues()

    }
}