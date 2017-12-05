package com.example.markkko.povezime.core.models

import com.google.gson.annotations.SerializedName


data class Car(override val id: Long = 0,
               val make: String,
               val model: String,
               val seats: Int = 0,
               val image: String? = null,
               @SerializedName("user_id")
               val userId: Long,
               val email:String? = null): BaseEntity {

    fun makeDeepCopy(): Car {
        return Car(id, make, model, seats, image, userId, email)
    }
}