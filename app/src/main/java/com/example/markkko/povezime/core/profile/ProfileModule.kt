package com.example.markkko.povezime.core.profile

import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

    @Provides
    internal fun providePresenter(presenter: ProfilePresenter): IProfileMVP.Presenter =
            presenter

    @Provides
    internal fun provideInteractor(interactor: ProfileInteractor): IProfileMVP.Interactor =
            interactor

}
