package com.example.markkko.povezime.core.models

import com.google.gson.annotations.SerializedName

class SearchResultsReq(@SerializedName("user_id") val userId: Long,
                       val from: String,
                       val to: String,
                       val date: String,
                       val luggage: Int,
                       @SerializedName("one_day") val oneDay: Int,
                       val seats: Int,
                       @SerializedName("from_name") val fromName: String,
                       @SerializedName("to_name") val toName: String) {

    override fun toString(): String {
        return "from: $from, to: $to, date: $date, luggage: ${luggage.toString()}, oneday: ${oneDay.toString()}, seats: ${seats.toString()}"
    }

    companion object {
        fun getDefaultRequest(id: Long, date: String) =
                SearchResultsReq(id, from = "44.872,18.81", to = "44.786,20.448", date = date, luggage = 0, oneDay = 1, seats = 2,
                        fromName = "Brcko", toName = "Bijeljina")

    }
}

class SearchRequestReq(@SerializedName("user_id") val userId: Long,
                       @SerializedName("search_id") val searchId: Long,
                       @SerializedName("offer_id") val offerId: Long)
