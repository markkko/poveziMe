package com.example.markkko.povezime.core.models

import com.google.gson.annotations.SerializedName


class OfferResultsReq(@SerializedName("user_id") val userId: Long,
                      val date: String,
                      val time: String,
                      val route: String,
                      @SerializedName("car_id") val carId: Long,
                      val seats: Int,
                      val luggage: Int,
                      @SerializedName("from_name") val fromName: String,
                      @SerializedName("to_name") val toName: String) {

    override fun toString(): String {
        return "route: $route, date: $date, car_id:$carId time:$time, seats:$seats, luggage:$luggage"
    }

}