package com.example.markkko.povezime.core.home.search

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.models.SearchRequestData
import com.example.markkko.povezime.core.models.SearchResultData

import io.reactivex.Single


interface SearchInteractor : BaseInteractor {

    fun getSearchResults(data: SearchRequestData): Single<List<SearchResultData>>

}
