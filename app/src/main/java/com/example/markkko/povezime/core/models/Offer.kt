package com.example.markkko.povezime.core.models

import com.google.gson.annotations.SerializedName


class Offer(override val id: Long,
                 val user: User,
                 val from: String,
                 val to: String,
                 val seats: Int,
                 val date: String,
                 @SerializedName("one_day") var oneDay: Int,
                 var luggage: Int

): BaseEntity {

    var isSent: Boolean = false

}