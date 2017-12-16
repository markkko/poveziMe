package com.example.markkko.povezime.core.models

import com.google.gson.annotations.SerializedName


class Offer(override val id: Long = 0,
            override @SerializedName("user_id") val userId: Long? = null,
            override val user: User? = null,
            override @SerializedName("from_name") val fromName: String,
            override @SerializedName("to_name") val toName: String,
            @SerializedName("car_id") val carId: Long? = null,
            val route: String? = null,
            override val seats: Int,
            override val date: String,
            val time: String,
            override var luggage: Int

) : Ride {

    var isSent: Boolean = false

    override fun toString(): String {
        return "route: $route, date: $date, car_id:$carId time:$time, seats:$seats, luggage:$luggage"
    }

}