package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.login.LoginPostData
import com.example.markkko.povezime.core.models.UserData

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginApi {

    @POST("user/firebaselogin")
    fun sendInfo(@Body data: LoginPostData): Single<UserData>

}
