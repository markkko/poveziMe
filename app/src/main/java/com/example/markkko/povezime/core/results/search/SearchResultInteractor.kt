package com.example.markkko.povezime.core.results.search

import com.example.markkko.povezime.app.user.UserRepository
import com.example.markkko.povezime.core.data.apis.SearchApi
import com.example.markkko.povezime.core.models.*
import io.reactivex.Single
import javax.inject.Inject



class SearchResultInteractor @Inject constructor(private val searchRepository: SearchRepository,
                                                 private val userRepository: UserRepository,
                                                 private val searchApi: SearchApi): ISearchResultsMVP.Interactor {

    override fun getResults(): List<Offer> =
            searchRepository.results

    override fun postRequest(data: SearchRequestReq): Single<SearchRequestRes> =
            searchApi.postRequest(data)

    override fun me(): User = userRepository.user

}