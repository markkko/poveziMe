package com.example.markkko.povezime.core.models

import com.example.markkko.povezime.R.string.date
import com.google.gson.annotations.SerializedName


//from: 44.872656299999996,18.8106276, to: 44.786567999999995,20.448921599999995, date: 2017-11-29, luggage: 0, oneday: 1, seats: 2

class SearchRequest(@SerializedName("user_id") val userId: Long,
                    var from: String,
                    var to: String,
                    var date: String,
                    var luggage: Int,
                    @SerializedName("one_day")
                    var oneDay: Int,
                    var seats: Int,
                    var offerId: String? = null) {

    override fun toString(): String {
        return "from: $from, to: $to, date: $date, luggage: ${luggage.toString()}, oneday: ${oneDay.toString()}, seats: ${seats.toString()}"
    }

    companion object {
        fun getDefaultRequest(id: Long, date: String) =
                SearchRequest(id, from = "44.872,18.81", to = "44.786,20.448", date = date, luggage = 0, oneDay = 1, seats = 2)

    }
}
