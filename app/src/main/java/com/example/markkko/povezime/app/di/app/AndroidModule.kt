package com.example.markkko.povezime.app.di.app

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources

import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.util.AppConstants

import javax.inject.Singleton

import dagger.Module
import dagger.Provides


@Singleton
@Module
class AndroidModule(private val application: PoveziMeApplication) {

    @Provides
    @Singleton
    internal fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun provideResources(): Resources = application.resources

    @Singleton
    @Provides
    internal fun provideSharedPreferences(): SharedPreferences =
            application.getSharedPreferences(AppConstants.PREF_USER, Context.MODE_PRIVATE)

}
