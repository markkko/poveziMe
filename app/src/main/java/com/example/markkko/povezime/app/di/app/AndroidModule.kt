package com.example.markkko.povezime.app.di.app

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.location.Geocoder
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.util.GeocoderUtils
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Module
class AndroidModule(private val application: PoveziMeApplication) {

    @Provides
    @Singleton
    @Named(AppConstants.APPLICATION_CONTEXT)
    internal fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun provideResources(): Resources = application.resources

    @Singleton
    @Provides
    internal fun provideSharedPreferences(): SharedPreferences =
            application.getSharedPreferences(AppConstants.PREF_USER, Context.MODE_PRIVATE)

    @Provides
    internal fun provideGeocoder(): GeocoderUtils =
            GeocoderUtils(Geocoder(application, Locale.getDefault()))

}
