package com.alessandrocandolini.currency_converter.business.usecase.rates

import com.alessandrocandolini.currency_converter.business.UseCase
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by alessandro.candolini on 04/09/2017.
 */

interface RefreshSourceUseCase : UseCase {

    fun refresh(): Observable<RefreshRates>

}

class TimerRefreshSourceUseCase(private val period: Long) : RefreshSourceUseCase {

    override fun refresh(): Observable<RefreshRates>
            = Observable.interval(period, TimeUnit.MINUTES)
            .map { RefreshRates.IGNORE }

}
