package com.alessandrocandolini.currency_converter

import android.app.Application
import com.alessandrocandolini.currency_converter.di.component.ApplicationComponent
import com.alessandrocandolini.currency_converter.di.component.DaggerApplicationComponent
import com.alessandrocandolini.currency_converter.di.module.ApplicationModule

/**
 * Created by alessandro.candolini on 02/09/2017.
 */

class BaseApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
