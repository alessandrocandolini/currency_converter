package com.alessandrocandolini.currency_converter.di.component

import com.alessandrocandolini.currency_converter.di.module.ApplicationModule
import com.alessandrocandolini.currency_converter.di.module.NetworkModule
import com.alessandrocandolini.currency_converter.di.module.RetrofitModule
import com.alessandrocandolini.currency_converter.di.module.SerializationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by alessandro.candolini on 03/09/2017.
 */

@Singleton // singleton by "definition" (hosted in Application class)
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkModule::class,
        RetrofitModule::class,
        SerializationModule::class
))
interface ApplicationComponent