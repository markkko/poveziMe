package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.core.data.apis.OfferApi
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.OfferResult
import io.reactivex.Single
import javax.inject.Inject


class OfferInteractorImpl @Inject constructor(private val offerApi: OfferApi): OfferInteractor {

    override fun offerRide(offer: OfferRequest): Single<List<OfferResult>> {
        return offerApi.offerRide(offer)
    }
}