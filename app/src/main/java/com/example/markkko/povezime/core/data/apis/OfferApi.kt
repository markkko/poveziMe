package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.OfferResultsReq
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface OfferApi {

    @POST("offer/ride")
    fun offerRide(@Body data: OfferResultsReq): Single<List<Offer>>

}