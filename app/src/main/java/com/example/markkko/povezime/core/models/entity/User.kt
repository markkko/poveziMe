package com.example.markkko.povezime.core.models.entity

import com.example.markkko.povezime.core.models.BaseEntity


open class User(override val id: Long = 0,
                val email: String,
                var name: String,
                var surname: String,
                var phone: String? = null,
                var profilePicture: String? = null,
                var viber: Int = 0,
                var whatsapp: Int = 0,
                var cars: MutableList<Car> = ArrayList(),
                var selectedCar: Car? = null
                ): BaseEntity