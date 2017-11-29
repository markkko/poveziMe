package com.example.markkko.povezime.core.di

import com.example.markkko.povezime.app.auth.*
import com.example.markkko.povezime.app.car.AddCarActivity
import com.example.markkko.povezime.app.home.offer.OfferFragment
import com.example.markkko.povezime.app.home.search.SearchFragment
import dagger.Subcomponent


@Subcomponent(modules = arrayOf(FeaturesModule::class))
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {

        fun featuresModule(featuresModule: FeaturesModule): Builder
        fun build(): ActivityComponent
    }

    fun inject(fragment: SearchFragment)
    fun inject(fragment: OfferFragment)

    fun inject(fragment: AddCarActivity.AddCarFragment)

    fun inject(activity: LoginActivity)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: CompleteInfoFragment)

}