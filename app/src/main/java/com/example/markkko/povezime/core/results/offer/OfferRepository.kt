package com.example.markkko.povezime.core.results.offer

import com.example.markkko.povezime.core.models.Offer
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OfferRepository @Inject constructor() {

    var results: ArrayList<Offer> = ArrayList()

}
