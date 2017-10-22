package com.example.markkko.povezime.core.di.data

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


@Module
class ClientModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                            @Named("networkTimeoutInSeconds") networkTimeoutInSeconds: Int,
                            @Named("isDebug") isDebug: Boolean): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
                //.addNetworkInterceptor(cacheInterceptor)
                //.addInterceptor(retryInterceptor)
                //.cache(cache)
                .connectTimeout(networkTimeoutInSeconds.toLong(), TimeUnit.SECONDS)

        //show logs if app is in Debug mode
        if (isDebug)
            okHttpClient.addInterceptor(loggingInterceptor)

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideCache(@Named("cacheDir") cacheDir: File, @Named("cacheSize") cacheSize: Long): Cache? {
        var cache: Cache? = null

        try {
            cache = Cache(File(cacheDir.path, HTTP_CACHE_PATH), cacheSize)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return cache
    }

    companion object {

        private val HTTP_CACHE_PATH = "http-cache"
        private val CACHE_CONTROL = "Cache-Control"
        private val PRAGMA = "Pragma"
    }
}
