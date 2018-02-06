package com.example.markkko.povezime.core.repositoreis

import com.example.markkko.povezime.core.base.BaseRepository
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OfferRepository @Inject constructor() : BaseRepository {

    var results: ArrayList<Search> = ArrayList()

    var currentOffer: Offer? = null

    fun markSent(search: Search) {
        val resultToMark = results.first { it.id == search.id }
        resultToMark.isSent = true
    }

    override fun clear() {
        currentOffer = null
        results = ArrayList()
    }
}
