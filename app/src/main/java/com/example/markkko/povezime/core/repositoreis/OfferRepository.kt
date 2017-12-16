package com.example.markkko.povezime.core.repositoreis

import com.example.markkko.povezime.core.models.Offer
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OfferRepository @Inject constructor() {

    var results: ArrayList<Offer> = ArrayList()

}
