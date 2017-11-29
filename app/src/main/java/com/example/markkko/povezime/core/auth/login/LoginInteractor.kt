package com.example.markkko.povezime.core.auth.login


import com.example.markkko.povezime.app.user.UserRepository
import com.example.markkko.povezime.core.auth.LoginResponse
import com.example.markkko.povezime.core.data.apis.UserApi
import com.example.markkko.povezime.core.models.User
import io.reactivex.Single
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val userApi: UserApi,
                                          private val userRepository: UserRepository)
    : ILoginMVP.Interactor {

    override fun sendLoginInfoToServer(data: LoginPostData): Single<User> {
        return userApi.sendInfo(data)
                .doOnSuccess { userRepository.user = it }
    }

}
