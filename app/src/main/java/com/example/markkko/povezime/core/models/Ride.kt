package com.example.markkko.povezime.core.models


interface Ride: BaseEntity {

    val userId: Long?
    val user: User?
    val fromName: String
    val toName: String
    val seats: Int
    val date: String
    var luggage: Int

}