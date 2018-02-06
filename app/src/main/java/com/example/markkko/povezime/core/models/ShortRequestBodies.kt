package com.example.markkko.povezime.core.models

import com.google.gson.annotations.SerializedName


class UserId(@SerializedName("user_id") val userId: Long)

class RequestReq(@SerializedName("user_id") val userId: Long,
                 @SerializedName("search_id") val searchId: Long,
                 @SerializedName("offer_id") val offerId: Long)
