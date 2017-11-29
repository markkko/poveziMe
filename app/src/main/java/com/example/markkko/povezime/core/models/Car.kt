package com.example.markkko.povezime.core.models


data class Car(override val id: Long = 0,
               val brand: String,
               val model: String,
               val seats: Int = 0,
               val image: String? = null,
               val email:String? = null): BaseEntity {

    fun makeDeepCopy(): Car {
        return Car(id, brand, model, seats, image, email)
    }
}