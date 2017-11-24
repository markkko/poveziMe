package com.example.markkko.povezime.core.models.dto

import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.models.BaseEntity
import com.google.gson.annotations.SerializedName


data class UserDTO(override val id: Long = 0,
                   var email: String,
                   var name: String?= null,
                   var surname: String?= null,
                   var phone: String?= null,
                   var profilePicture: String? = null,
                   var viber: Int,
                   var whatsapp: Int,
                   var cars: MutableList<CarDTO> = ArrayList(),
                   var selectedCar: CarDTO? = null): BaseEntity {

    @SerializedName("reg_id")
    var regId: String? = null
        set(regId) {
            field = this.regId
        }

    fun makeDeepCopy(): UserDTO {
        val cars = ArrayList<CarDTO>()
        cars.addAll(this.cars)
        return UserDTO(id, email, name, surname, phone, profilePicture, viber, whatsapp, cars, selectedCar?.makeDeepCopy())
    }

    fun isCompletedInfo(): Boolean =
            (isNullOrEmpty(name) || isNullOrEmpty(surname) || isNullOrEmpty(phone))

}
