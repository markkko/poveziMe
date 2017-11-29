package com.example.markkko.povezime.core.car

import com.example.markkko.povezime.app.user.UserRepository
import com.example.markkko.povezime.core.data.apis.CarApi
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.User
import io.reactivex.Single
import javax.inject.Inject

class CarInteractor @Inject constructor(private val carApi: CarApi,
                                        private val userRepository: UserRepository) : ICarMVP.Interactor {

    override fun newCar(car: Car): Single<Car> {
        return carApi.addCar(car)
                .doOnSuccess { userRepository.addCar(car) }
    }

    override fun me(): User = userRepository.user
}