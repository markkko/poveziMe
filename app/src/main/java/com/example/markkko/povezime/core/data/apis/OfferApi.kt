package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.OfferResult
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET

interface OfferApi {

    @GET("ride/offer")
    fun offerRide(@Body data: OfferRequest): Single<List<OfferResult>>

}