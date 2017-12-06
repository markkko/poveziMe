package com.example.markkko.povezime.core.di

import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.auth.AuthModule
import com.example.markkko.povezime.core.car.CarModule
import com.example.markkko.povezime.core.home.offer.OfferModule
import com.example.markkko.povezime.core.home.search.SearchModule
import com.example.markkko.povezime.core.results.ResultModule
import dagger.Module

@ActivityScope
@Module(includes = [(AuthModule::class), (SearchModule::class), (OfferModule::class), (CarModule::class), (ResultModule::class)])
class FeaturesModule
