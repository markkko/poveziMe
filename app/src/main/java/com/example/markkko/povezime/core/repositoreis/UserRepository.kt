package com.example.markkko.povezime.core.repositoreis

import com.example.markkko.povezime.core.base.BaseRepository
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(): BaseRepository {

    lateinit var user: User

    fun addCar(car: Car) {
        user.cars.add(car)
        user.cars.sortBy { it.make.toLowerCase() }
    }

    fun updateUser(user: User) {
        this.user.name = user.name
        this.user.surname = user.surname
        this.user.phone = user.phone
        this.user.whatsapp = user.whatsapp
        this.user.viber = user.viber
    }

    override fun clear() {}
}
