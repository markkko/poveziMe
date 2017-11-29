package com.example.markkko.povezime.core.car

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.models.Car
import io.reactivex.Single


interface CarInteractor: BaseInteractor{

    fun newCar(car: Car): Single<Car>

}