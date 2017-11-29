package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.app.user.UserRepository
import com.example.markkko.povezime.core.data.apis.OfferApi
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.OfferResult
import com.example.markkko.povezime.core.models.User
import io.reactivex.Single
import javax.inject.Inject


class OfferInteractor @Inject constructor(private val offerApi: OfferApi,
                                          private val userRepository: UserRepository)
    : IOfferMVP.Interactor{

    override fun offerRide(offer: OfferRequest): Single<List<OfferResult>> =
            offerApi.offerRide(offer)

    override fun me(): User = userRepository.user
}