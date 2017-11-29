package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.app.di.GoogleApiModule
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