package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.OfferResult
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface OfferApi {

    @POST("offer/ride")
    fun offerRide(@Body data: OfferRequest): Single<List<OfferResult>>

}