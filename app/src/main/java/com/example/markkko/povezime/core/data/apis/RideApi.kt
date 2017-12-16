package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.models.MatchInfo
import com.example.markkko.povezime.core.models.UserId
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface RideApi {

    @POST("ride/request/all")
    fun getAllRequests(@Body userId: UserId): Single<List<MatchInfo>>

}