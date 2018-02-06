package com.example.markkko.povezime.core.profile

import com.example.markkko.povezime.core.repositoreis.UserRepository
import com.example.markkko.povezime.core.data.apis.UserApi
import com.example.markkko.povezime.core.models.User
import io.reactivex.Single
import javax.inject.Inject


class ProfileInteractor @Inject constructor(private val userRepository: UserRepository,
                                            private val userApi: UserApi): IProfileMVP.Interactor {

    override fun updateUser(data: User): Single<User> =
        userApi.updateUser(data)
                .doOnSuccess { userRepository.updateUser(it) }

    override fun me(): User = userRepository.user
}