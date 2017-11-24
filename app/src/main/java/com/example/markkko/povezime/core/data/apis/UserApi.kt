package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.auth.login.LoginPostData
import com.example.markkko.povezime.core.models.dto.UserDTO

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface UserApi {

    @POST("user/firebaselogin")
    fun sendInfo(@Body data: LoginPostData): Single<UserDTO>

    @POST("user/update")
    fun updateUser(@Body user: UserDTO): Single<UserDTO>

}
