package com.alessandrocandolini.currency_converter.business.entity

import junit.framework.Assert
import org.junit.Test
import java.math.BigDecimal

/**
 * Created by alessandro.candolini on 04/09/2017.
 */
class MoneyTest {

    @Test
    fun test_WhenTheyAreTheSame_MustBeEqual() {

        // given
        val fakeMoney = Money(BigDecimal.ONE, Currency.EUR)

        // then
        Assert.assertEquals(fakeMoney,fakeMoney)

    }

    @Test
    fun test_WhenTheyShareSameCurrencyAndValueAndScala_MustBeEqual() {

        // given
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = Money(BigDecimal.valueOf(1), Currency.EUR)

        // then
        Assert.assertEquals(fakeMoney1,fakeMoney2)

    }

    @Test
    fun test_WhenTheyShareSameCurrencyAndValueButNotScale_MustBeEqual() {

        // given
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = Money(BigDecimal.valueOf(1.00), Currency.EUR)

        // then
        Assert.assertEquals(fakeMoney1,fakeMoney2)
    }

    @Test
    fun test_WhenDifferentTypes_MustBeDifferent() {

        // given
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = "dejuewg"

        // then
        Assert.assertNotSame(fakeMoney1,fakeMoney2)

    }

    @Test
    fun test_WhenDifferentValue_MustBeDifferent() {

        // given
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = Money(BigDecimal.valueOf(2), Currency.EUR)

        // then
        Assert.assertNotSame(fakeMoney1,fakeMoney2)
    }

    @Test
    fun test_WhenDifferentCurrency_MustBeDifferent() {

        // given
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = Money(BigDecimal.valueOf(1), Currency.USD)

        // then
        Assert.assertNotSame(fakeMoney1,fakeMoney2)
    }

    @Test
    fun test_WhenAddingSameMoneyTwiceToAHashSet_ListSizeMustBeOne() {

        // given
        val list : MutableCollection<Money> = hashSetOf()
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)

        // when
        list.add(fakeMoney1)
        list.add(fakeMoney1)

        // then
        Assert.assertEquals(1,list.size)
    }

    @Test
    fun test_WhenAddingSameValueTwiceToAHashSet_ListSizeMustBeOne() {

        // given
        val collection : MutableCollection<Money> = hashSetOf()
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = Money(BigDecimal.valueOf(1), Currency.EUR)

        // when
        collection.add(fakeMoney1)
        collection.add(fakeMoney2)

        // then
        Assert.assertEquals(1,collection.size)
    }



    @Test
    fun test_WhenAddingMoneyWithSameValueAndCurrencyToAHashSet_ListSizeMustBeOne() {

        // given
        val collection : MutableCollection<Money> = hashSetOf()
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = Money(BigDecimal.valueOf(1.00), Currency.EUR)

        // when
        collection.add(fakeMoney1)
        collection.add(fakeMoney2)

        // then
        Assert.assertEquals(1,collection.size)
    }

    @Test
    fun test_WhenAddingMoneyWithSameValueAndDifferentCurrencyToAHashSet_ListSizeMustBeTwo() {

        // given
        val collection : MutableCollection<Money> = hashSetOf()
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = Money(BigDecimal.valueOf(1.00), Currency.USD)

        // when
        collection.add(fakeMoney1)
        collection.add(fakeMoney2)

        // then
        Assert.assertEquals(2,collection.size)
    }

    @Test
    fun test_WhenAddingMoneyWithDifferentValueAndSameCurrencyToAHashSet_ListSizeMustBeTwo() {

        // given
        val collection : MutableCollection<Money> = hashSetOf()
        val fakeMoney1 = Money(BigDecimal.valueOf(1), Currency.EUR)
        val fakeMoney2 = Money(BigDecimal.valueOf(2), Currency.EUR)

        // when
        collection.add(fakeMoney1)
        collection.add(fakeMoney2)

        // then
        Assert.assertEquals(2,collection.size)
    }
}