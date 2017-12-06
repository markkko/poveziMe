package com.example.markkko.povezime.core.models


import com.google.gson.annotations.SerializedName


class Search(override val id: Long,
             @SerializedName("user_id") val userId: Long,
             val user: String,
             val from: String,
             val to: String,
             val seats: Int,
             val date: String,
             val time: String,
             @SerializedName("one_day") var oneDay: Int,
             var luggage: Int

): BaseEntity {

    var isSent: Boolean = false

}
