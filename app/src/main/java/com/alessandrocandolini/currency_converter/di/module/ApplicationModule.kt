package com.alessandrocandolini.currency_converter.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by alessandro.candolini on 03/09/2017.
 */

@Module
class ApplicationModule(private val application : Application) {

    @Provides
    fun provideApplication() : Application = application

    @Provides
    fun provideApplicationContext() : Context = application

}