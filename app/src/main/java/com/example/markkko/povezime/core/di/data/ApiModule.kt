package com.example.markkko.povezime.core.di.data

import com.example.markkko.povezime.core.data.apis.CarApi
import com.example.markkko.povezime.core.data.apis.UserApi
import com.example.markkko.povezime.core.data.apis.OfferApi
import com.example.markkko.povezime.core.data.apis.SearchApi
import com.google.gson.Gson

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {

    @Provides
    fun provideLoginApiService(retrofit: Retrofit): UserApi =
            retrofit.create(UserApi::class.java)

    @Provides
    fun provideSearchApiService(retrofit: Retrofit): SearchApi =
            retrofit.create(SearchApi::class.java)

    @Provides
    fun provideOfferApiService(retrofit: Retrofit): OfferApi =
            retrofit.create(OfferApi::class.java)

    @Provides
    fun provideCarApiService(retrofit: Retrofit): CarApi = retrofit.create(CarApi::class.java)

    @Provides
    fun provideRetrofit(baseUrl: HttpUrl?, converterFactory: Converter.Factory, callAdapterFactory: CallAdapter.Factory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideRxJavaCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }
}
