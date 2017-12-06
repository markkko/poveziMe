package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.app.di.GoogleApiModule
import com.example.markkko.povezime.core.home.search.ISearchMVP
import com.example.markkko.povezime.core.home.search.SearchInteractor
import com.example.markkko.povezime.core.home.search.SearchPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [GoogleApiModule::class])
class OfferModule {

    @Provides
    internal fun provideOfferPresenter(offerPresenter: OfferPresenter): IOfferMVP.Presenter {
        return offerPresenter
    }

    @Provides
    internal fun provideOfferInteractor(offerInteractor: OfferInteractor): IOfferMVP.Interactor {
        return offerInteractor
    }

}