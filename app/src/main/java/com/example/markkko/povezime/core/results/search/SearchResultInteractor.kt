package com.example.markkko.povezime.core.results.search

import com.example.markkko.povezime.core.repositoreis.UserRepository
import com.example.markkko.povezime.core.data.apis.SearchApi
import com.example.markkko.povezime.core.models.*
import com.example.markkko.povezime.core.repositoreis.SearchRepository
import io.reactivex.Single
import javax.inject.Inject



class SearchResultInteractor @Inject constructor(private val searchRepository: SearchRepository,
                                                 private val userRepository: UserRepository,
                                                 private val searchApi: SearchApi): ISearchResultsMVP.Interactor {

    override fun getResults(): List<Offer> =
            searchRepository.results

    override fun postRequest(data: RequestReq): Single<MatchInfo> =
            searchApi.postRequest(data)
                    .doOnSuccess { searchRepository.markSent(it.offer) }

    override fun getCurrentSearch(): Search =
            searchRepository.currentSearch!!

    override fun me(): User = userRepository.user

}