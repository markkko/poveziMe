package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.app.di.GoogleApiModule
import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.home.search.SearchInteractor
import com.example.markkko.povezime.core.home.search.SearchInteractorImpl
import com.example.markkko.povezime.core.home.search.SearchPresenter
import com.example.markkko.povezime.core.home.search.SearchPresenterImp
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(GoogleApiModule::class))
class OfferModule {

    @Provides
    internal fun provideOfferPresenter(offerPresenterImpl: OfferPresenterImpl): OfferPresenter {
        return offerPresenterImpl
    }

    @Provides
    internal fun provideOfferInteractor(offerInteractorImpl: OfferInteractorImpl): OfferInteractor {
        return offerInteractorImpl
    }

}