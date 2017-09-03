package com.alessandrocandolini.currency_converter.di.component

import com.alessandrocandolini.currency_converter.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by alessandro.candolini on 03/09/2017.
 */

@Singleton // singleton by "definition" (hosted in Application class)
@Component(modules = arrayOf(
        ApplicationModule::class
))
interface ApplicationComponent