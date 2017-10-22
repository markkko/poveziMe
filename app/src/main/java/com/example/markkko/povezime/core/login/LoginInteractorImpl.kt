package com.example.markkko.povezime.core.login


import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.data.apis.LoginApi
import com.example.markkko.povezime.core.models.dto.UserDTO
import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.firebase.auth.FirebaseAuth

import javax.inject.Inject

import io.reactivex.Single

@ActivityScope
class LoginInteractorImpl @Inject
constructor(private val loginApi: LoginApi, private val schedulerProvider: SchedulerProvider) : LoginInteractor {

    private val mAuth: FirebaseAuth? = null

    override fun unbind() {

    }

    override fun sendLoginInfoToServer(data: LoginPostData): Single<UserDTO> {
        return loginApi.sendInfo(data)
    }
}
