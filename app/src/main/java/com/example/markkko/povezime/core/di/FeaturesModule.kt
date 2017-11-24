package com.example.markkko.povezime.core.di

import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.auth.AuthModule
import com.example.markkko.povezime.core.home.offer.OfferModule
import com.example.markkko.povezime.core.home.search.SearchModule
import dagger.Module

@ActivityScope
@Module(includes = arrayOf(AuthModule::class, SearchModule::class, OfferModule::class))
class FeaturesModule
