package com.example.markkko.povezime.core.models.entity

import com.example.markkko.povezime.core.models.BaseEntity


open class Car(override val id: Long = 0,
               val brand: String,
               val model: String? = null,
               val seats: Int = 0,
               val image: String? = null):BaseEntity