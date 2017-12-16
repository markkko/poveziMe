package com.example.markkko.povezime.core.repositoreis

import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchRepository @Inject constructor() {

    var currentSearch: Search? = null

    var results: ArrayList<Offer> = ArrayList()

    fun markSent(offer: Offer) {
        val resultToMark = results.first { it.id == offer.id }
        resultToMark.isSent = true
    }

}
