package com.example.markkko.povezime.core.auth

import com.example.markkko.povezime.core.auth.completeInfo.CompleteInfoInteractor
import com.example.markkko.povezime.core.auth.completeInfo.CompleteInfoPresenter
import com.example.markkko.povezime.core.auth.completeInfo.ICompleteInfoMVP
import com.example.markkko.povezime.core.auth.login.ILoginMVP
import com.example.markkko.povezime.core.auth.login.LoginInteractor
import com.example.markkko.povezime.core.auth.login.LoginPresenter
import com.example.markkko.povezime.core.auth.registration.IRegistrationMVP
import com.example.markkko.povezime.core.auth.registration.RegistrationPresenter
import dagger.Module
import dagger.Provides


@Module
class AuthModule {

    @Provides
    fun provideLoginInteractor(interactor: LoginInteractor): ILoginMVP.Interactor = interactor

    @Provides
    fun provideLoginPresenter(presenter: LoginPresenter): ILoginMVP.Presenter = presenter

    @Provides
    fun provideRegistrationPresenter(presenter: RegistrationPresenter): IRegistrationMVP.Presenter =
            presenter

    @Provides
    fun provideCompleteInfoPresenter(presenter: CompleteInfoPresenter): ICompleteInfoMVP.Presenter =
            presenter

    @Provides
    fun provideCompleteInfoInteractor(interactor: CompleteInfoInteractor): ICompleteInfoMVP.Interactor =
            interactor

}