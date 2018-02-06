package com.example.markkko.povezime.core.di

import com.example.markkko.povezime.core.auth.completeInfo.CompleteInfoPresenter
import com.example.markkko.povezime.core.auth.completeInfo.ICompleteInfoMVP
import com.example.markkko.povezime.core.auth.login.ILoginMVP
import com.example.markkko.povezime.core.auth.login.LoginPresenter
import com.example.markkko.povezime.core.auth.registration.IRegistrationMVP
import com.example.markkko.povezime.core.auth.registration.RegistrationPresenter
import com.example.markkko.povezime.core.car.CarPresenter
import com.example.markkko.povezime.core.car.ICarMVP
import com.example.markkko.povezime.core.home.HomePresenter
import com.example.markkko.povezime.core.home.IHomeMVP
import com.example.markkko.povezime.core.home.offer.IOfferMVP
import com.example.markkko.povezime.core.home.offer.OfferPresenter
import com.example.markkko.povezime.core.home.search.ISearchMVP
import com.example.markkko.povezime.core.home.search.SearchPresenter
import com.example.markkko.povezime.core.profile.IProfileMVP
import com.example.markkko.povezime.core.profile.ProfilePresenter
import com.example.markkko.povezime.core.requests.AllRequestsPresenter
import com.example.markkko.povezime.core.requests.IRequestsMVP
import com.example.markkko.povezime.core.results.offer.IOfferResultsMVP
import com.example.markkko.povezime.core.results.offer.OfferResultPresenter
import com.example.markkko.povezime.core.results.search.ISearchResultsMVP
import com.example.markkko.povezime.core.results.search.SearchResultPresenter
import dagger.Module
import dagger.Provides


@Module(includes = [(InteractorModule::class)])
class PresenterModule {

    @Provides
    fun provideLoginPresenter(presenter: LoginPresenter): ILoginMVP.Presenter = presenter

    @Provides
    fun provideRegistrationPresenter(presenter: RegistrationPresenter): IRegistrationMVP.Presenter =
            presenter

    @Provides
    fun provideCompleteInfoPresenter(presenter: CompleteInfoPresenter): ICompleteInfoMVP.Presenter =
            presenter

    @Provides
    internal fun provideOfferPresenter(offerPresenter: OfferPresenter): IOfferMVP.Presenter =
            offerPresenter


    @Provides
    internal fun provideSearchPresenter(searchPresenter: SearchPresenter): ISearchMVP.Presenter =
            searchPresenter

    @Provides
    fun provideSearchResultPresenter(presenter: SearchResultPresenter): ISearchResultsMVP.Presenter =
            presenter

    @Provides
    fun provideOfferResultPresenter(presenter: OfferResultPresenter): IOfferResultsMVP.Presenter =
            presenter

    @Provides
    fun provideRequestsPresenter(presenter: AllRequestsPresenter): IRequestsMVP.Presenter =
            presenter

    @Provides
    internal fun providePresenter(presenter: ProfilePresenter): IProfileMVP.Presenter =
            presenter

    @Provides
    fun provideCarPresenter(carPresenter: CarPresenter): ICarMVP.Presenter {
        return carPresenter
    }

    @Provides
    fun provideHomePresenter(presenter: HomePresenter): IHomeMVP.Presenter =
            presenter

}