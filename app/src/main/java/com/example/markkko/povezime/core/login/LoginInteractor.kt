package com.example.markkko.povezime.core.login

import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.models.UserData

import io.reactivex.Single


interface LoginInteractor : BaseInteractor {

    fun sendLoginInfoToServer(data: LoginPostData): Single<UserData>

}
