package com.example.markkko.povezime.core.requests

import com.example.markkko.povezime.core.data.apis.RideApi
import com.example.markkko.povezime.core.models.MatchInfo
import com.example.markkko.povezime.core.models.UserId
import com.example.markkko.povezime.core.repositoreis.RequestsRepository
import com.example.markkko.povezime.core.repositoreis.UserRepository
import io.reactivex.Single
import javax.inject.Inject


class AllRequestsInteractor @Inject constructor(private val requestsRepository: RequestsRepository,
                            private val userRepository: UserRepository,
                            private val rideApi: RideApi): IRequestsMVP.Interactor {

    override fun getAllRequests(): Single<List<MatchInfo>> {
        val id = userRepository.user.id
        return rideApi.getAllRequests(UserId(id))
                .doOnSuccess { requestsRepository.requests = it }
    }

}
