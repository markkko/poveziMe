package com.example.markkko.povezime.core.models


import com.google.gson.annotations.SerializedName


class Search(override val id: Long = 0,
             override @SerializedName("user_id") val userId: Long? = null,
             override val user: User? = null,
             val from: String = "",
             val to: String = "",
             override @SerializedName("from_name")val fromName: String,
             override @SerializedName("to_name")val toName: String,
             override val seats: Int,
             override val date: String,
             @SerializedName("one_day") var oneDay: Int,
             override var luggage: Int

): Ride {

    var isSent: Boolean = false

    override fun toString(): String {
        return "from: $from, to: $to, date: $date, luggage: ${luggage.toString()}, oneday: ${oneDay.toString()}, seats: ${seats.toString()}"
    }

    companion object {
        fun getDefaultRequest(id: Long, date: String) =
                Search(userId = id, from = "44.872,18.81", to = "44.786,20.448", date = date, luggage = 0, oneDay = 1, seats = 2,
                        fromName = "Brcko", toName = "Bijeljina")

    }

}
