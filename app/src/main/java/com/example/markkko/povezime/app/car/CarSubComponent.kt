package com.example.markkko.povezime.app.car

import com.example.markkko.povezime.app.di.GoogleApiModule
import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.app.home.search.SearchFragment
import com.example.markkko.povezime.core.home.search.SearchModule

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(SearchModule::class))
interface CarSubComponent {

    fun inject(activity: AddCarActivity)

}
