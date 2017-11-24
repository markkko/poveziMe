package com.example.markkko.povezime.core.auth.login


import com.example.markkko.povezime.core.data.apis.UserApi
import com.example.markkko.povezime.core.models.dto.UserDTO
import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val userApi: UserApi, private val schedulerProvider: SchedulerProvider)
    : ILoginMVP.Interactor {

    private val mAuth: FirebaseAuth? = null

    override fun sendLoginInfoToServer(data: LoginPostData): Single<UserDTO> {
        return userApi.sendInfo(data)
    }
}
