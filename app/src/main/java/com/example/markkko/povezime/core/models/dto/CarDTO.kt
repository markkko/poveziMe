package com.example.markkko.povezime.core.models.dto

import com.example.markkko.povezime.core.models.BaseEntity
import com.example.markkko.povezime.core.models.entity.Car


data class CarDTO(override val id: Long = 0,
                  val brand: String,
                  val model: String,
                  val seats: Int = 0,
                  val image: String? = null,
                  val email:String? = null): BaseEntity {

    fun transform(): Car {
        return Car(id, brand, model, seats, image)
    }
}