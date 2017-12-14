package com.example.markkko.povezime.core.models


class LoginResponse(val id: Long,
                    var email: String,
                    var name: String?= null,
                    var surname: String?= null,
                    var phone: String?= null,
                    var image: String? = null,
                    var viber: Int,
                    var whatsapp: Int,
                    val token: String,
                    var cars: MutableList<Car> = ArrayList(),
                    var selectedCar: Car? = null) {

    fun createUser(): User {
        return User(id, email, name, surname, phone, image, viber, whatsapp, cars, selectedCar)
    }

}