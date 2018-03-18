package com.example.markkko.povezime.core.results.offer

import com.example.markkko.povezime.core.repositoreis.UserRepository
import com.example.markkko.povezime.core.data.apis.SearchApi
import com.example.markkko.povezime.core.models.*
import com.example.markkko.povezime.core.repositoreis.OfferRepository
import com.example.markkko.povezime.core.repositoreis.SearchRepository
import io.reactivex.Single
import javax.inject.Inject



class OfferResultInteractor @Inject constructor(private val offerRepository: OfferRepository,
                                                private val userRepository: UserRepository,
                                                private val searchApi: SearchApi): IOfferResultsMVP.Interactor {

    override fun getResults(): List<Search> =
            offerRepository.results

    override fun postRequest(data: RequestReq): Single<MatchInfo> =
            searchApi.postRequest(data)
                    .doOnSuccess { offerRepository.markSent(it.search) }

    override fun getCurrentOffer(): Offer =
            offerRepository.currentOffer!!

    override fun me(): User = userRepository.user

}