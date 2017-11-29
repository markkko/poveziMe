package com.example.markkko.povezime.core.car

import dagger.Module
import dagger.Provides


@Module
class CarModule {

    @Provides
    fun provideCarInteractor(carInteractor: CarInteractor): ICarMVP.Interactor {
        return carInteractor
    }

    @Provides
    fun provideCarPresenter(carPresenter: CarPresenter): ICarMVP.Presenter {
        return carPresenter
    }

}