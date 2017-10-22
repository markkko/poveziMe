package com.example.markkko.povezime.core.car

import com.example.markkko.povezime.app.di.activity.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CarModule {

    @ActivityScope
    @Provides
    fun provideCarInteractor(carInteractorImpl: CarInteractorImpl): CarInteractor {
        return carInteractorImpl
    }

    @ActivityScope
    @Provides
    fun provideCarPresenter(carPresenterImpl: CarPresenterImpl): CarPresenter {
        return carPresenterImpl
    }

}