package com.example.markkko.povezime.app.di.app


import com.example.markkko.povezime.app.car.CarSubComponent
import com.example.markkko.povezime.app.di.GoogleApiModule
import com.example.markkko.povezime.app.home.offer.OfferSubComponent
import com.example.markkko.povezime.app.home.search.SearchSubComponent
import com.example.markkko.povezime.app.login.LoginSubComponent
import com.example.markkko.povezime.core.car.CarModule
import com.example.markkko.povezime.core.di.data.DataModule
import com.example.markkko.povezime.core.home.offer.OfferModule
import com.example.markkko.povezime.core.home.search.SearchModule
import com.example.markkko.povezime.core.login.LoginModule

import javax.inject.Singleton

import dagger.Component


@Singleton
@Component(modules = arrayOf(AndroidModule::class, ApplicationModule::class, DataModule::class))
interface ApplicationComponent {

    fun plus(module: LoginModule): LoginSubComponent
    fun plus(searchModule: SearchModule, googleApiModule: GoogleApiModule): SearchSubComponent
    fun plus(offerModule: OfferModule, googleApiModule: GoogleApiModule): OfferSubComponent
    fun plus(carModule: CarModule): CarSubComponent

}
