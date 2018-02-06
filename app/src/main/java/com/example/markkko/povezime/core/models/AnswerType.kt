package com.example.markkko.povezime.core.models

import com.google.gson.annotations.SerializedName


enum class AnswerType {

    A,
    D,
    @SerializedName("pending") PENDING

}
