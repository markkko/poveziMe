package com.example.markkko.povezime.core.di

import com.example.markkko.povezime.core.auth.completeInfo.CompleteInfoInteractor
import com.example.markkko.povezime.core.auth.completeInfo.ICompleteInfoMVP
import com.example.markkko.povezime.core.auth.login.ILoginMVP
import com.example.markkko.povezime.core.auth.login.LoginInteractor
import com.example.markkko.povezime.core.car.CarInteractor
import com.example.markkko.povezime.core.car.ICarMVP
import com.example.markkko.povezime.core.di.data.DataModule
import com.example.markkko.povezime.core.home.HomeInteractor
import com.example.markkko.povezime.core.home.IHomeMVP
import com.example.markkko.povezime.core.home.offer.IOfferMVP
import com.example.markkko.povezime.core.home.offer.OfferInteractor
import com.example.markkko.povezime.core.home.search.ISearchMVP
import com.example.markkko.povezime.core.home.search.SearchInteractor
import com.example.markkko.povezime.core.profile.IProfileMVP
import com.example.markkko.povezime.core.profile.ProfileInteractor
import com.example.markkko.povezime.core.requests.AllRequestsInteractor
import com.example.markkko.povezime.core.requests.IRequestsMVP
import com.example.markkko.povezime.core.results.offer.IOfferResultsMVP
import com.example.markkko.povezime.core.results.offer.OfferResultInteractor
import com.example.markkko.povezime.core.results.search.ISearchResultsMVP
import com.example.markkko.povezime.core.results.search.SearchResultInteractor
import dagger.Module
import dagger.Provides


@Module(includes = [(DataModule::class)])
class InteractorModule {

    @Provides
    fun provideLoginInteractor(interactor: LoginInteractor): ILoginMVP.Interactor =
            interactor

    @Provides
    fun provideCompleteInfoInteractor(interactor: CompleteInfoInteractor): ICompleteInfoMVP.Interactor =
            interactor

    @Provides
    internal fun provideOfferInteractor(offerInteractor: OfferInteractor): IOfferMVP.Interactor =
            offerInteractor

    @Provides
    internal fun provideSearchInteractor(searchSearchInteractor: SearchInteractor): ISearchMVP.Interactor =
            searchSearchInteractor

    @Provides
    fun provideSearchResultInteractor(interactor: SearchResultInteractor): ISearchResultsMVP.Interactor =
            interactor

    @Provides
    fun provideOfferResultInteractor(interactor: OfferResultInteractor): IOfferResultsMVP.Interactor =
            interactor

    @Provides
    fun provideRequestsInteractor(interactor: AllRequestsInteractor): IRequestsMVP.Interactor =
            interactor

    @Provides
    internal fun provideInteractor(interactor: ProfileInteractor): IProfileMVP.Interactor =
            interactor

    @Provides
    fun provideCarInteractor(carInteractor: CarInteractor): ICarMVP.Interactor =
            carInteractor

    @Provides
    fun provideHomeInteractor(interactor: HomeInteractor): IHomeMVP.Interactor =
            interactor

}