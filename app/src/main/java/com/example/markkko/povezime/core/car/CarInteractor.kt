package com.example.markkko.povezime.core.car

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.models.dto.CarDTO
import io.reactivex.Single


interface CarInteractor: BaseInteractor{

    fun newCar(car: CarDTO): Single<CarDTO>

}