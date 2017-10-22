package com.example.markkko.povezime.app

import android.app.Application
import android.content.Context
import com.example.markkko.povezime.app.car.CarSubComponent

import com.example.markkko.povezime.app.di.GoogleApiModule
import com.example.markkko.povezime.app.di.app.AndroidModule
import com.example.markkko.povezime.app.di.app.ApplicationComponent
import com.example.markkko.povezime.app.di.app.DaggerApplicationComponent
import com.example.markkko.povezime.app.home.offer.OfferSubComponent
import com.example.markkko.povezime.app.home.search.SearchSubComponent
import com.example.markkko.povezime.app.login.LoginSubComponent
import com.example.markkko.povezime.core.home.search.SearchModule
import com.example.markkko.povezime.core.login.LoginModule


class PoveziMeApplication : Application() {

    var loginSubComponent: LoginSubComponent? = null
    var searchSubComponent: SearchSubComponent? = null
    var offerSubComponent: OfferSubComponent? = null
    var carSubComponent: CarSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        component = createComponent()
    }

    fun createComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
    }

    companion object {

        var component: ApplicationComponent? = null
            private set

        operator fun get(context: Context): PoveziMeApplication {
            return context.applicationContext as PoveziMeApplication
        }
    }

}
