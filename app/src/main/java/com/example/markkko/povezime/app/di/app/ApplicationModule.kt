package com.example.markkko.povezime.app.di.app


import android.content.Context
import android.content.SharedPreferences

import com.example.markkko.povezime.BuildConfig
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.app.util.AppSchedulerProvider
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.Constants
import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson

import java.io.File

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl

@Singleton
@Module
class ApplicationModule {
    @Provides
    @Singleton
    @Named("isDebug")
    internal fun provideIsDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    internal fun provideNetworkTimeoutInSeconds(): Int {
        return AppConstants.NETWORK_CONNECTION_TIMEOUT
    }

    @Provides
    @Singleton
    internal fun provideEndpoint(): HttpUrl? {
        return HttpUrl.parse(Constants.BASE_URL)
    }

    @Provides
    @Singleton
    internal fun provideAppScheduler(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    @Named("cacheSize")
    internal fun provideCacheSize(): Long {
        return AppConstants.CACHE_SIZE
    }

    @Provides
    @Singleton
    @Named("cacheMaxAge")
    internal fun provideCacheMaxAgeMinutes(): Int {
        return AppConstants.CACHE_MAX_AGE
    }

    @Provides
    @Singleton
    @Named("cacheMaxStale")
    internal fun provideCacheMaxStaleDays(): Int {
        return AppConstants.CACHE_MAX_STALE
    }

    @Provides
    @Singleton
    @Named("retryCount")
    fun provideApiRetryCount(): Int {
        return AppConstants.API_RETRY_COUNT
    }

    @Provides
    @Singleton
    @Named("cacheDir")
    internal fun provideCacheDir(context: Context): File {
        return context.cacheDir
    }

    @Provides
    @Named("isConnect")
    internal fun provideIsConnect(context: Context): Boolean {
        //return Utils.isConnected(context);
        return true
    }

    @Provides
    internal fun provideFirebaseAnalytics(context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }


    @Provides
    @Named("me")
    fun provideUser(prefs: SharedPreferences): User {
        val json = prefs.getString(AppConstants.PREF_USER, "")
        return Gson().fromJson(json, User::class.java)
    }


}
