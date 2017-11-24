package com.example.markkko.povezime.core.auth.completeInfo

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.data.apis.UserApi
import com.example.markkko.povezime.core.models.dto.UserDTO
import javax.inject.Inject



class CompleteInfoInteractor @Inject constructor(private val userApi: UserApi)
    : ICompleteInfoMVP.Interactor {

    override fun updateUser(user: UserDTO) = userApi.updateUser(user)
}