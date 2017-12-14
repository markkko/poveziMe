package com.example.markkko.povezime.core.auth.login


import android.content.SharedPreferences
import com.example.markkko.povezime.app.user.UserRepository
import com.example.markkko.povezime.core.data.apis.UserApi
import com.example.markkko.povezime.core.models.User
import io.reactivex.Single
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val userApi: UserApi,
                                          private val userRepository: UserRepository,
                                          private val prefs: SharedPreferences)
    : ILoginMVP.Interactor {

    override fun sendLoginInfoToServer(data: LoginPostData): Single<User> {
        return userApi.sendInfo(data)
                .doOnSuccess { prefs.edit().putString("token", it.token).apply() }
                .map { it.createUser() }
                .doOnSuccess { it.cars.sortBy { it.make.toLowerCase() } }
                .doOnSuccess { userRepository.user = it }
    }

}
