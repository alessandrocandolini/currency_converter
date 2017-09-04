package com.alessandrocandolini.currency_converter.business.usecase

import com.alessandrocandolini.currency_converter.business.UseCase
import com.alessandrocandolini.currency_converter.business.entity.Currency
import com.alessandrocandolini.currency_converter.business.entity.ExchangeRate
import com.alessandrocandolini.currency_converter.business.entity.ExchangeRateValue
import com.alessandrocandolini.currency_converter.business.entity.Money
import dagger.Module
import dagger.Provides

/**
 * Created by alessandro.candolini on 03/09/2017.
 */

interface ConvertMoneyUseCase : UseCase {

    fun execute(currentMoney: Money, newCurrency: Currency, exchangeRate: ExchangeRate): Money

}


// 1. can't make the implementation package-visible in Kotlin, so i need to make it internal (to use it in the test)
// 2. I can use the dagger module also in the tests but this would make the test setup less straightforward
// 3. the weak type system of kotlin does not allow to embed additional information in a straightforward way, namely:
// the rate should be the rate associated with those two currencies
// Generics can (partially) help to achieve this, but will make syntax more involved
internal class ConvertMoneyUseCaseImpl : ConvertMoneyUseCase {

    override fun execute(currentMoney: Money, newCurrency: Currency, exchangeRate: ExchangeRate)
            = Money(currentMoney.amount * exchangeRate(currentMoney.currency, newCurrency), newCurrency)

}


@Module
class ConvertMoneyModule {

    @Provides
    fun providesConvertMoney(): ConvertMoneyUseCase = ConvertMoneyUseCaseImpl()

}
