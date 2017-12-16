package com.example.markkko.povezime.core.auth.completeInfo

import com.example.markkko.povezime.core.repositoreis.UserRepository
import com.example.markkko.povezime.core.data.apis.UserApi
import com.example.markkko.povezime.core.models.User
import javax.inject.Inject



class CompleteInfoInteractor @Inject constructor(private val userApi: UserApi,
                                                 private val userRepository: UserRepository)
    : ICompleteInfoMVP.Interactor {

    override fun updateUser(user: User) = userApi.updateUser(user)

    override fun me(): User = userRepository.user
}