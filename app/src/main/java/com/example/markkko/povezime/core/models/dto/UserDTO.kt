package com.example.markkko.povezime.core.models.dto

import com.example.markkko.povezime.core.models.BaseEntity
import com.example.markkko.povezime.core.models.entity.Car
import com.example.markkko.povezime.core.models.entity.User


data class UserDTO(override val id: Long = 0,
                   var email: String,
                   var name: String,
                   var surname: String,
                   var phone: String,
                   var profilePicture: String? = null,
                   var viber: Int,
                   var whatsapp: Int,
                   var cars: MutableList<CarDTO>,
                   var selectedCar: CarDTO? = null): BaseEntity {

    fun transform(): User {
        val cars = ArrayList<Car>()
        this.cars.mapTo(cars) { it.transform() }
        return User(id, email, name, surname, phone, profilePicture, viber, whatsapp, cars, selectedCar?.transform())
    }

}
