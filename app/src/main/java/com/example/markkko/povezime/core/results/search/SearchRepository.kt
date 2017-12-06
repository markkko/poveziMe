package com.example.markkko.povezime.core.results.search

import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchRepository @Inject constructor() {

    var currentSearch: Search? = null

    var results: ArrayList<Offer> = ArrayList()

}
