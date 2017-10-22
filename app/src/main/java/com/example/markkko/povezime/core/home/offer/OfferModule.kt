package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.home.search.SearchInteractor
import com.example.markkko.povezime.core.home.search.SearchInteractorImpl
import com.example.markkko.povezime.core.home.search.SearchPresenter
import com.example.markkko.povezime.core.home.search.SearchPresenterImp
import dagger.Module
import dagger.Provides

@Module
class OfferModule {

    @Provides
    @ActivityScope
    internal fun provideOfferPresenter(offerPresenterImpl: OfferPresenterImpl): OfferPresenter {
        return offerPresenterImpl
    }

    @Provides
    @ActivityScope
    internal fun provideOfferInteractor(offerInteractorImpl: OfferInteractorImpl): OfferInteractor {
        return offerInteractorImpl
    }

}