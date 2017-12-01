package com.example.markkko.povezime.core.models

import com.google.gson.annotations.SerializedName


class OfferRequest(val date: String,
                   val time: String,
                   val route: String,
                   @SerializedName("car_id") val carId: Long,
                   val seats: Int,
                   val luggage: Int,
                   @SerializedName("user_id") val userId: Long) {

    override fun toString(): String {
        return "route: $route, date: $date, car_id:$carId time:$time, seats:$seats, luggage:$luggage"
    }
}