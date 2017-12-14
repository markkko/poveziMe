package com.example.markkko.povezime.core.di

import com.example.markkko.povezime.core.auth.completeInfo.CompleteInfoPresenter
import com.example.markkko.povezime.core.auth.completeInfo.ICompleteInfoMVP
import com.example.markkko.povezime.core.auth.login.ILoginMVP
import com.example.markkko.povezime.core.auth.login.LoginPresenter
import com.example.markkko.povezime.core.auth.registration.IRegistrationMVP
import com.example.markkko.povezime.core.auth.registration.RegistrationPresenter
import com.example.markkko.povezime.core.car.CarPresenter
import com.example.markkko.povezime.core.car.ICarMVP
import com.example.markkko.povezime.core.home.offer.IOfferMVP
import com.example.markkko.povezime.core.home.offer.OfferPresenter
import com.example.markkko.povezime.core.home.search.ISearchMVP
import com.example.markkko.povezime.core.home.search.SearchPresenter
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
    fun provideCarPresenter(carPresenter: CarPresenter): ICarMVP.Presenter {
        return carPresenter
    }

}