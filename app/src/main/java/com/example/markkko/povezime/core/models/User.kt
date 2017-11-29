package com.example.markkko.povezime.core.models

import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.google.gson.annotations.SerializedName


data class User(override val id: Long,
                var email: String,
                var name: String?= null,
                var surname: String?= null,
                var phone: String?= null,
                var image: String? = null,
                var viber: Int,
                var whatsapp: Int,
                var cars: MutableList<Car> = ArrayList(),
                var selectedCar: Car? = null): BaseEntity {

    @SerializedName("reg_id")
    var regId: String? = null
        set(regId) {
            field = this.regId
        }

    fun makeDeepCopy(): User {
        val cars = ArrayList<Car>()
        cars.addAll(this.cars)
        return User(id, email, name, surname, phone, image, viber, whatsapp, cars, selectedCar?.makeDeepCopy())
    }

    fun isCompletedInfo(): Boolean =
            (isNullOrEmpty(name) || isNullOrEmpty(surname) || isNullOrEmpty(phone))

    companion object {
        fun emptyUser(): User = User(id = 0, email = "", viber = 0, whatsapp = 0)
    }

}
