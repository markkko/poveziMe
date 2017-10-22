package com.example.markkko.povezime.core.login


import com.example.markkko.povezime.app.di.activity.ActivityScope

import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    @ActivityScope
    fun provideLoginInteractor(interactor: LoginInteractorImpl): LoginInteractor {
        return interactor
    }

    @Provides
    @ActivityScope
    fun provideLoginPresenter(presenter: LoginPresenterImpl): LoginPresenter {
        return presenter
    }

}
