package com.example.markkko.povezime.core.models


import com.example.markkko.povezime.core.models.BaseEntity
import com.google.gson.annotations.SerializedName

import java.util.Date

import lombok.Data


data class SearchResultData(override val id: Long,
                            val email: String,
                            val from: String,
                            val to: String,
                            val seats: Int,
                            val date: Date,
                            @SerializedName("one_day") var oneDay: Int,
                            var luggage: Int

) : BaseEntity
