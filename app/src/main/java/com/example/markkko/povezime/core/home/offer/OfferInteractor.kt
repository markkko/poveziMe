package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.OfferResult
import io.reactivex.Single


interface  OfferInteractor: BaseInteractor {

    fun offerRide(offer: OfferRequest) : Single<List<OfferResult>>

}