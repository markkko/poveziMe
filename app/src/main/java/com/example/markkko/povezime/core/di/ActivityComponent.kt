package com.example.markkko.povezime.core.di

import com.example.markkko.povezime.app.auth.*
import com.example.markkko.povezime.app.car.AddCarActivity
import com.example.markkko.povezime.app.home.HomeActivity
import com.example.markkko.povezime.app.home.offer.OfferFragment
import com.example.markkko.povezime.app.home.search.SearchFragment
import com.example.markkko.povezime.app.profile.ProfileActivity
import com.example.markkko.povezime.app.requests.AllRequestsFragment
import com.example.markkko.povezime.app.results.offer.OfferResultsFragment
import com.example.markkko.povezime.app.results.search.SearchResultsFragment
import dagger.Subcomponent


@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }

    fun inject(activity: HomeActivity)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: OfferFragment)

    fun inject(fragment: SearchResultsFragment)
    fun inject(fragment: OfferResultsFragment)

    fun inject(fragment: AllRequestsFragment)

    fun inject(fragment: ProfileActivity.ProfileFragment)

    fun inject(fragment: AddCarActivity.AddCarFragment)

    fun inject(activity: LoginActivity)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: CompleteInfoFragment)

}