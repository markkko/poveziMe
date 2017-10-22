package com.example.markkko.povezime.core.car

import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.data.apis.CarApi
import com.example.markkko.povezime.core.models.dto.CarDTO
import io.reactivex.Single
import javax.inject.Inject

@ActivityScope
class CarInteractorImpl @Inject constructor(val carApi: CarApi): CarInteractor {

    override fun newCar(car: CarDTO): Single<CarDTO> {
        return carApi.addCar(car)
    }
}