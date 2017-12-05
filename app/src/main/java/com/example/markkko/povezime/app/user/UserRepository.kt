package com.example.markkko.povezime.app.user

import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor() {

    lateinit var user: User

    fun addCar(car: Car) {
        user.cars.add(car)
        user.cars.sortBy { it.make.toLowerCase() }
    }
}
