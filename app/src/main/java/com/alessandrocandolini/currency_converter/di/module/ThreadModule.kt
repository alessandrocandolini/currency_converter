package com.alessandrocandolini.currency_converter.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by alessandro.candolini on 03/09/2017.
 */

@Module
class ThreadModule {

    @Provides
    fun providesSchedulerProvider() : SchedulerProvider = SchedulerProviderImpl(AndroidSchedulers.mainThread(), Schedulers.io())

    @Provides
    fun providesComputationSchedulerProvider() : ComputationSchedulerProvider = ComputationSchedulerProviderImpl()

}

interface SchedulerProvider {

    fun io() : Scheduler
    fun ui() : Scheduler

}

private class SchedulerProviderImpl(private val uiScheduler : Scheduler,
                                      private val ioScheduler : Scheduler
                                      ) : SchedulerProvider {
    override fun io(): Scheduler  = ioScheduler
    override fun ui(): Scheduler  = uiScheduler

}


interface ComputationSchedulerProvider {

    fun scheduler() : Scheduler
}

private class ComputationSchedulerProviderImpl() : ComputationSchedulerProvider {
    override fun scheduler(): Scheduler = Schedulers.computation()
}